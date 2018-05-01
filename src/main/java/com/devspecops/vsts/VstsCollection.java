/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.vsts;

import java.util.Arrays;

/**
 *
 * @author EstepTravis
 */
public class VstsCollection {
    private int count;
    private VstsProject[] value;
    
    public VstsCollection() {
        
    }
    
    public VstsCollection(int count, VstsProject[] value) {
        this.count = count;
        this.value = value;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the value
     */
    public VstsProject[] getValue() {
        return value;
    }

    /**
     * @param projects the value to set
     */
    public void setValue(VstsProject[] values) {
        this.value = values;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("**** VSTS Collection Object ****\n");
        sb.append("id = ").append(getCount()).append("\n")
                .append("projects = ").append(Arrays.toString(getValue()));
        
        return sb.toString();
    }
}
