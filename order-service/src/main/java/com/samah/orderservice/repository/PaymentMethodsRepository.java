package com.samah.orderservice.repository;

import com.samah.orderservice.entity.Order;
import com.samah.orderservice.entity.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodsRepository extends JpaRepository<PaymentMethods, Integer> {

}
