package com.samah.orderservice.controller;

import com.samah.orderservice.service.impl.InfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Application Info", description = "The general info of this application")
@RestController
@RequestMapping("/v1")
public class InfoController {
    @Autowired
    private InfoServiceImpl infoServiceImpl;

    @Operation(
            summary = "Fetch general app info ",
            description = "fetches all general application info, e.g. application name and version")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/info")
    public String getInfo() {
        return infoServiceImpl.getAppNameAndVersion();
    }

    @Operation(
            summary = "Fetch Java version",
            description = "Fetch the Java version and other environment variables")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/java-version")
    public String getJavaVersion() {
        return infoServiceImpl.getJavaVersion();
    }
}
