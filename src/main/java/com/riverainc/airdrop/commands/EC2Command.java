/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.commands;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.internal.SdkInternalList;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceBlockDeviceMapping;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.riverainc.airdrop.models.BackupSnapshot;
import com.riverainc.airdrop.models.EnvBackup;
import com.riverainc.airdrop.models.Environment;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author testep
 */
public class EC2Command {
    
    public AmazonEC2 ec2;
    
    public EC2Command() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            AWSCredentials credentials = new PropertiesCredentials(
                    new File(classLoader.getResource("AWSCredentials.properties").getFile()));
            ec2 = new AmazonEC2Client(credentials);
        } catch(IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<String> getEnvironments() {
        List<String> envs = new SdkInternalList<>();
        
        List<Instance> envInstances = new SdkInternalList<>();
        DescribeInstancesResult instanceResult = ec2.describeInstances();
        
        instanceResult.getReservations().stream().map((r) -> r.getInstances()).forEach((instances) -> {
            instances.stream().map((i) -> 
                    i.getTags()).filter((instanceTags) -> 
                            (instanceTags != null && instanceTags.size() > 0)).forEach((instanceTags) -> {
                    instanceTags.stream().filter((tag) -> 
                            (tag.getKey().equals("Environment"))).map((tag) -> 
                                    tag.getValue()).filter((environment) -> 
                                            (!envs.contains(environment))).forEach((environment) -> {
                                                envs.add(environment);
                });
            });
        });
        
        System.out.println("Number of environments: " + envs.size());
        
        envs.stream().forEach((env) -> {
            System.out.println("Environment: " + env);
        });
        
        return envs;
    }
    
    public List<Instance> getInstancesInEnvironment(String environmentName) {
        
        DescribeInstancesRequest dir = new DescribeInstancesRequest();
        List<String> filterValues = new ArrayList<>();
        
        filterValues.add(environmentName);
        
        Filter envFilter = new Filter("tag:Environment", filterValues);
        
        DescribeInstancesResult result = ec2.describeInstances(dir.withFilters(envFilter));
        
        List<Reservation> reservations = result.getReservations();
        List<Instance> environmentInstances = new ArrayList<>();
        
        reservations.stream().map((r) -> r.getInstances()).map((List<Instance> rInstances) -> {
            environmentInstances.addAll(rInstances);
            return rInstances;
        }).forEach((List<Instance> rInstances) -> {
            rInstances.stream().map((instance) -> {
                InstanceState state = instance.getState();
                String stateName = state.getName();
                System.out.println("Instance ID: " + instance.getInstanceId());
                return instance;
            }).forEach((instance) -> {
                System.out.println("Instance Name: " + instance.getTags());
            });
        });
        
        return environmentInstances;
    }
    
    public Map<String, String> getEnvironmentStatuses() {
        Map<String, String> envStatuses = new HashMap<>();
        
        List<Environment> envList = new ArrayList<>();
        
        getEnvironments().stream().forEach((env) -> {
            envList.add(new Environment(env));
        });
        
        envList.stream().forEach((e) -> {
            envStatuses.put(e.getName(), e.getStatus());
        });
        
        return envStatuses;
    }
    
    public Instance getInstanceDetails(String instanceId) {
        Instance instance;
        
        List<String> instanceIds = new LinkedList<>();
        instanceIds.add(instanceId);
        
        DescribeInstancesRequest dir = new DescribeInstancesRequest();
        dir.setInstanceIds(instanceIds);
        
        DescribeInstancesResult diResult = ec2.describeInstances(dir);
        instance = diResult.getReservations().get(0).getInstances().get(0);
        
        return instance;
    }
    
    public DescribeAvailabilityZonesResult myAvailabilityZones() {
        
        DescribeAvailabilityZonesResult azResults = ec2.describeAvailabilityZones();
        
        return azResults;
    }
    
    public void startInstance(String instanceId) {
        List<String> instanceIds = new LinkedList<>();
        instanceIds.add(instanceId);
        
        StartInstancesRequest sir = new StartInstancesRequest(instanceIds);
        ec2.startInstances(sir);
    }
    
    public void startInstances(List<String> instanceIds) {
        StartInstancesRequest sir = new StartInstancesRequest(instanceIds);
        ec2.startInstances(sir);
    }
    
    public void stopInstance(String instanceId) {
        List<String> instanceIds = new LinkedList<>();
        instanceIds.add(instanceId);
        
        StopInstancesRequest sir = new StopInstancesRequest(instanceIds);
        ec2.stopInstances(sir);
    }
    
    public void stopInstances(List<String> instanceIds) {
        StopInstancesRequest sir = new StopInstancesRequest(instanceIds);
        ec2.stopInstances(sir);
    }
    
    public void startEnvironment(String envName) {
        List<String> instanceIds = new LinkedList<>();
        
        getInstancesInEnvironment(envName).stream().forEach((i) -> {
            instanceIds.add(i.getInstanceId());
        });
        
        StartInstancesRequest sir = new StartInstancesRequest(instanceIds);
        ec2.startInstances(sir);
    }
    
    public void stopEnvironment(String envName) {
        List<String> instanceIds = new LinkedList<>();
        
        getInstancesInEnvironment(envName).stream().forEach((i) -> {
            instanceIds.add(i.getInstanceId());
        });
        
        StopInstancesRequest sir = new StopInstancesRequest(instanceIds);
        ec2.stopInstances(sir);
    }
    
    public EnvBackup createEnvSnapshot(String envName) {
        EnvBackup envBackup = new EnvBackup();
        envBackup.setBackupDate(Date.from(Instant.now()));
        envBackup.setBackupEnv(envName);
        envBackup.setBackupSource(EnvBackup.BACKUP_SOURCE_SCHEDULER);
        
        List<Instance> envInstances = getInstancesInEnvironment(envName);
        List<BackupSnapshot> snapshots = new ArrayList<>();
        
        envInstances.stream().forEach((instance) -> {
            List<InstanceBlockDeviceMapping> mappingList = instance.getBlockDeviceMappings();
            mappingList.stream().forEach((device) -> {
                BackupSnapshot snapshot = new BackupSnapshot();
                snapshot.setBackupVolumeId(device.getEbs().getVolumeId());
                snapshot.setBackupInstanceId(instance.getInstanceId());
                
                CreateSnapshotRequest snapReq = 
                        new CreateSnapshotRequest(snapshot.getBackupVolumeId(), "Created by Buster");
                CreateSnapshotResult snapRes = ec2.createSnapshot(snapReq);
                snapshot.setSnapshotId(snapRes.getSnapshot().getSnapshotId());
                
                snapshots.add(snapshot);
            });
        });
        
        envBackup.setSnapshots(snapshots);
        
        return envBackup;
    }
}
