/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.deployment;

import com.riverainc.airdrop.models.EnvBackup;
import com.riverainc.airdrop.models.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author testep
 */
public class SnapshotSql {
    
    private ResultSet rs = null;
    
    private String deploymentsDbConnection = "jdbc:postgresql://localhost:5432/buster";
    private String deploymentsDbUser = "postgres";
    private String deploymentsDbAuth = "i4m4pldg";
    
    private String insertBackupRow = "INSERT INTO backups (backup_date, backup_env, backup_source) "
            + "VALUES (?, ?, ?) RETURNING backup_id";
    private PreparedStatement insertBackupStmt = null;
    
    private String insertBackupSnapshotRow = 
            "INSERT INTO backup_snapshots (backup_id, backup_instance_id, " 
            + "backup_instance_name, backup_snapshot_id, backup_volume_id) "
            + "VALUES (?, ?, ?, ?, ?)";
    private PreparedStatement insertBackupSnapshotStmt = null;
    
    private String dateFormat = "MM/dd/yyyy";
    
    private Connection conn = null;
    
    public int insertBackup(EnvBackup envBackup) {
        int backupId = 0;
        
        try {
            conn = DriverManager.getConnection(deploymentsDbConnection,
                deploymentsDbUser, deploymentsDbAuth);
            conn.setAutoCommit(false);
            
            insertBackupStmt = conn.prepareStatement(insertBackupRow);
            insertBackupStmt.setDate(1, java.sql.Date.valueOf(envBackup.getBackupDate().toString()));
            insertBackupStmt.setString(2, envBackup.getBackupEnv());
            insertBackupStmt.setString(3, envBackup.getBackupSource());
            
            ResultSet backupResult = insertBackupStmt.executeQuery();
            backupResult.next();
            backupId = backupResult.getInt(0);
            
        } catch(SQLException se) {
            System.out.println("There was an error inserting a backup row");
        }
        
        return backupId;
    }
    
    public void insertSnapshotResults(EnvBackup envBackup) {
        try {
            //DateFormat df = new SimpleDateFormat(dateFormat);
            //Date date = df.parse(deploymentDate);
            //long time = date.getTime();
            //Timestamp ts = new Timestamp(time);
            
            conn = DriverManager.getConnection(deploymentsDbConnection, deploymentsDbUser,
                                            deploymentsDbAuth);
            conn.setAutoCommit(false);
            //updateDeploymentStmt = conn.prepareStatement(updateDeploymentEnvQuery);
            //updateDeploymentStmt.setString(1, deploymentEnv);
            //updateDeploymentStmt.setTimestamp(2, ts);
            //updateDeploymentStmt.setInt(2, Integer.parseInt(buildNumber));
            //updateDeploymentStmt.executeUpdate();
            
            conn.commit();
            
        } catch(SQLException se) {
            try {
                conn.rollback();
            } catch(SQLException se2) {
                System.out.println("There was an error rolling back changes...");
            }
            
            System.out.println("There was an error updating a deployment, rolling back changes:");
            System.out.println(se.getMessage());
        } finally {
            try {
                if(null != insertBackupStmt) {
                    insertBackupStmt.close();
                }
                
                if(null != insertBackupSnapshotStmt) {
                    insertBackupSnapshotStmt.close();
                }
                
                if(null != conn) {
                    conn.close();
                }
            } catch(SQLException se3) {
                System.out.println("There was an error closing prepared statement or connection");
            }
        }
    }
    
    public List<Environment> getAllSnapshots() {
        List<Environment> envs = new ArrayList<>();
        
        return envs;
    }
}
