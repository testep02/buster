/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.salesforce;

import java.util.List;

/**
 *
 * @author EstepTravis
 */
public interface ISfdxCommand {
    String getFullCommand();
    void addFlag(SfdxCommandFlag flag);
    List<SfdxCommandFlag> getFlags();
    int getFlagCount();
    SfdxCommandFlag getFlag(int flagNum);
    void setCommand(String command);
    void setSubCommand(String subCommand);
    void setAction(String action);
    String getNamespace();
    String getCommand();
    String getSubCommand();
    String getAction();
    
    void execute(String command);
}
