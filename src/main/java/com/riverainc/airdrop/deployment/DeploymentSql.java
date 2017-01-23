/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.deployment;

import com.riverainc.airdrop.models.BusterBuild;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author testep
 */
public class DeploymentSql {
    
    private String updateDeploymentEnvQuery = "UPDATE build" 
            + " SET state = ?"
            + " WHERE build_id = ?";
    private PreparedStatement updateDeploymentStmt = null;
    
    private String selectDeployments = "SELECT * FROM build";
    private PreparedStatement selectDeploymentsStmt = null;
    
    private String updateDeploymentDateQuery = "UPDATE build"
            + " SET build_date = ?"
            + " WHERE build_id = ?";
    private PreparedStatement updateDeploymentDateStmt = null;
    
    private String updateDeploymentApproverQuery = "UPDATE build"
            + " SET ";
    private PreparedStatement updateDeploymentApproverStmt = null;
    
    private ResultSet rs = null;
    
    private String deploymentsDbConnection = "jdbc:postgresql://localhost:5432/buster";
    private String deploymentsDbUser = "postgres";
    private String deploymentsDbAuth = "i4m4pldg";
    
    private String dateFormat = "MM/dd/yyyy";
    
    private Connection conn = null;
    
    public DeploymentSql() {
        
    }
    
    public void updateDeploymentEnv(String buildNumber, String deploymentEnv) {
        
        
        try {
            //DateFormat df = new SimpleDateFormat(dateFormat);
            //Date date = df.parse(deploymentDate);
            //long time = date.getTime();
            //Timestamp ts = new Timestamp(time);
            
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            updateDeploymentStmt = conn.prepareStatement(updateDeploymentEnvQuery);
            
            updateDeploymentStmt.setString(1, deploymentEnv);
            //updateDeploymentStmt.setTimestamp(2, ts);
            updateDeploymentStmt.setInt(2, Integer.parseInt(buildNumber));
            
            updateDeploymentStmt.executeUpdate();
            
            conn.commit();
            
        } catch(SQLException se) {
            try {
                conn.rollback();
            } catch(SQLException se2) {
                System.out.println("There was an error rolling back changes...");
            }
            
            System.out.println("There was an error updating a deployment, rolling back changes:");
            System.out.println("Build Number: " + buildNumber);
            System.out.println("Build Env: " + deploymentEnv);
            System.out.println(se.getMessage());
        } finally {
            try {
                if(null != updateDeploymentStmt) {
                    updateDeploymentStmt.close();
                }
                
                if(null != conn) {
                    conn.close();
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
    }
    
    public void updateDeploymentDate(String buildNumber, String deploymentDate) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            Date date = df.parse(deploymentDate);
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            
            conn = DriverManager.getConnection(deploymentsDbConnection,
                    deploymentsDbUser, deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            updateDeploymentDateStmt = conn.prepareStatement(updateDeploymentDateQuery);
            updateDeploymentDateStmt.setTimestamp(1, ts);
        } catch(ParseException | SQLException se) {
            
        }
    }
    
    public void updateDeploymentApprover(String buildNumber, String approver) {
        /*try {
            
        } catch(SQLException se) {
            
        }*/
    }
    
    public List<BusterBuild> getDeployments() {
        List<BusterBuild> buildList = new ArrayList<>();
        
        try {
            selectDeploymentsStmt = conn.prepareStatement(selectDeployments);
            updateDeploymentStmt.executeQuery();
        } catch(SQLException se) {
            System.out.println("There was an error selecting deployments:");
            System.out.println(se.getMessage());
        }
        
        return buildList;
    }
    
    public void createDeployment(String buildNumber, String deploymentEnv, String deploymentDate) {
        
    }
}
