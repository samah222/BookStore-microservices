package com.samah.orderservice.controller;

import com.samah.orderservice.entity.Shipper;
import com.samah.orderservice.service.ShipperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Shippers", description = "Show all shippers data of the Books-store")
@RestController
@RequestMapping("/v1/shippers")
public class ShipperController {
    private ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @GetMapping
    @Operation(
            summary = "Fetch all shippers data",
            description = "fetches all shippers data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    public ResponseEntity<List<Shipper>> getAllPaymentMethods() {
        return new ResponseEntity<List<Shipper>>(shipperService.getAllShippers(), HttpStatus.OK);
    }
}
