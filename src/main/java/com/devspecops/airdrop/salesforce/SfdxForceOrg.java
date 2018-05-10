/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.salesforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EstepTravis
 */
public class SfdxForceOrg implements ISfdxCommand {
    public static final String NAMESPACE = "force";
    public static final String COMMAND = "org";
    public static final String ACTION_CREATE = "create";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_DISPLAY = "display";
    public static final String ACTION_LIST = "list";
    public static final String ACTION_OPEN = "open";
    public static final String SUB_COMMAND_SHAPE = "shape";
    
    private String command;
    private String subCommand;
    private String action;
    
    private List<SfdxCommandFlag> commandFlags;
    
    public SfdxForceOrg() {
        this.commandFlags = new ArrayList<>();
    }
    
    @Override
    public String getFullCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("sfdx")
                .append(" ")
                .append(getNamespace())
                .append(":")
                .append(getCommand());
        
        if(null != subCommand && !subCommand.equals("")) {
            sb.append(":").append(getSubCommand());
        }
        
        if(null != action && !action.equals("")) {
            sb.append(":").append(getAction());
        }
        
        return sb.toString();
    }
    
    @Override
    public void execute() {
        StringBuffer output = new StringBuffer();
        
        Process process;
        
        try {
            process = Runtime.getRuntime().exec("ping -c 3 http://www.google.com");
            process.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line = "";
            
            while((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            
            System.out.println("Results from ping:");
            System.out.println(output.toString());
        } catch (Exception e) {
            System.out.println("There was an error trying to run ping: ");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void addFlag(SfdxCommandFlag flag) {
        this.commandFlags.add(flag);
    }
    
    @Override
    public List<SfdxCommandFlag> getFlags() {
        return commandFlags;
    }
    
    @Override
    public int getFlagCount() {
        return commandFlags.size();
    }
    
    @Override
    public SfdxCommandFlag getFlag(int flagNum) {
        return commandFlags.get(flagNum);
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
    
    @Override
    public void setSubCommand(String subCommand) {
        this.subCommand = subCommand;
    }
    
    @Override
    public void setAction(String action) {
        this.action = action;
    }
    
    @Override
    public String getNamespace() {
        return NAMESPACE;
    }
    
    @Override
    public String getCommand() {
        return this.command;
    }
    
    @Override
    public String getSubCommand() {
        return this.subCommand;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getAction() {
        return this.action;
    }
}
