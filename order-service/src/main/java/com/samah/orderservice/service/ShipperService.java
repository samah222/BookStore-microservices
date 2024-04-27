package com.samah.orderservice.service;

import com.samah.orderservice.entity.Shipper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShipperService {

    List<Shipper> getAllShippers();
}
