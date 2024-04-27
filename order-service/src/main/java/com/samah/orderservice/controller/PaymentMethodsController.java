package com.samah.orderservice.controller;

import com.samah.orderservice.entity.PaymentMethods;
import com.samah.orderservice.service.PaymentMethodsService;
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

@Tag(name = "Payment Methods", description = "Show all Payment Methods of the Books-store")
@RestController
@RequestMapping("/v1/paymentmethods")
public class PaymentMethodsController {
    private PaymentMethodsService paymentMethodsService;

    public PaymentMethodsController(PaymentMethodsService paymentMethodsService) {
        this.paymentMethodsService = paymentMethodsService;
    }

    @GetMapping
    @Operation(
            summary = "Fetch all Payment Methods",
            description = "fetches all PaymentMethods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    public ResponseEntity<List<PaymentMethods>> getAllPaymentMethods() {
        return new ResponseEntity<>(paymentMethodsService.getAllPaymentMethods(), HttpStatus.OK);
    }
}
