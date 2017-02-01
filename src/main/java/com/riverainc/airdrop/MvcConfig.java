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
        registry.addViewController("/home");
        registry.addViewController("/");
        registry.addViewController("/aws/home");
        registry.addViewController("/aws/environments");
        registry.addViewController("/aws/environments/environment");
        registry.addViewController("/aws/environments/envSnapshots");
        registry.addViewController("/aws/environments/createSnapshot");
        registry.addViewController("/login");
        registry.addViewController("/deployments/deploymentList");
        registry.addViewController("/deployments/scheduleDeployment");
        registry.addViewController("/deployments/deploymentScheduleWindows");
        registry.addViewController("/deployments/approveBuild");
        registry.addViewController("/aws/snapshots/snapshots");
    }
}
