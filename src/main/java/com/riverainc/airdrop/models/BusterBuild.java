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
public class BusterBuild {
    private static final int BUILD_STATE_TEST = 0;
    private static final int BUILD_STATE_TEST_APPROVED = 1;
    private static final int BUILD_STATE_TEST_SCHEDULED = 2;
    private static final int BUILD_STATE_QA = 3;
    private static final int BUILD_STATE_QA_APPROVED = 4;
    private static final int BUILD_STATE_QA_SCHEDULED = 5;
    private static final int BUILD_STATE_ALPHA = 6;
    private static final int BUILD_STATE_ALPHA_GOLD = 7;
    private static final int BUILD_STATE_ALPHA_GOLD_SCHEDULED = 8;
    private static final int BUILD_STATE_DEMO_GOLD = 9;
    private static final int BUILD_STATE_REJECTED = 99;
    
    private int buildId;
    private int buildConfigId;
    private String buildName;
    private int buildStatus;
    private int buildState;
    private String buildVersion;

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
     * @return the buildStatus
     */
    public int getBuildStatus() {
        return buildStatus;
    }

    /**
     * @param buildStatus the buildStatus to set
     */
    public void setBuildStatus(int buildStatus) {
        this.buildStatus = buildStatus;
    }

    /**
     * @return the buildState
     */
    public int getBuildState() {
        return buildState;
    }

    /**
     * @param buildState the buildState to set
     */
    public void setBuildState(int buildState) {
        this.buildState = buildState;
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
}
