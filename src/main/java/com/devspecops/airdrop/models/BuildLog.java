/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.models;

import java.util.Date;

/**
 *
 * @author testep
 */
public class BuildLog {
    private int buildLogsId;
    private int buildId;
    private String logUid;
    private Date logDate; // Change to LocalDate in next release
    private int buildPreviousState;
    private int buildNewState;

    public BuildLog(int logId, int buildId, String logUid, Date logDate,
            int previousState, int newState) {
        this.buildLogsId = logId;
        this.buildId = buildId;
        this.logUid = logUid;
        this.logDate = logDate;
        this.buildPreviousState = previousState;
        this.buildNewState = newState;
    }
    
    /**
     * @return the buildLogsId
     */
    public int getBuildLogsId() {
        return buildLogsId;
    }

    /**
     * @param buildLogsId the buildLogsId to set
     */
    public void setBuildLogsId(int buildLogsId) {
        this.buildLogsId = buildLogsId;
    }

    /**
     * @return the buildId
     */
    public int getBuildId() {
        return buildId;
    }

    /**
     * @param buildId the buildId to set
     */
    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    /**
     * @return the logUid
     */
    public String getLogUid() {
        return logUid;
    }

    /**
     * @param logUid the logUid to set
     */
    public void setLogUid(String logUid) {
        this.logUid = logUid;
    }

    /**
     * @return the logDate
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * @param logDate the logDate to set
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * @return the buildPreviousState
     */
    public int getBuildPreviousState() {
        return buildPreviousState;
    }

    /**
     * @param buildPreviousState the buildPreviousState to set
     */
    public void setBuildPreviousState(int buildPreviousState) {
        this.buildPreviousState = buildPreviousState;
    }

    /**
     * @return the buildNewState
     */
    public int getBuildNewState() {
        return buildNewState;
    }

    /**
     * @param buildNewState the buildNewState to set
     */
    public void setBuildNewState(int buildNewState) {
        this.buildNewState = buildNewState;
    }
}
