/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 *
 * @author testep
 */
public class DeploymentJob implements Job {
    @Override
    public void execute(JobExecutionContext jExeCtx) {
        JobDataMap jDataMap = jExeCtx.getJobDetail().getJobDataMap();
        
        
    }
}
