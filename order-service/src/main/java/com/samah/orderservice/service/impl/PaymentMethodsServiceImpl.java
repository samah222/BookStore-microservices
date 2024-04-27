package com.samah.orderservice.service.impl;

import com.samah.orderservice.entity.PaymentMethods;
import com.samah.orderservice.repository.PaymentMethodsRepository;
import com.samah.orderservice.service.PaymentMethodsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodsServiceImpl implements PaymentMethodsService {
    private PaymentMethodsRepository repository;

    public PaymentMethodsServiceImpl(PaymentMethodsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentMethods> getAllPaymentMethods() {
        return repository.findAll();
    }
}
