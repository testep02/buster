/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.scheduler;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author testep
 */
public class BusterScheduler {
    private static BusterScheduler instance;
    
    private Scheduler sch;
    
    private BusterScheduler() {}
    
    public static synchronized BusterScheduler getInstance() {
        if(instance == null)
            instance = new BusterScheduler();
        
        return instance;
    }
    
    public void startSchedules() {
        try {
            SchedulerFactory schFactory = new StdSchedulerFactory();
            sch = schFactory.getScheduler();

            sch.start();
        } catch(SchedulerException se) {
            System.out.println("There was an error starting the scheduler:");
            System.out.println(se.getMessage());
        }
    }
    
    public void addJob(String jobName, String jobGroup, JobDetail job, Trigger jobTrigger) {
        try {
            sch.scheduleJob(job, jobTrigger);
        } catch(SchedulerException se) {
            System.out.println("There was an error when trying to schedule a job: ");
            System.out.println("Job Name: " + jobName);
            System.out.println("Job Group: " + jobGroup);
            System.out.println(se.getMessage());
        }
    }
    
    public void removeJob(String jobName, String jobGroup, JobKey jobKey) {
        try {
            sch.deleteJob(jobKey);
        } catch(SchedulerException se) {
            System.out.println("There was an error trying to remove a job: ");
            System.out.println("Job Name: " + jobName);
            System.out.println("Job Group: " + jobGroup);
            System.out.println(se.getMessage());
        }
    }
    
    public void pauseJob(String jobName, String jobGroup, JobKey jobKey) {
        try {
            sch.pauseJob(jobKey);
        } catch(SchedulerException se) {
            System.out.println("There was an error trying to pause a job: ");
            System.out.println("Job Name: " + jobName);
            System.out.println("Job Group: " + jobGroup);
            System.out.println(se.getMessage());
        }
    }
    
    public void resumeJob(String jobName, String jobGroup, JobKey jobKey) {
        try {
            sch.resumeJob(jobKey);
        } catch(SchedulerException se) {
            System.out.println("There was an error trying to resume a job: ");
            System.out.println("Job Name: " + jobName);
            System.out.println("Job Group: " + jobGroup);
            System.out.println(se.getMessage());
        }
    }
    
    public void getJobs() {
        
    }
}
