/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop;

import com.riverainc.airdrop.commands.AnsibleCommand;
import com.riverainc.airdrop.commands.EC2Command;
import com.riverainc.airdrop.deployment.DeploymentSql;
import com.riverainc.airdrop.scheduler.EnvScheduler;
import com.riverainc.airdrop.scheduler.SnapshotScheduler;
import com.riverainc.airdrop.scheduler.jobs.StartEnvJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author testep
 */

@SpringBootApplication
@EnableAutoConfiguration(exclude = JmxAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {
    
    //public static String ansibleCommandExample(Model model, 
    //        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        //AnsibleCommand cmd = new AnsibleCommand();
        
        //String commandText = "ping -c 3 google.com";
        
        //return cmd.executeCommand(commandText);
        
    //    model.addAttribute("name", name);
        
    //    return "home";
    //}
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        
        /*** 
         * 
         * TEST SECTION. NOT FOR PRODUCTION USE!!
         * 
         * */
        
        //DeploymentSql dSql = new DeploymentSql();
        //dSql.updateDeploymentEnv("319", "DEMO");
        //SnapshotScheduler ss = SnapshotScheduler.getInstance();
        //ss.schedule();
        //EnvScheduler envSch = EnvScheduler.getInstance();
        //envSch.scheduleEnvStart("test", "");
        //envSch.scheduleEnvStop("test", "");
        
        /*** 
         * 
         * END TEST SECTION.
         * 
         * */
    }
}
