/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.scheduler.jobs;

import com.devspecops.airdrop.commands.EC2Command;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 *
 * @author testep
 */
public class StartEnvJob implements Job {
    public static final String ENV_NAME = "envName";
    
    @Override
    public void execute(JobExecutionContext jExeCtx) {
        JobDataMap jDataMap = jExeCtx.getJobDetail().getJobDataMap();
        
        
        if(!jDataMap.containsKey(StartEnvJob.ENV_NAME))
            return;
        
        String envName = jDataMap.getString(StartEnvJob.ENV_NAME);
        
        EC2Command cmd = new EC2Command();
        
        System.out.println("Job scheduled to start environment " + envName);
        cmd.startEnvironment(envName);
    }
}