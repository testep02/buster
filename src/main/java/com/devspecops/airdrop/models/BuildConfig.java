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
public class BuildConfig {
    private int buildConfigId;
    private String buildConfigName;
    private String buildConfigCron;
    private Date buildConfigCreationDate;
    private String buildConfigCreationUser;
    private int buildConfigEnabled;
    private String buildConfigEnv;

    /**
     * @return the buildConfigId
     */
    public int getBuildConfigId() {
        return buildConfigId;
    }

    /**
     * @param buildConfigId the buildConfigId to set
     */
    public void setBuildConfigId(int buildConfigId) {
        this.buildConfigId = buildConfigId;
    }

    /**
     * @return the buildConfigName
     */
    public String getBuildConfigName() {
        return buildConfigName;
    }

    /**
     * @param buildConfigName the buildConfigName to set
     */
    public void setBuildConfigName(String buildConfigName) {
        this.buildConfigName = buildConfigName;
    }

    /**
     * @return the buildConfigCron
     */
    public String getBuildConfigCron() {
        return buildConfigCron;
    }

    /**
     * @param buildConfigCron the buildConfigCron to set
     */
    public void setBuildConfigCron(String buildConfigCron) {
        this.buildConfigCron = buildConfigCron;
    }

    /**
     * @return the buildConfigCreationDate
     */
    public Date getBuildConfigCreationDate() {
        return buildConfigCreationDate;
    }

    /**
     * @param buildConfigCreationDate the buildConfigCreationDate to set
     */
    public void setBuildConfigCreationDate(Date buildConfigCreationDate) {
        this.buildConfigCreationDate = buildConfigCreationDate;
    }

    /**
     * @return the buildConfigCreationUser
     */
    public String getBuildConfigCreationUser() {
        return buildConfigCreationUser;
    }

    /**
     * @param buildConfigCreationUser the buildConfigCreationUser to set
     */
    public void setBuildConfigCreationUser(String buildConfigCreationUser) {
        this.buildConfigCreationUser = buildConfigCreationUser;
    }

    /**
     * @return the buildConfigEnabled
     */
    public int getBuildConfigEnabled() {
        return buildConfigEnabled;
    }

    /**
     * @param buildConfigEnabled the buildConfigEnabled to set
     */
    public void setBuildConfigEnabled(int buildConfigEnabled) {
        this.buildConfigEnabled = buildConfigEnabled;
    }

    /**
     * @return the buildConfigEnv
     */
    public String getBuildConfigEnv() {
        return buildConfigEnv;
    }

    /**
     * @param buildConfigEnv the buildConfigEnv to set
     */
    public void setBuildConfigEnv(String buildConfigEnv) {
        this.buildConfigEnv = buildConfigEnv;
    }
}
