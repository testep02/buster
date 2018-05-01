/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.models;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Tag;
import com.devspecops.airdrop.commands.EC2Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author testep
 */
public class Environment {
    private String name;
    private String status;
    private List<Instance> instances;
    
    public Environment(String name) {
        this.name = name;
    }
    
    public Environment(String name, String status) {
        this.name = name;
        this.status = status;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStatus() {
        int instanceCount = getInstances().size();
        int offlineCount = 0;
        int onlineCount = 0;
        
        onlineCount = getInstances().stream()
                .filter(
                        (i) -> i.getState().getName().equalsIgnoreCase("running")
                ).collect(Collectors.toList()).size();
        
        offlineCount = getInstances().stream()
                .filter(
                        (i) -> i.getState().getName().equalsIgnoreCase("stopped")
                ).collect(Collectors.toList()).size();
        
        if(offlineCount == 0 && onlineCount == instanceCount)
            status = "online";
        else if(onlineCount == 0 && offlineCount == instanceCount)
            status = "offline";
        else if(onlineCount > 0 && offlineCount > 0)
            status = "partial";
        
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<Instance> getInstances() {
        EC2Command command = new EC2Command();
        instances = command.getInstancesInEnvironment(name);
        
        return instances;
    }
    
    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
    
    public List<String> getInstanceNames() {
        List<String> instanceNames = new ArrayList<String>();
        
        for(Instance i : getInstances()) {            
            for(Tag t : i.getTags()) {
                if(t.getKey().equals("Name"))
                    instanceNames.add(t.getValue());
            }
        }
        
        return instanceNames;
    }
    
    public Map<String, String> getInstanceTags(String instanceName) {
        Map<String, String> instanceTags = new HashMap<String, String>();
        
        getInstances().stream().filter((i) -> (i.getTags().stream().filter(tag -> tag.getValue().equalsIgnoreCase(instanceName))
                .collect(Collectors.toList()).size() > 0)).forEach((i) -> {
                    i.getTags().stream().forEach((t) -> {
                        instanceTags.put(t.getKey(), t.getValue());
                    });
        });
        
        return instanceTags;
    }
}
