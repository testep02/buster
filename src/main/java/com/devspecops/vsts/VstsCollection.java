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
    private VstsProject[] projects;
    
    public VstsCollection() {
        
    }
    
    public VstsCollection(int count, VstsProject[] projects) {
        this.count = count;
        this.projects = projects;
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
     * @return the projects
     */
    public VstsProject[] getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(VstsProject[] projects) {
        this.projects = projects;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("**** VSTS Collection Object ****\n");
        sb.append("id = ").append(getCount()).append("\n")
                .append("projects = ").append(Arrays.toString(getProjects()));
        
        return sb.toString();
    }
}
