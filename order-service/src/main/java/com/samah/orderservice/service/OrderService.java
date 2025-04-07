package com.samah.orderservice.service;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public OrderDto addOrder(OrderDto OrderDto);
    public OrderDto getOrder(int id);

    public List<OrderDto> getAllOrders() ;

    //public List<OrderDto> getAllOrdersforBook(int bookId);

    public OrderDto updateOrder(OrderDto order, int id) ;

    OrderDto cancelOrder(OrderDto orderDto, int id);
}
