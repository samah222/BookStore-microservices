package com.samah.Bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.application.version}")
    private String applicationVersion;
    @Autowired
    private Environment environment;

    public String getAppNameAndVersion(){
        return  "This application is "+applicationName+" and this version: "+applicationVersion;
    }

    public String getJavaVersion() {
        return environment.getProperty("JAVA.VERSION");
    }
}
