/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.controllers;

import com.riverainc.airdrop.security.LDAPUtil;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.riverainc.airdrop.commands.EC2Command;
import com.riverainc.airdrop.models.JenkinsBuild;
import com.riverainc.airdrop.models.InstancePricing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author testep
 */

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        JenkinsBuild jenkinsBuild = new JenkinsBuild();
        
        model.addAttribute("jenkinsBuilds", jenkinsBuild);
        
        EC2Command ec2Command = new EC2Command();
        LDAPUtil lc = new LDAPUtil();
        
        DescribeAvailabilityZonesResult azResult = ec2Command.myAvailabilityZones();
        
        model.addAttribute("numAzs", azResult.getAvailabilityZones().size());
        model.addAttribute("azs", azResult.getAvailabilityZones().toArray());
        
        return "home";
    }
}
