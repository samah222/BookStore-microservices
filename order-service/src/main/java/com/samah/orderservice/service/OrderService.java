package com.samah.orderservice.service;

import com.samah.orderservice.model.Order;
import com.samah.orderservice.repository.OrderRepository;
import com.samah.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order Order){
        Order savedOrder = orderRepository.save(Order);
        return savedOrder;
    }
    public Order getOrder(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersforBook(int bookId) {
        return orderRepository.findByBookId(bookId);
    }
}
