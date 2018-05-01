/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.controllers;

import com.devspecops.airdrop.deployment.DeploymentSql;
import com.devspecops.airdrop.models.BuildLog;
import com.devspecops.airdrop.models.BusterBuild;
import com.devspecops.airdrop.models.ScheduledBuild;
import com.devspecops.airdrop.security.BusterRoles;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author testep
 */

@Controller
public class DeploymentsController {
    
    @RequestMapping("/deployments/deploymentList")
    public void getDeploymentList(Model model) {
        
        try{            
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUser = auth.getPrincipal().toString();

            DeploymentSql sql = new DeploymentSql();
            List<BusterBuild> buildList = sql.getDeployments();

            List<String> roles = new ArrayList<>();

            auth.getAuthorities().stream().forEach((role) -> {
                roles.add(role.getAuthority());
            });

            model.addAttribute("currentUser", currentUser);
            model.addAttribute("roles", roles);
            model.addAttribute("builds", buildList);
        } catch(Exception e) {
            System.out.println("Error getting deployments: Class " + e.getClass());
            System.out.println(e.getMessage());
            System.out.println("Cause:");
            System.out.println(e.getCause());
            System.out.println("Stacktrace:");
            if(e.getStackTrace() != null && e.getStackTrace().length > 0) {
                for(StackTraceElement st : e.getStackTrace()) {
                    System.out.println("File Name: " + st.getFileName() 
                            + "Class: " + st.getClassName() 
                            + "Line: " + st.getLineNumber() 
                            + "Method: " + st.getMethodName());
                    System.out.println("------------------------------------------------------------------------------------------------");
                }
            }
        }
    }
    
    @RequestMapping("/deployments/approveBuild")
    public void approveBuild(@RequestParam("buildId") int buildId,
            @RequestParam("currentState") int currentState,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", roles);
        model.addAttribute("buildId", buildId);
        model.addAttribute("currentState", currentState);
    }
    
    @RequestMapping("/deployments/approveBuildConfirmation")
    public void approveBuildConfirmation(@RequestParam("buildId") int buildId,
            @RequestParam("currentState") int currentState,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        if(BusterRoles.isUserInRole(currentState, roles)) {
            DeploymentSql sql = new DeploymentSql();
            sql.incrementBuildState(buildId, ++currentState);
            sql.insertBuildLog(currentUser, buildId, currentState, ++currentState);
        }
        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", roles);
        model.addAttribute("buildId", buildId);
    }
    
    @RequestMapping("/deployments/rejectBuild")
    public void rejectBuild(@RequestParam("buildId") int buildId,
            @RequestParam("currentState") int currentState,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        DeploymentSql sql = new DeploymentSql();
        
        BusterBuild build = sql.getBuildDetails(buildId);
        
        model.addAttribute("roles", roles);
        model.addAttribute("build", build);
    }
    
    @RequestMapping("/deployments/rejectBuildConfirmation")
    public void confirmRejection(@RequestParam("buildId") int buildId,
            @RequestParam("currentState") int currentState,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        if(BusterRoles.isUserInRole(currentState, roles)) {
                DeploymentSql sql = new DeploymentSql();
                sql.updateBuildReject(currentUser, buildId, currentState);
                sql.insertBuildLog(currentUser, buildId, currentState, 99);
        }
        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", roles);
        model.addAttribute("buildId", buildId);
    }
    
    @RequestMapping("/deployments/scheduleDeployment")
    public void scheduleDeployment(@RequestParam("buildId") int buildId,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        DeploymentSql sql = new DeploymentSql();
        BusterBuild bb = sql.getBuildDetails(buildId);
        
        model.addAttribute("build", bb);
    }
    
    @PostMapping("/deployments/scheduleDeploymentConfirmation")
    public void confirmSchedule(@ModelAttribute BusterBuild scheduledBuild, Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        int currentBuildState = scheduledBuild.getBuildState();
        
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        DeploymentSql sql = new DeploymentSql();
        sql.createDeployment(scheduledBuild);
        sql.incrementBuildState(scheduledBuild.getBuildId(), ++currentBuildState);
        sql.insertBuildLog(currentUser, scheduledBuild.getBuildId(), 
                currentBuildState, ++currentBuildState);
        
        model.addAttribute("scheduledBuild", scheduledBuild);
    }
    
    @RequestMapping("/deployments/buildLogs")
    public void viewBuildLogs(@RequestParam("buildId") int buildId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        DeploymentSql sql = new DeploymentSql();
        BusterBuild bb = sql.getBuildDetails(buildId);
        List<BuildLog> buildLogs = sql.getBuildLogs(buildId);
        
        model.addAttribute("roles", roles);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("build", bb);
        model.addAttribute("buildLogs", buildLogs);
    }
}
