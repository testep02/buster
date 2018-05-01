/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.vsts;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;

/**
 *
 * @author EstepTravis
 */
public class VstsRepository {
    public /*VstsCollection[]*/ void getCollection() {
        int httpStatusCode = 0;
        String auth = ":oggrxnghhk5xqfap3ioqlgvfitzx54ze7ukledugzkvitijr6cxa";
        
        HttpGet vstsRequest = new HttpGet("https://getbeyond.visualstudio.com/DefaultCollection/_apis/projects");
        
    }
}
