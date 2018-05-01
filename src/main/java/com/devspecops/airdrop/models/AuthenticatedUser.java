/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author testep
 */
public class AuthenticatedUser {
    private boolean isUserAuthenticated;
    private String userEmail;
    private String userName;
    private List<String> userGroups;
    
    public AuthenticatedUser() {
        
    }
    
    public boolean getIsUserAuthenticated() {
        return this.isUserAuthenticated;
    }
    
    public void setIsUserAuthenticated(boolean auth) {
        this.isUserAuthenticated = auth;
    }
    
    public String getUserEmail() {
        if(null == userEmail)
            return "";
        
        return this.userEmail;
    }
    
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    
    public String getUserName() {
        if(null == userName)
            return "";
        
        return this.userName;
    }
    
    public void setUserName(String name) {
        this.userName = name;
    }
    
    public List<String> getUserGroups() {
        if(null == userGroups)
            userGroups = new ArrayList<>();
        
        return this.userGroups;
    }
    
    public void setUserGroups(List<String> groups) {
        this.userGroups = groups;
    }
}
