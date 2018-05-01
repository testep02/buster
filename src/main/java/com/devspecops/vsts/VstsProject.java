/*
 * To change this license header, choose License Headers in VstsProject Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.vsts;

/**
 *
 * @author EstepTravis
 */
public class VstsProject {
    private String id;
    private String name;
    private String description;
    private String url;
    private String state;
    private int revision;
    private String visibility;
    
    public VstsProject() {
        
    }

    public VstsProject(String id, String name, String description, String url,
            String state, int revision, String visibility) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.state = state;
        this.revision = revision;
        this.visibility = visibility;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the revision
     */
    public int getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(getId()).append("\n")
                .append("name = ").append(getName()).append("\n")
                .append("description =").append(getDescription()).append("\n")
                .append("url = ").append(getUrl()).append("\n")
                .append("state = ").append(getState()).append("\n")
                .append("revision = ").append(getRevision()).append("\n")
                .append("visibility = ").append(getVisibility()).append("\n");
        
        return sb.toString();
    }
}