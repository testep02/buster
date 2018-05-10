/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.salesforce;

/**
 *
 * @author EstepTravis
 */
public class SfdxCommandFlag {
    private String flagName;
    private String flagShorthand;
    private String flagValue;

    /**
     * @return the flagName
     */
    public String getFlagName() {
        return flagName;
    }

    /**
     * @param flagName the flagName to set
     */
    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    /**
     * @return the flagShorthand
     */
    public String getFlagShorthand() {
        return flagShorthand;
    }

    /**
     * @param flagShorthand the flagShorthand to set
     */
    public void setFlagShorthand(String flagShorthand) {
        this.flagShorthand = flagShorthand;
    }

    /**
     * @return the flagValue
     */
    public String getFlagValue() {
        return flagValue;
    }

    /**
     * @param flagValue the flagValue to set
     */
    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }
}
