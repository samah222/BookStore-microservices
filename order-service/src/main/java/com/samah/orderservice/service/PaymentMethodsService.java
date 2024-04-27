package com.samah.orderservice.service;

import com.samah.orderservice.entity.PaymentMethods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentMethodsService {
    List<PaymentMethods> getAllPaymentMethods();
}
