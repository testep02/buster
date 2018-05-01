/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.models;

/**
 *
 * @author testep
 */
public class JenkinsBuildSummary {
    private String buildName = "";
    private String buildUrl = "";
    private String buildColor = "";

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
     * @return the buildUrl
     */
    public String getBuildUrl() {
        return buildUrl;
    }

    /**
     * @param buildUrl the buildUrl to set
     */
    public void setBuildUrl(String buildUrl) {
        this.buildUrl = buildUrl;
    }

    /**
     * @return the buildColor
     */
    public String getBuildColor() {
        return buildColor;
    }

    /**
     * @param buildColor the buildColor to set
     */
    public void setBuildColor(String buildColor) {
        this.buildColor = buildColor;
    }
}
