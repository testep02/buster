/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author testep
 */
public class EnvBackup {
    public static final String BACKUP_SOURCE_SCHEDULER = "backupScheduler";
    public static final String BACKUP_SOURCE_MANUAL = "backupManual";
    
    private int backupId;
    private Date backupDate;
    private String backupEnv;
    private int backupSchedulerId;
    private String backupSource;
    private List<BackupSnapshot> snapshots;

    /**
     * @return the backupId
     */
    public int getBackupId() {
        return backupId;
    }

    /**
     * @param backupId the backupId to set
     */
    public void setBackupId(int backupId) {
        this.backupId = backupId;
    }

    /**
     * @return the backupDate
     */
    public Date getBackupDate() {
        return backupDate;
    }

    /**
     * @param backupDate the backupDate to set
     */
    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    /**
     * @return the backupEnv
     */
    public String getBackupEnv() {
        return backupEnv;
    }

    /**
     * @param backupEnv the backupEnv to set
     */
    public void setBackupEnv(String backupEnv) {
        this.backupEnv = backupEnv;
    }

    /**
     * @return the backupSchedulerId
     */
    public int getBackupSchedulerId() {
        return backupSchedulerId;
    }

    /**
     * @param backupSchedulerId the backupSchedulerId to set
     */
    public void setBackupSchedulerId(int backupSchedulerId) {
        this.backupSchedulerId = backupSchedulerId;
    }

    /**
     * @return the backupSource
     */
    public String getBackupSource() {
        return backupSource;
    }

    /**
     * @param backupSource the backupSource to set
     */
    public void setBackupSource(String backupSource) {
        this.backupSource = backupSource;
    }

    /**
     * @return the snapshots
     */
    public List<BackupSnapshot> getSnapshots() {
        return snapshots;
    }

    /**
     * @param snapshots the snapshots to set
     */
    public void setSnapshots(List<BackupSnapshot> snapshots) {
        this.snapshots = snapshots;
    }
    
}
