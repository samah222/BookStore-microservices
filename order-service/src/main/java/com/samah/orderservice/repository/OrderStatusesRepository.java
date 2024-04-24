package com.samah.orderservice.repository;

import com.samah.orderservice.entity.OrderStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusesRepository extends JpaRepository<OrderStatuses, Integer> {

}
