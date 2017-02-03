/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.controllers;

import com.riverainc.airdrop.deployment.DeploymentSql;
import com.riverainc.airdrop.models.BusterBuild;
import com.riverainc.airdrop.security.BusterRoles;
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
import org.springframework.web.bind.annotation.RequestMapping;
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
        model.addAttribute("buildId", buildId);
        model.addAttribute("currentState", currentState);
    }
    
    @RequestMapping("/deployments/approveBuildConfirmation")
    public void approveBuildConfirmation(@RequestParam("buildId") int buildId,
            @RequestParam("currentState") int currentState,
            Model model) {
        
        int userAuthStatus = 0;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        if(currentState == 0 && BusterRoles.isUserInRole(currentState, roles)) {
            if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_TEST) || 
                    roles.contains(BusterRoles.E6_BUSTER_ADMIN)) {
                DeploymentSql sql = new DeploymentSql();
                if(sql.incrementBuildState(buildId, ++currentState)) {
                    
                }
            }
        } else if(currentState == 3 && BusterRoles.isUserInRole(currentState, roles)) {
            if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_QA) || 
                    roles.contains(BusterRoles.E6_BUSTER_ADMIN)) {
                DeploymentSql sql = new DeploymentSql();
                if(sql.incrementBuildState(buildId, ++currentState)) {
                    
                }
            }            
        } else if(currentState == 6 && BusterRoles.isUserInRole(currentState, roles)) {
            if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_ALPHA) || 
                    roles.contains(BusterRoles.E6_BUSTER_ADMIN)) {
                DeploymentSql sql = new DeploymentSql();
                if(sql.incrementBuildState(buildId, ++currentState)) {
                    
                }
            }
        }
        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userAuthStatus", userAuthStatus);
        model.addAttribute("buildId", buildId);
    }
    
    @RequestMapping("/deployments/rejectBuild")
    public void rejectBuild(@RequestParam("buildId") int buildId,
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
        
        model.addAttribute("buildId", buildId);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        
        List<String> roles = new ArrayList<>();
        
        auth.getAuthorities().stream().forEach((role) -> {
            roles.add(role.getAuthority());
        });
        
        if(currentState == 0 && BusterRoles.isUserInRole(currentState, roles)) {
            if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_TEST) || 
                    roles.contains(BusterRoles.E6_BUSTER_ADMIN)) {
                DeploymentSql sql = new DeploymentSql();
                if(sql.incrementBuildState(buildId, ++currentState)) {
                    
                }
            }
        } else if(currentState == 3 && BusterRoles.isUserInRole(currentState, roles)) {
            if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_QA) || 
                    roles.contains(BusterRoles.E6_BUSTER_ADMIN)) {
                DeploymentSql sql = new DeploymentSql();
                if(sql.incrementBuildState(buildId, ++currentState)) {
                    
                }
            }            
        } else if(currentState == 6 && BusterRoles.isUserInRole(currentState, roles)) {
            if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_ALPHA) || 
                    roles.contains(BusterRoles.E6_BUSTER_ADMIN)) {
                DeploymentSql sql = new DeploymentSql();
                if(sql.incrementBuildState(buildId, ++currentState)) {
                    
                }
            }
        }
    }
    
    @RequestMapping("/deployments/scheduleDeployment")
    public void scheduleDeployment(@RequestParam("buildId") int buildId,
            Model model) {
        
        model.addAttribute("buildId", buildId);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
    
    @RequestMapping("/deployments/confirmSchedule")
    public void confirmSchedule(@RequestParam("buildId") int buildId,
            Date deploymentDate,
            Model model) {
        
        model.addAttribute("buildId", buildId);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
}
