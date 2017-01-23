/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.commands;

/**
 *
 * @author testep
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AnsibleCommand {
    
    public String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        
        Process p;
        
        try {
            URL url = new URL("http://jenkins.rd.rcg.local:8080/job/e6-build-ui/lastSuccessfulBuild/api/xml");
            
            Document dom = new SAXReader().read(url);
            
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";
            
            while((line = reader.readLine()) != null) {
                output.append(line).append("<br />");
            }
            
            //Element rootElement = dom.getRootElement();
            
            List<Element> actionElements = dom.getRootElement().elements("action");
            
            Element buildAction = null;
            
            for( Iterator iter = actionElements.iterator(); iter.hasNext(); ) {
                Element actionElement = (Element)iter.next();
                if(actionElement.attribute("_class") != null && actionElement.attribute("_class").getValue().equals("hudson.plugins.git.util.BuildData"))
                    buildAction = actionElement;
            }
            
            Element build = buildAction.element("buildsByBranchName").element("refsremotesoriginreleasetest");
            
            output.append(String.format("Build Number: %s", build.element("buildNumber").getText()));
            output.append("<br />");
            output.append(String.format("Build SHA: %s", build.element("marked").element("SHA1").getText()));
            output.append("<br />");
            output.append(String.format("Build Result: %s", dom.getRootElement().element("result").getText()));
            output.append("<br />");
            output.append(String.format("Changeset: %s", dom.getRootElement().element("changeSet").element("item")
                    .element("id").getText()));
            output.append("<br />");
            output.append(String.format("Change Author: %s", dom.getRootElement().element("changeSet").element("item")
                    .element("author").element("fullName").getText()));
            
            // scan through the job list and print its status
            for( Element job : (List<Element>)dom.getRootElement().elements("action")) {
                output.append(String.format("Name: %s", job.element("refsremotesoriginreleasetest").elementText("buildNumber")));
                output.append("<br />");
                output.append(String.format("Status: %s", job.elementText("color")));
                output.append("<br />");
                output.append(String.format("Job URL: <a href=\"%s\">Go To Job</a>", job.elementText("url")));
                output.append("<br /><br />");
            }
            
            System.out.println(output.toString());
            //output.append(method.getResponseBodyAsString());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return output.toString();
    }
    
    private static void checkResult(int i) throws IOException {
        if(i / 100 != 2){
            //throw new IOException();
        }
            
    }
}
