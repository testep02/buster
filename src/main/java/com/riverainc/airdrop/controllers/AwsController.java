/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.controllers;

import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.riverainc.airdrop.commands.EC2Command;
import com.riverainc.airdrop.deployment.SnapshotSql;
import com.riverainc.airdrop.models.EnvBackup;
import com.riverainc.airdrop.models.Environment;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author testep
 */

/**
 * 
 * @description
 * This controller provides the functionality for the AWS functions
 */

@Controller
public class AwsController {
    
    @RequestMapping("/aws/home")
    public void getHome(Model model) {
        
    }
    
    @RequestMapping("/aws/environments/environments")
    public void listEnvironments(Model model) {
        EC2Command cmd = new EC2Command();
        
        Map<String, String> envs = cmd.getEnvironmentStatuses();
        
        model.addAttribute("envs", envs);
    }
    
    @RequestMapping("/aws/environments/envDetails")
    public void getEnvironmentDetails(@RequestParam(value="envName", required=true) String envName, Model model) {
        System.out.println("Env passed: " + envName);
    }
    
    @RequestMapping("/aws/environments/envSnapshots")
    public void getEnvSnapshots(@RequestParam(value="envName", required=true) String envName) {
        
    }
    
    @RequestMapping("/aws/environments/createSnapshot")
    public void createEnvSnapshot(@RequestParam("envName") String envName) {
        EC2Command cmd = new EC2Command();
        EnvBackup backup = cmd.createEnvSnapshot(envName);
        
        SnapshotSql snapSql = new SnapshotSql();
        backup.setBackupId(snapSql.insertBackup(backup));
        
        snapSql.insertSnapshotResults(backup);
    }
    
    @RequestMapping("/aws/servers/servers")
    public void listServers(Model model) {
        
    }
    
    @RequestMapping("/aws/servers/srvrDetails")
    public void getServerDetails(@RequestParam(value="instanceId", required=true) String instanceId, Model model) {
        
    }
    
    @RequestMapping("/aws/services/services")
    public void listServices(Model model) {
        
    }
    
    @RequestMapping("/aws/services/srvcDetails")
    public void getServiceDetails(@RequestParam(value="instanceId", required=true) String instanceId,
                                @RequestParam(value="srvcName", required=true) String serviceName,
                                Model model) {
        
    }
    
    @RequestMapping("/aws/instances/instances")
    public void getInstances() {
        
    }
    
    @RequestMapping("/aws/instances/instance")
    public void getInstanceDetails(@RequestParam(value="instanceId", required=true) String instanceId) {
        System.out.println("InstanceId Passed: " + instanceId);
    }
    
    @RequestMapping("/aws/schedule/schedules")
    public void getSchedules() {
        
    }
    
    @RequestMapping("/aws/schedule/schedule")
    public void getScheduleDetails(@RequestParam(value="sheduleName") String schName) {
        System.out.println("Schedule Name Passed: " + schName);
    }
    
    @RequestMapping("/aws/schedule/editSchedule")
    public void editSchedule(@RequestParam(value="scheduleName") String schName) {
        System.out.println("Schedule Name Passed: " + schName);
    }
    
    @RequestMapping("/aws/schedule/createSchedule")
    public void createSchedule() {
        
    }
    
    @RequestMapping("/aws/schedule/createScheduleAction")
    public void createScheduleAction(@RequestParam(value="schName", defaultValue="Generic Schedule") String schName,
            @RequestParam(value="schDesc") String schDesc,
            @RequestParam(value="schEnabled") String schEnabled,
            @RequestParam(value="schCron") String schCron,
            @RequestParam(value="schEnv") String schEnv) {
        
    }
    
}
