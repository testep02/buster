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
public class BusterBuildConfig {
    private int buildConfigId;
    private String buildName;
    private String cronSchedule;
    private String command;
    private String buildRepo;
    private String buildBranch;

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
     * @return the cronSchedule
     */
    public String getCronSchedule() {
        return cronSchedule;
    }

    /**
     * @param cronSchedule the cronSchedule to set
     */
    public void setCronSchedule(String cronSchedule) {
        this.cronSchedule = cronSchedule;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the buildRepo
     */
    public String getBuildRepo() {
        return buildRepo;
    }

    /**
     * @param buildRepo the buildRepo to set
     */
    public void setBuildRepo(String buildRepo) {
        this.buildRepo = buildRepo;
    }

    /**
     * @return the buildBranch
     */
    public String getBuildBranch() {
        return buildBranch;
    }

    /**
     * @param buildBranch the buildBranch to set
     */
    public void setBuildBranch(String buildBranch) {
        this.buildBranch = buildBranch;
    }
}
