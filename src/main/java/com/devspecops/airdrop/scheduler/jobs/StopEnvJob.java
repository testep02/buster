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
public class StopEnvJob implements Job {
    
    public static final String ENV_NAME = "envName";
    
    public String envName;
    
    @Override
    public void execute(JobExecutionContext jExeCtx) {
        JobDataMap jDataMap = jExeCtx.getJobDetail().getJobDataMap();
        
        envName = jDataMap.getString(StopEnvJob.ENV_NAME);
        
        EC2Command cmd = new EC2Command();
        cmd.stopEnvironment(envName);
    }
}
