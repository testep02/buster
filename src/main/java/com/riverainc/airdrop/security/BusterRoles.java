/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.security;

import java.util.List;

/**
 *
 * @author testep
 */
public class BusterRoles {
    
    // Basic Buster Roles
    public static final String E6_BUSTER_USER = "E6_BUSTER_USER";
    public static final String E6_BUSTER_ADMIN = "E6_BUSTER_ADMIN";
    
    // Artifact Approval and Rejection Roles
    // Allows a user to approve or reject artifacts that are deployed
    // to each environment.
    public static final String E6_ARTIFACT_AUDITOR_TEST = "E6_ARTIFACT_AUDITOR_TEST";
    public static final String E6_ARTIFACT_AUDITOR_QA = "E6_ARTIFACT_AUDITOR_QA";
    public static final String E6_ARTIFACT_AUDITOR_ALPHA = "E6_ARTIFACT_AUDITOR_ALPHA";
    public static final String E6_ARTIFACT_AUDITOR_DEMO = "E6_ARTIFACT_AUDITOR_DEMO";
    
    // Deployment Scheduling Roles
    // Allows a user to schedule the date when a set of artifacts should be
    // promoted to the next environment.
    public static final String E6_DEPLOY_AUDITOR_QA = "E6_DEPLOY_AUDITOR_QA";
    public static final String E6_DEPLOY_AUDITOR_ALPHA = "E6_DEPLOY_AUDITOR_ALPHA";
    public static final String E6_DEPLOY_AUDITOR_DEMO = "E6_DEPLOY_AUDITOR_DEMO";
    
    // AWS Backup Manager Roles
    // Allows a user to manage the backups (snapshots) that we create nightly.
    // The user can delete old backups.
    public static final String E6_AWS_BACKUP_MANAGER = "E6_BACKUP_MANAGER";
    public static final String E6_AWS_SCHEDULE_MANAGER = "E6_AWS_SCHEDULE_MANAGER";
    
    public static boolean isUserInRole(int currentState, List<String> roles) {
        switch(currentState){
            case 0:
                if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_TEST) 
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
            
            case 1:
                if(roles.contains(BusterRoles.E6_DEPLOY_AUDITOR_QA) 
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
                
            case 3:
                if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_QA) 
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
                
            case 4:
                if(roles.contains(BusterRoles.E6_DEPLOY_AUDITOR_ALPHA) 
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
                
            case 6:
                if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_ALPHA) 
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
                
            case 7:
                if(roles.contains(BusterRoles.E6_DEPLOY_AUDITOR_DEMO) 
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
                
            case 99:
                if(roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_DEMO) 
                        || roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_ALPHA)
                        || roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_QA)
                        || roles.contains(BusterRoles.E6_ARTIFACT_AUDITOR_TEST)
                        || roles.contains(BusterRoles.E6_BUSTER_ADMIN))
                    return true;
                else
                    return false;
            
            default:
                return false;
        }
    }
}
