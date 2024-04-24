package com.samah.Bookservice.controller;

import com.samah.Bookservice.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/info")
    public String getInfo(){
        return infoService.getAppNameAndVersion();
    }
    @GetMapping("/java-version")
    public String getJavaVersion(){
        return infoService.getJavaVersion();
    }
}
