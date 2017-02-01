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
    
    private String deploymentsDbConnection = "jdbc:postgresql://localhost:5432/buster";
    private String deploymentsDbUser = "postgres";
    private String deploymentsDbAuth = "i4m4pldg";
    
    private String updateDeploymentEnvQuery = "UPDATE build" 
            + " SET build_state = ?"
            + " WHERE build_id = ?";
    private PreparedStatement updateDeploymentStmt = null;
    
    private PreparedStatement incrementBuildStateStmt = null;
    
    private String incrementBuildStateQuery = "UPDATE build SET build_state = ? WHERE build_id = ?";
    
    private String selectDeployments = "SELECT * FROM build ORDER BY build_date DESC";
    
    private PreparedStatement selectDeploymentsStmt = null;
    
    private String updateDeploymentDateQuery = "UPDATE build"
            + " SET build_date = ?"
            + " WHERE build_id = ?";
    private PreparedStatement updateDeploymentDateStmt = null;
    
    private String updateDeploymentApproverQuery = "UPDATE build"
            + " SET ";
    private PreparedStatement updateDeploymentApproverStmt = null;
    
    private ResultSet rs = null;
    
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
    
    public void updateNextScheduledDeploymentDate(String buildNumber, String deploymentDate) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            Date date = df.parse(deploymentDate);
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            
            conn.setAutoCommit(false);
            
            updateDeploymentDateStmt = conn.prepareStatement(updateDeploymentDateQuery);
            updateDeploymentDateStmt.setTimestamp(1, ts);
        } catch(ParseException | SQLException e) {
            
        }
    }
    
    public void incrementBuildState(int buildNumber, int newState) {
        try {
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            incrementBuildStateStmt = conn.prepareStatement(incrementBuildStateQuery);
            incrementBuildStateStmt.setInt(1, newState);
            incrementBuildStateStmt.setInt(2, buildNumber);
            incrementBuildStateStmt.executeUpdate();
            
            conn.commit();
        } catch(SQLException se2) {
            System.out.println("There was an error rolling back changes...");
            System.out.println("There was an error updating a deployment, rolling back changes:");
            System.out.println("Build Number: " + buildNumber);
            System.out.println("New State: " + newState);
            System.out.println(se2.getMessage());
        } finally {
            try {
                if(null != updateDeploymentStmt) {
                    incrementBuildStateStmt.close();
                }
                
                if(null != conn) {
                    conn.close();
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
    }
    
    public List<BusterBuild> getDeployments() {
        
        List<BusterBuild> deploymentList = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(deploymentsDbConnection,
                    deploymentsDbUser, deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            selectDeploymentsStmt = conn.prepareStatement(selectDeployments);
            ResultSet deploymentResults = selectDeploymentsStmt.executeQuery();
            
            while(deploymentResults.next()) {
                BusterBuild bb = new BusterBuild(
                                        deploymentResults.getInt("build_id"),
                                        deploymentResults.getInt("build_config_id"),
                                        deploymentResults.getString("build_name"),
                                        deploymentResults.getInt("build_status"),
                                        deploymentResults.getInt("build_state"),
                                        deploymentResults.getString("build_version"),
                                        deploymentResults.getDate("build_date"),
                                        deploymentResults.getDate("next_deployment_date"));
                
                deploymentList.add(bb);
            }
        } catch(SQLException | NullPointerException | ClassNotFoundException se) {
            System.out.println("There was an error selecting deployments:");
            System.out.println("Cause: " + se.getCause());
            System.out.println("Stacktrace:");
            for(int i = 0; i < se.getStackTrace().length; i++) {
                System.out.println(se.getStackTrace()[i]);
            }
            
        }
        
        return deploymentList;
    }
    
    public void createDeployment(String buildNumber, String deploymentEnv, String deploymentDate) {
        
    }
}
