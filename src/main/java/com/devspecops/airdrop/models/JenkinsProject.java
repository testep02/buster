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
public class JenkinsProject {
    private String projectName = "";
    private String projectUrl = "";
    private boolean buildable = false;
    private String healthDescription = "";
    private String healthScore = "";
    private String color = "";
    private boolean inQueue = false;
    private boolean concurrentBuild = false;
    private boolean keepDependencies = false;
    private JenkinsBuild lastBuild = null;
    private JenkinsBuild lastCompletedBuild = null;
    private JenkinsBuild lastFailedBuild = null;
    private JenkinsBuild lastStableBuild = null;
    private JenkinsBuild lastSuccessfulBuild = null;
    private JenkinsBuild lastUnsuccessfulBuild = null;

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
     * @return the projectUrl
     */
    public String getProjectUrl() {
        return projectUrl;
    }

    /**
     * @param projectUrl the projectUrl to set
     */
    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    /**
     * @return the buildable
     */
    public boolean isBuildable() {
        return buildable;
    }

    /**
     * @param buildable the buildable to set
     */
    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }

    /**
     * @return the healthDescription
     */
    public String getHealthDescription() {
        return healthDescription;
    }

    /**
     * @param healthDescription the healthDescription to set
     */
    public void setHealthDescription(String healthDescription) {
        this.healthDescription = healthDescription;
    }

    /**
     * @return the healthScore
     */
    public String getHealthScore() {
        return healthScore;
    }

    /**
     * @param healthScore the healthScore to set
     */
    public void setHealthScore(String healthScore) {
        this.healthScore = healthScore;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the inQueue
     */
    public boolean isInQueue() {
        return inQueue;
    }

    /**
     * @param inQueue the inQueue to set
     */
    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

    /**
     * @return the concurrentBuild
     */
    public boolean isConcurrentBuild() {
        return concurrentBuild;
    }

    /**
     * @param concurrentBuild the concurrentBuild to set
     */
    public void setConcurrentBuild(boolean concurrentBuild) {
        this.concurrentBuild = concurrentBuild;
    }

    /**
     * @return the keepDependencies
     */
    public boolean isKeepDependencies() {
        return keepDependencies;
    }

    /**
     * @param keepDependencies the keepDependencies to set
     */
    public void setKeepDependencies(boolean keepDependencies) {
        this.keepDependencies = keepDependencies;
    }

    /**
     * @return the lastBuild
     */
    public JenkinsBuild getLastBuild() {
        return lastBuild;
    }

    /**
     * @param lastBuild the lastBuild to set
     */
    public void setLastBuild(JenkinsBuild lastBuild) {
        this.lastBuild = lastBuild;
    }

    /**
     * @return the lastCompletedBuild
     */
    public JenkinsBuild getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    /**
     * @param lastCompletedBuild the lastCompletedBuild to set
     */
    public void setLastCompletedBuild(JenkinsBuild lastCompletedBuild) {
        this.lastCompletedBuild = lastCompletedBuild;
    }

    /**
     * @return the lastFailedBuild
     */
    public JenkinsBuild getLastFailedBuild() {
        return lastFailedBuild;
    }

    /**
     * @param lastFailedBuild the lastFailedBuild to set
     */
    public void setLastFailedBuild(JenkinsBuild lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
    }

    /**
     * @return the lastStableBuild
     */
    public JenkinsBuild getLastStableBuild() {
        return lastStableBuild;
    }

    /**
     * @param lastStableBuild the lastStableBuild to set
     */
    public void setLastStableBuild(JenkinsBuild lastStableBuild) {
        this.lastStableBuild = lastStableBuild;
    }

    /**
     * @return the lastSuccessfulBuild
     */
    public JenkinsBuild getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    /**
     * @param lastSuccessfulBuild the lastSuccessfulBuild to set
     */
    public void setLastSuccessfulBuild(JenkinsBuild lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    /**
     * @return the lastUnsuccessfulBuild
     */
    public JenkinsBuild getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    /**
     * @param lastUnsuccessfulBuild the lastUnsuccessfulBuild to set
     */
    public void setLastUnsuccessfulBuild(JenkinsBuild lastUnsuccessfulBuild) {
        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
    }
}
