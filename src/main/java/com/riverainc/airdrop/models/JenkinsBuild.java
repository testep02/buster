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
public class JenkinsBuild {
    private String buildNumber = "";
    private String buildDate = "";
    private String projectName = "";
    private String buildBranch = "";
    private String buildSHA1 = "";
    private String buildResult = "";
    private String buildUrl = "";
    private String buildCommitId = "";
    private String buildCommitComment = "";
    private String buildCommitAuthor = "";
    private String buildCommitAuthorUrl = "";

    /**
     * @return the buildNumber
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     * @param buildNumber the buildNumber to set
     */
    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
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

    /**
     * @return the buildSHA1
     */
    public String getBuildSHA1() {
        return buildSHA1;
    }

    /**
     * @param buildSHA1 the buildSHA1 to set
     */
    public void setBuildSHA1(String buildSHA1) {
        this.buildSHA1 = buildSHA1;
    }

    /**
     * @return the buildResult
     */
    public String getBuildResult() {
        return buildResult;
    }

    /**
     * @param buildResult the buildResult to set
     */
    public void setBuildResult(String buildResult) {
        this.buildResult = buildResult;
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
     * @return the buildCommitId
     */
    public String getBuildCommitId() {
        return buildCommitId;
    }

    /**
     * @param buildCommitId the buildCommitId to set
     */
    public void setBuildCommitId(String buildCommitId) {
        this.buildCommitId = buildCommitId;
    }

    /**
     * @return the buildCommitComment
     */
    public String getBuildCommitComment() {
        return buildCommitComment;
    }

    /**
     * @param buildCommitComment the buildCommitComment to set
     */
    public void setBuildCommitComment(String buildCommitComment) {
        this.buildCommitComment = buildCommitComment;
    }

    /**
     * @return the buildCommitAuthor
     */
    public String getBuildCommitAuthor() {
        return buildCommitAuthor;
    }

    /**
     * @param buildCommitAuthor the buildCommitAuthor to set
     */
    public void setBuildCommitAuthor(String buildCommitAuthor) {
        this.buildCommitAuthor = buildCommitAuthor;
    }

    /**
     * @return the buildCommitAuthorUrl
     */
    public String getBuildCommitAuthorUrl() {
        return buildCommitAuthorUrl;
    }

    /**
     * @param buildCommitAuthorUrl the buildCommitAuthorUrl to set
     */
    public void setBuildCommitAuthorUrl(String buildCommitAuthorUrl) {
        this.buildCommitAuthorUrl = buildCommitAuthorUrl;
    }
}
