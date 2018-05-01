/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.vsts;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author EstepTravis
 */
public class VstsHttp {
    @Autowired
    private VstsConfig vstsConfig;
    
    private String vstsCollectionUrl;
    
    public VstsHttp() {
        vstsConfig = new VstsConfig();
    }
    
    public VstsCollection getCollection() {
        VstsCollection collection = null;
        try {
            ObjectMapper objMapper = new ObjectMapper();
            collection = objMapper.readValue(
                            getVstsProjectsJson(), 
                            VstsCollection.class);
        } catch (IOException | NullPointerException ioe) {
            System.out.println("There was an error getting collection data:");
            System.out.println(ioe.getMessage());
        }
        
        return collection;
    }

    public /*VstsRepositoryCollection*/ void getRepoCollection() {

    }

    private byte[] getVstsProjectsJson() {
        int statusCode = 0;
        byte[] jsonData = null;

        HttpGet request = new HttpGet(getProjectsUrl());
        String authHeader = getAuthHeader();
        
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        
        HttpClient client = HttpClientBuilder.create().build();
        
        try {
            HttpResponse response = client.execute(request);
            statusCode = response.getStatusLine().getStatusCode();
            
            if(statusCode == 200) {
                HttpEntity entity = response.getEntity();
                
                if(null != entity) {
                    InputStream ios = entity.getContent();
                    String content = IOUtils.toString(ios);
                    jsonData = content.getBytes();
                }
            }
        } catch( IOException ioe) {
            System.out.println("There was an error getting VSTS Projects.");
            System.out.println(ioe.getMessage());
        }
        return jsonData;
    }
    
    private String getProjectsUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("https://")
                .append(vstsConfig.getOrgName())
                .append(".visualstudio.com/")
                .append(vstsConfig.getCollection())
                .append("/_apis/projects");
        
        System.out.println("Collection URL: ");
        System.out.println(sb.toString());
        
        return sb.toString();
    }
    
    private String getAuthHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(vstsConfig.getUsername())
                .append(":")
                .append(vstsConfig.getAuthToken());
        
        byte[] encodedAuth = Base64.encodeBase64(
                sb.toString().getBytes(
                        Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        
        return authHeader;
    }
}


