package com.samah.orderservice.repository;

import com.samah.orderservice.entity.Order;
import com.samah.orderservice.util.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByBookId(int bookId);
    List<Order> findByCustomerId(int customerId);
    List<Order> findByStatus(OrderStatus status);

}
