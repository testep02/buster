/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.deployment;

import com.riverainc.airdrop.models.BuildLog;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String deploymentsDbUser = "postgres";
    private String deploymentsDbAuth = "i4m4pldg";
    
    // Prepared Statements and Queries
    private PreparedStatement incrementBuildStateStmt = null;
    private String incrementBuildStateQuery = "UPDATE build SET build_state = ? WHERE build_id = ?";
    
    private PreparedStatement insertLogStmt = null;
    private String insertLogQuery = "INSERT INTO build_logs" 
            + " (build_id, log_uid, log_date, build_previous_state, build_new_state)"
            + " VALUES (?, ?, ?, ?, ?)";
    
    private PreparedStatement selectLogsStmt = null;
    private String selectLogsQuery = "SELECT * FROM build_logs WHERE build_id = ?";
    
    private PreparedStatement insertScheduledDeploymentStmt = null;
    private String insertScheduledDeploymentQuery = "INSERT INTO deployment_scheduler (build_id, deployment_status, deployment_date) "
            + "VALUES (?, ?, ?)";
    
    private PreparedStatement updateNextDeploymentDateStmt = null;
    private String updateNextDeploymentDateQuery = "UPDATE build SET next_deployment_date = ? WHERE build_id = ?";
    
    private PreparedStatement selectDeploymentsStmt = null;
    private String selectDeployments = "SELECT * FROM build ORDER BY build_date DESC";
    
    private PreparedStatement selectBuildStmt = null;
    private String selectBuildQuery = "SELECT * FROM build WHERE build_id = ?";
    
    private PreparedStatement updateBuildRejectStmt = null;
    private String updateBuildRejectQuery = "UPDATE build SET build_state = 99 WHERE build_id = ?";
    
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
                bb.setBuildConfigId(selectBuildResult.getInt("build_config_id"));
                bb.setBuildName(selectBuildResult.getString("build_name"));
                bb.setBuildStatus(selectBuildResult.getInt("build_status"));
                bb.setBuildState(selectBuildResult.getInt("build_state"));
                bb.setBuildVersion(selectBuildResult.getString("build_version"));
                bb.setBuildDate(selectBuildResult.getDate("build_date"));
                bb.setNextDeploymentDate(selectBuildResult.getDate("next_deployment_date"));
            }
            
            if(null != selectBuildStmt) {
                selectBuildStmt.close();
                selectBuildStmt = null;
            }

            if(null != conn) {
                conn.close();
                conn = null;
            }
        } catch(SQLException se) {
            System.out.println("There was an error querying for build details...");
            System.out.println("Build Number: " + buildId);
            System.out.println(se.getMessage());
        }
        
        return bb;
    }
    
    public boolean incrementBuildState(int buildId, int newState) {
        try {
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            incrementBuildStateStmt = conn.prepareStatement(incrementBuildStateQuery);
            incrementBuildStateStmt.setInt(1, newState);
            incrementBuildStateStmt.setInt(2, buildId);
            incrementBuildStateStmt.executeUpdate();
            
            conn.commit();
        } catch(SQLException se2) {
            System.out.println("There was an error rolling back changes...");
            System.out.println("There was an error incrementing build state, rolling back changes:");
            System.out.println("Build Number: " + buildId);
            System.out.println("New State: " + newState);
            System.out.println(se2.getMessage());
            return false;
        } finally {
            try {
                if(null != incrementBuildStateStmt) {
                    incrementBuildStateStmt.close();
                    incrementBuildStateStmt = null;
                }
                
                if(null != conn) {
                    conn.close();
                    conn = null;
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
        
        return true;
    }
    
    public void updateBuildReject(String currentUser, int buildId, int currentState) {
        try {
            LocalDate localDate = LocalDate.now(ZoneId.of("America/Kentucky/Louisville"));
            
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            updateBuildRejectStmt = conn.prepareStatement(updateBuildRejectQuery);
            updateBuildRejectStmt.setInt(2, buildId);
            updateBuildRejectStmt.executeUpdate();
            
            conn.commit();
        } catch(SQLException se2) {
            System.out.println("There was an error rolling back changes...");
            System.out.println("There was an error updating build rejection state, rolling back changes:");
            System.out.println("Build Number: " + buildId);
            System.out.println(se2.getMessage());
        } finally {
            try {
                if(null != updateBuildRejectStmt) {
                    updateBuildRejectStmt.close();
                    updateBuildRejectStmt = null;
                }
                
                if(null != insertLogStmt) {
                    insertLogStmt.close();
                    insertLogStmt = null;
                }
                
                if(null != conn) {
                    conn.close();
                    conn = null;
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
    }
    
    public void insertBuildLog(String currentUser, int buildId, int currentState, int newState) {
        try {
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            
            conn.setAutoCommit(false);
            
            insertLogStmt = conn.prepareStatement(insertLogQuery);
            insertLogStmt.setInt(1, buildId);
            insertLogStmt.setString(2, currentUser);
            insertLogStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            insertLogStmt.setInt(4, currentState);
            insertLogStmt.setInt(5, newState);
            insertLogStmt.execute();
                    
            conn.commit();
        } catch(SQLException se2) {
            System.out.println("There was an error rolling back changes...");
            System.out.println("There was an error inserting a build approval log, rolling back changes:");
            System.out.println("Build Number: " + buildId);
            System.out.println(se2.getMessage());
        } finally {
            try {
                if(null != insertLogStmt) {
                    insertLogStmt.close();
                }
                
                if(null != conn) {
                    conn.close();
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
    }
    
    public List<BuildLog> getBuildLogs(int buildId) {
        List<BuildLog> buildLogs = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(deploymentsDbConnection,
                    deploymentsDbUser, deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            selectLogsStmt = conn.prepareStatement(selectLogsQuery);
            selectLogsStmt.setInt(1, buildId);
            
            ResultSet logResults = selectLogsStmt.executeQuery();
            
            while(logResults.next()) {
                BuildLog bl = new BuildLog(
                        logResults.getInt("build_logs_id"),
                        logResults.getInt("build_id"),
                        logResults.getString("log_uid"),
                        logResults.getDate("log_date"),
                        logResults.getInt("build_previous_state"),
                        logResults.getInt("build_new_state"));
                
                buildLogs.add(bl);
            }
        } catch(SQLException | NullPointerException | ClassNotFoundException se) {
            System.out.println("There was an error getting build logs:");
            System.out.println("Message:");
            System.out.println(se.getMessage());
        }
        
        return buildLogs;
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
    
    public void createDeployment(BusterBuild scheduledBuild) {
        try {
            /*
            When creating a deployment, the deployment_status has to be set. This field is to track the status of the build.
            The valid statuses are as follows:
            
            1 = Scheduled
            2 = Deployment Complete (Success)
            3 = Deployment Complete (Failed)
            99 = Deployment Cancelled by user
            
            When inserting a newly scheduled build, we set the value to 1 and then update the value as needed.
            
            !!! NOTE !!!
            We also want to update the next_deployment_date in the build table. This will be phased out with the next version. For
            now this is how we easily display this value in the deploymentList table for the user to see. In the next version, this
            value will come from the deployment_scheduler table and will be based on the status of the scheduled deployment for the
            build ID we are looking at.
            */
            
            Instant nextDeploymentDateInstant = scheduledBuild.getNextDeploymentDate().toInstant();
            ZoneId zoneId = ZoneId.of("America/Kentucky/Louisville");
            ZonedDateTime zoneDateTime = ZonedDateTime.ofInstant(nextDeploymentDateInstant, zoneId);
            LocalDate nextDeploymentLocalDate = zoneDateTime.toLocalDate();

            //java.sql.Date nextDeploymentDate = 
            
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            
            conn.setAutoCommit(false);
            
            insertScheduledDeploymentStmt = conn.prepareStatement(insertScheduledDeploymentQuery);
            insertScheduledDeploymentStmt.setInt(1, scheduledBuild.getBuildId());
            insertScheduledDeploymentStmt.setInt(2, 1);
            insertScheduledDeploymentStmt.setDate(3, java.sql.Date.valueOf(nextDeploymentLocalDate));
            insertScheduledDeploymentStmt.execute();
                    
            conn.commit();
            
            updateNextDeploymentDateStmt = conn.prepareStatement(updateNextDeploymentDateQuery);
            updateNextDeploymentDateStmt.setDate(1, java.sql.Date.valueOf(nextDeploymentLocalDate));
            updateNextDeploymentDateStmt.setInt(2, scheduledBuild.getBuildId());
            updateNextDeploymentDateStmt.executeUpdate();
            
            conn.commit();
        } catch(SQLException se) {
            try{
                conn.rollback();
                
                System.out.println("There was an error inserting a build approval log, rolling back changes:");
                System.out.println("Build Number: " + scheduledBuild.getBuildId());
                System.out.println(se.getMessage());
            } catch(SQLException se2) {
                System.out.println("There was an error rolling back changes...");
                System.out.println("Message: ");
                System.out.println(se2.getMessage());
                System.out.println("Stacktrace: ");
                System.out.println(Arrays.toString(se2.getStackTrace()));
            }
        } finally {
            try {
                if(null != insertScheduledDeploymentStmt) {
                    insertScheduledDeploymentStmt.close();
                    insertScheduledDeploymentStmt = null;
                }
                
                if(null != updateNextDeploymentDateStmt) {
                    updateNextDeploymentDateStmt.close();
                    updateNextDeploymentDateStmt = null;
                }
                
                if(null != conn) {
                    conn.close();
                    conn = null;
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
                System.out.println("Message: ");
                System.out.println(se3.getMessage());
                System.out.println("Stacktrace: ");
                System.out.println(Arrays.toString(se3.getStackTrace()));
            }
        }
    }
}
