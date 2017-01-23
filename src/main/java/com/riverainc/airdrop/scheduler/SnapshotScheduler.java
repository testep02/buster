/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.scheduler;

import com.riverainc.airdrop.scheduler.jobs.SnapshotJob;
import com.riverainc.airdrop.scheduler.jobs.UpdateJob;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 *
 * @author testep
 */
public class SnapshotScheduler {
    private static SnapshotScheduler instance;
    
    private Scheduler sch;
    
    private SnapshotScheduler(){}
    
    public static synchronized SnapshotScheduler getInstance() {
        if(instance == null) {
            instance = new SnapshotScheduler();
        }
        
        return instance;
    }
    
    public void schedule() {
        try {
            Class<SnapshotJob> snapshotJob = SnapshotJob.class;
            Class<UpdateJob> updateJob = UpdateJob.class;
            
            JobDetail job = JobBuilder.newJob(updateJob).withIdentity("updateJob", "updateJobGrp").build();
            JobDetail job2 = JobBuilder.newJob(snapshotJob).withIdentity("snapshotJob", "snapshotJobGrp").build();
            
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10).repeatForever())
                    .build();
            
            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(5).repeatForever())
                    .build();
            
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            
            sch.start();
            sch.scheduleJob(job, trigger);
            sch.scheduleJob(job2, trigger2);
            
            Set<JobKey> updateJobs = sch.getJobKeys(GroupMatcher.jobGroupEquals("updateJobGrp"));
            
            Set<JobKey> jobKeys = sch.getJobKeys(GroupMatcher.jobGroupEquals(Scheduler.DEFAULT_GROUP));
            
            jobKeys.stream().forEach((i) -> {
                System.out.println("Job Name: " + i.getName());
                System.out.println("Job Group: " + i.getGroup());
            });
            
        } catch(SchedulerException se) {
            System.out.println("There was an error scheduling the job: " + se.getMessage());
        }
    }
    
    public void reschedule(String jobName, String jobGroup, String cronString) {
         
   }
    
    public List<JobKey> listJobs() {
        List<JobKey> jobs = new ArrayList<>();
        
        try {
            jobs.addAll(sch.getJobKeys(GroupMatcher.anyJobGroup()));
        } catch(SchedulerException se) {
            System.out.println("There was an error getting the jobs from this scheduler: ");
            System.out.println(se.getMessage());
        }
        
        return jobs;
    }
    
    public Map<String, String> getJobs() {
        HashMap<String, String> jobs = new HashMap<>();
        
        try {
            Set<JobKey> jobKeys = sch.getJobKeys(GroupMatcher.anyJobGroup());
            
            jobKeys.stream().forEach((key) -> {
                jobs.put("jobName", key.getName());
            });
        } catch(SchedulerException se) {
            System.out.println("Error getting jobs from scheduler: ");
            System.out.println(se.getStackTrace());
        }
        
        return jobs;
    }
}
