package com.riverainc.airdrop;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author testep
 */

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/aws/home").setViewName("awsHome");
        registry.addViewController("/aws/environments").setViewName("awsEnvironments");
        registry.addViewController("/aws/environments/environment").setViewName("awsEnvironment");
        registry.addViewController("/aws/environments/envSnapshots").setViewName("awsEnvSnapshots");
        registry.addViewController("/aws/environments/createSnapshot").setViewName("awsCreateSnapshot");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/deployments/deploymentList").setViewName("deploymentList");
        registry.addViewController("/deployments/scheduleDeployment").setViewName("scheduleDeployment");
        registry.addViewController("/deployments/deploymentScheduleWindows").setViewName("deploymentScheduleWindows");
        
    }
}
