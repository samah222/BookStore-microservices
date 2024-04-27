package com.samah.orderservice.service.impl;

import com.samah.orderservice.entity.Shipper;
import com.samah.orderservice.repository.ShipperRepository;
import com.samah.orderservice.service.ShipperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {
    private ShipperRepository repository;

    public ShipperServiceImpl(ShipperRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Shipper> getAllShippers() {
        return repository.findAll();
    }
}
