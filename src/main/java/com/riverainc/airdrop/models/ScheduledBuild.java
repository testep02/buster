/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.models;

import java.util.Date;

/**
 *
 * @author testep
 */
public class ScheduledBuild {
    private int buildId;
    private String buildName;
    private Date deploymentDate;
    private String deploymentEnv;
    private String buildVersion;
    private String buildDate;
    private String currentState;

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
     * @return the buildName
     */
    public String getBuildName() {
        return buildName;
    }

    /**
     * @param buildName the buildName to set
     */
    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    /**
     * @return the deploymentDate
     */
    public Date getDeploymentDate() {
        return deploymentDate;
    }

    /**
     * @param deploymentDate the deploymentDate to set
     */
    public void setDeploymentDate(Date deploymentDate) {
        this.deploymentDate = deploymentDate;
    }

    /**
     * @return the deploymentEnv
     */
    public String getDeploymentEnv() {
        return deploymentEnv;
    }

    /**
     * @param deploymentEnv the deploymentEnv to set
     */
    public void setDeploymentEnv(String deploymentEnv) {
        this.deploymentEnv = deploymentEnv;
    }

    /**
     * @return the buildVersion
     */
    public String getBuildVersion() {
        return buildVersion;
    }

    /**
     * @param buildVersion the buildVersion to set
     */
    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    /**
     * @return the buildDate
     */
    public String getBuildDate() {
        return buildDate;
    }

    /**
     * @param buildDate the buildDate to set
     */
    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    /**
     * @return the currentState
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * @param currentState the currentState to set
     */
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
