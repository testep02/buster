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
    
    // TEMP DB CONNECTION SETTINGS. DO NOT CHANGE.
    private String deploymentsDbConnection = "jdbc:postgresql://localhost:5432/buster";
    private String deploymentsDbUser = "buster";
    private String deploymentsDbAuth = "i4m4pldg";
    
    // Prepared Statements and Queries
    private PreparedStatement incrementBuildStateStmt = null;
    private String incrementBuildStateQuery = "UPDATE build SET build_state = ? WHERE build_id = ?";
    
    private PreparedStatement insertApprovalLogStmt = null;
    private String insertApprovalLogQuery = "INSERT INTO build_approval_logs" 
            + " (build_id, build_approver_uid, build_approval_date)"
            + " VALUES (?, ?, ?)";
    
    private PreparedStatement selectDeploymentsStmt = null;
    private String selectDeployments = "SELECT * FROM build ORDER BY build_date DESC";
    
    private PreparedStatement selectBuildStmt = null;
    private String selectBuildQuery = "SELECT build_name, build_date, build_version, build_state FROM build " 
            + "WHERE build_id = ?";
    
    private ResultSet rs = null;
    
    private String dateFormat = "MM/dd/yyyy";
    
    private Connection conn = null;
    
    public DeploymentSql() {
        
    }
    
    public BusterBuild getBuildDetails(int buildId) {
        BusterBuild bb = new BusterBuild();
        
        try {
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            selectBuildStmt = conn.prepareStatement(selectBuildQuery);
            selectBuildStmt.setInt(1, buildId);
            ResultSet selectBuildResult = selectBuildStmt.executeQuery();
            
            if(selectBuildResult.next()) {
                bb.setBuildId(buildId);
                bb.setBuildName(selectBuildResult.getString("build_name"));
                bb.setBuildDate(selectBuildResult.getDate("build_date"));
                bb.setBuildVersion(selectBuildResult.getString("build_version"));
            }
            
            if(null != selectBuildStmt) {
                selectBuildStmt.close();
            }

            if(null != conn) {
                conn.close();
            }
        } catch(SQLException se) {
            System.out.println("There was an error querying for build...");
            System.out.println("Build Number: " + buildId);
            System.out.println(se.getMessage());
        }
        
        return bb;
    }
    
    public boolean incrementBuildState(int buildNumber, int newState) {
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
            return false;
        } finally {
            try {
                if(null != incrementBuildStateStmt) {
                    incrementBuildStateStmt.close();
                }
                
                if(null != conn) {
                    conn.close();
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
        
        return true;
    }
    
    public void insertBuildApprovalLog(String currentUser, int buildId, int currentState) {
        try {
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            
            conn.setAutoCommit(false);
            
            
            
            conn.commit();
        } catch(SQLException se2) {
            System.out.println("There was an error rolling back changes...");
            System.out.println("There was an error updating a deployment, rolling back changes:");
            System.out.println("Build Number: " + buildId);
            System.out.println(se2.getMessage());
        } finally {
            try {
                if(null != incrementBuildStateStmt) {
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
