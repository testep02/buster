/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.scheduler.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 *
 * @author testep
 */
public class UpdateJob implements Job {
    private Logger logger = Logger.getLogger(UpdateJob.class);
    
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        System.out.println("UpdateJob ran successfully...");
    }
}
