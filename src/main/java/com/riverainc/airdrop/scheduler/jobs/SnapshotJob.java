/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 *
 * @author testep
 */
public class SnapshotJob implements Job {
    public void execute(JobExecutionContext jExeCtx) {
        System.out.println("Snapshot job executed successfully...");
    }
}
