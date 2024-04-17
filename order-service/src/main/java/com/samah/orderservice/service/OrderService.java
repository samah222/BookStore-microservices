package com.samah.orderservice.service;

import com.samah.orderservice.entity.Order;
import com.samah.orderservice.repository.OrderRepository;
import com.samah.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Order addOrder(Order Order);
    public Order getOrder(int id);

    public List<Order> getAllOrders() ;

    public List<Order> getAllOrdersforBook(int bookId);

    public Order updateOrder(Order order, int id) ;
}
