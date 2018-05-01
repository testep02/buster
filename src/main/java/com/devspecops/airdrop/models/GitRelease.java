/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.models;

import java.util.Date;

/**
 *
 * @author EstepTravis
 */
public class GitRelease {
    private String projectName;
    private String releaseVersion;
    private String releaseUrl;
    private Date createdDate;
    private String releaseMessage;

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the releaseVersion
     */
    public String getReleaseVersion() {
        return releaseVersion;
    }

    /**
     * @param releaseVersion the releaseVersion to set
     */
    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the releaseMessage
     */
    public String getReleaseMessage() {
        return releaseMessage;
    }

    /**
     * @param releaseMessage the releaseMessage to set
     */
    public void setReleaseMessage(String releaseMessage) {
        this.releaseMessage = releaseMessage;
    }

    /**
     * @return the releaseUrl
     */
    public String getReleaseUrl() {
        return releaseUrl;
    }

    /**
     * @param releaseUrl the releaseUrl to set
     */
    public void setReleaseUrl(String releaseUrl) {
        this.releaseUrl = releaseUrl;
    }
    
}
