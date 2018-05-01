/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.commands;

import com.devspecops.vsts.VstsCollection;
import com.devspecops.vsts.VstsHttp;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author EstepTravis
 */
public class VstsCommand {
    public List<String> getProjects() {
        ArrayList<String> projects = new ArrayList<>();         
        
        return projects;
    }
    
    public void testVsts() {
        /*int statusCode = 0;
        
        HttpGet request = new HttpGet("https://getbeyond.visualstudio.com/DefaultCollection/_apis/projects");
        String auth = ":oggrxnghhk5xqfap3ioqlgvfitzx54ze7ukledugzkvitijr6cxa";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        
        HttpClient client = HttpClientBuilder.create().build();
        
        try {
            HttpResponse response = client.execute(request);
            
            statusCode = response.getStatusLine().getStatusCode();
            
            HttpEntity entity = response.getEntity();
            
            if(null != entity) {
                InputStream ios = entity.getContent();
                
                String content = IOUtils.toString(ios);
                
                byte[] jsonData = content.getBytes();
                
                ObjectMapper objMapper = new ObjectMapper();
                
                VstsCollection collection = objMapper.readValue(jsonData, VstsCollection.class);
                
                if(null != collection) {
                    System.out.println("----------- Colletion -----------");
                    System.out.println(collection.toString());
                }
            }
            
            
            System.out.println("Response_code: " + statusCode);
            System.out.println(response.getEntity().getContent());
        } catch (IOException ioe) {
            System.out.println("Error getting projects: ");
            System.out.println(ioe.getMessage());
        }*/
        
        VstsHttp http = new VstsHttp();
        VstsCollection collection = http.getCollection();
        
        if(null != collection) {
            System.out.println(collection.toString());
        }
    }
}
