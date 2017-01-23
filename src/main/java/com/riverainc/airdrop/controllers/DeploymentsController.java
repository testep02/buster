/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.controllers;

import com.riverainc.airdrop.deployment.DeploymentSql;
import com.riverainc.airdrop.models.BusterBuild;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
        
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.addAll(auth.getAuthorities());
        
        DeploymentSql sql = new DeploymentSql();
        
        List<BusterBuild> buildList = sql.getDeployments();
        
    }
    
    @RequestMapping("/deployments/approveDeployment")
    public void approveDeployment(@RequestParam("buildNumber") String buildNumber,
            @RequestParam("currentStage") int currentStage,
            Model model) {
        
        model.addAttribute("buildNumber", buildNumber);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
    
    @RequestMapping("/deployments/confirmApproval")
    public void confirmApproval(@RequestParam("buildNumber") String buildNumber,
            Model model) {
        
        model.addAttribute("buildNumber", buildNumber);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
    
    @RequestMapping("/deployments/rejectDeployment")
    public void rejectDeployment(@RequestParam("buildNumber") String buildNumber,
            Model model) {
        
        model.addAttribute("buildNumber", buildNumber);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
    
    @RequestMapping("/deployments/confirmRejection")
    public void confirmRejection(@RequestParam("buildNumber") String buildNumber,
            Model model) {
        
        model.addAttribute("buildNumber", buildNumber);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
    
    @RequestMapping("/deployments/scheduleDeployment")
    public void scheduleDeployment(@RequestParam("buildNumber") String buildNumber,
            Model model) {
        
        model.addAttribute("buildNumber", buildNumber);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
    
    @RequestMapping("/deployments/confirmSchedule")
    public void confirmSchedule(@RequestParam("buildNumber") String buildNumber,
            Date deploymentDate,
            Model model) {
        
        model.addAttribute("buildNumber", buildNumber);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getPrincipal().toString();
    }
}
