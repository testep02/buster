/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.models;

/**
 *
 * @author testep
 */
public class BackupSnapshot {
    private String snapshotId;
    private String backupInstanceId;
    private String backupVolumeId;

    /**
     * @return the snapshotId
     */
    public String getSnapshotId() {
        return snapshotId;
    }

    /**
     * @param snapshotId the snapshotId to set
     */
    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * @return the backupInstanceId
     */
    public String getBackupInstanceId() {
        return backupInstanceId;
    }

    /**
     * @param backupInstanceId the backupInstanceId to set
     */
    public void setBackupInstanceId(String backupInstanceId) {
        this.backupInstanceId = backupInstanceId;
    }

    /**
     * @return the backupVolumeId
     */
    public String getBackupVolumeId() {
        return backupVolumeId;
    }

    /**
     * @param backupVolumeId the backupVolumeId to set
     */
    public void setBackupVolumeId(String backupVolumeId) {
        this.backupVolumeId = backupVolumeId;
    }
}
