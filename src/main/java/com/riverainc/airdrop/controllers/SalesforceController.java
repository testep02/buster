/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author EstepTravis
 */

@Controller
public class SalesforceController {
    public int getSalesforceBranches() {
        int statusCode = 0;
        
        HttpGet request = new HttpGet("https://getbeyond.visualstudio.com/DefaultCollection/_apis/projects");
        String auth = "oggrxnghhk5xqfap3ioqlgvfitzx54ze7ukledugzkvitijr6cxa";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        
        HttpClient client = HttpClientBuilder.create().build();
        
        try {
            HttpResponse response = client.execute(request);
            
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException ioe) {
            
        }
        return statusCode;
    }
}
