/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.jenkinsapi;

import com.devspecops.airdrop.models.JenkinsBuild;
import com.devspecops.airdrop.models.JenkinsBuildSummary;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author testep
 */
public class JenkinsUtil {
    private static final String JENKINS_URL = "http://jenkins.rd.rcg.local:8080/";
    private static final String JENKINS_BASE_PATH = "api/xml";
    private static final String JENKINS_BUILD = "job/%s/api/xml";
    private static final String JENKINS_LAST_BUILD = "job/%s/lastBuild/api/xml";
    private static final String JENKINS_LAST_SUCCESSFUL_BUILD = "job/%s/lastSuccessfulBuild/api/xml";
    private static final String JENKINS_LAST_UNSUCCESSFUL_BUILD = "job/%s/lastUnsuccessfulBuild/api/xml";
    private static final String JENKINS_LAST_STABLE_BUILD = "job/%s/lastStableBuild/api/xml";
    private static final String JENKINS_LAST_COMPLETED_BUILD = "job/%s/lastCompletedBuild/api/xml";
    private static final String JENKINS_LAST_FAILED_BUILD = "job/%s/lastFailedBuild/api/xml";
    private static final String JENKINS_SINGLE_BUILD = "job/%s/%s/api/xml";
    
    private URL apiUrl;
    private Document apiDom;
    
    public JenkinsUtil() {
        
    }
    
    public JenkinsBuild getJenkinsBuild(String projectName, String buildNumber) {
        JenkinsBuild jb = new JenkinsBuild();
        
        String callUrl = JENKINS_URL + String.format(JENKINS_SINGLE_BUILD, projectName, buildNumber);
        
        try {
            apiUrl = new URL(callUrl);
            
            apiDom = new SAXReader().read(apiUrl);
            
            List<Element> actionElements = apiDom.getRootElement().elements("action");
            
            Element isBuilding = apiDom.getRootElement().element("building");
            jb.setBuildNumber(apiDom.getRootElement().element("id").getText());
            jb.setBuildResult(apiDom.getRootElement().element("result").getText());
            jb.setBuildDate(apiDom.getRootElement().element("timestamp").getText());
            jb.setBuildUrl(apiDom.getRootElement().element("url").getText());
        } catch(MalformedURLException | DocumentException ex) {
            System.out.println("Exception Caught:");
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
        }
        
        return jb;
    }
    
    public List<JenkinsBuildSummary> getBuildSummaryList() {
        List<JenkinsBuildSummary> buildList = new ArrayList<>();
        
        //buildList
        
        return buildList;
    }
    
    public void getLastBuild(String jobName) {
        
    }
    
    public void getLastSuccessfulBuild(String jobName) {
        
    }
    
    public void getLastUnsuccessfulBuild(String jobName) {
        
    }
    
    public void getLastStableBuild(String jobName) {
        
    }
    
    public void getLastCompletedBuild(String jobName) {
        
    }
    
    public void getLastFailedBuild(String jobName) {
        
    }
}
