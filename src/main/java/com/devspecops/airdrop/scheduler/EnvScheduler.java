/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.scheduler;

import com.devspecops.airdrop.scheduler.jobs.StartEnvJob;
import com.devspecops.airdrop.scheduler.jobs.StopEnvJob;
import java.util.ArrayList;
import java.util.List;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author testep
 */
public class EnvScheduler {
    private static EnvScheduler instance;
    private Scheduler sch;
    
    private EnvScheduler() {}
    
    public static synchronized EnvScheduler getInstance() {
        if(instance == null)
            instance = new EnvScheduler();
        
        return instance;
    }
    
    public void scheduleEnvStart(String envName, String cronSchedule) {
        try{
            JobDetail envStartJob = JobBuilder.newJob(StartEnvJob.class)
                    .withIdentity("startEnvJob", "startEnvGroup").build();
            
            
            
            envStartJob.getJobDataMap().put(StartEnvJob.ENV_NAME, envName);
            
            Trigger jobTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInHours(10).repeatForever()).build();
            
            SchedulerFactory schFactory = new StdSchedulerFactory();
            sch = schFactory.getScheduler();
            
            sch.start();
            sch.scheduleJob(envStartJob, jobTrigger);
            
        } catch(SchedulerException se) {
            System.out.println("There was an error starting this job:");
            System.out.println(se.getMessage());
        }
    }
    
    public void scheduleEnvStop(String envName, String cronSchedule) {
        try{
            JobDetail envStopJob = JobBuilder.newJob(StopEnvJob.class)
                    .withIdentity("stopEnvJob", "stopEnvGroup").build();
            
            envStopJob.getJobDataMap().put(StartEnvJob.ENV_NAME, envName);
            
            Trigger jobTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInHours(10).repeatForever()).build();
            
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            
            sch.start();
            sch.scheduleJob(envStopJob, jobTrigger);
        } catch(SchedulerException se) {
            System.out.println("There was an error starting this job:");
            System.out.println(se.getMessage());
        }
    }
    
    public List<String> getJobs() {
        List<String> jobs = new ArrayList<>();
        
        
        
        return jobs;
    }
}
