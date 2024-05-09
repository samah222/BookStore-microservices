package com.samah.orderservice.service.impl;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.exception.InvalidDataException;
import com.samah.orderservice.mapper.Mapper;
import com.samah.orderservice.repository.OrderRepository;
import com.samah.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ProcessOrders processOrders;

    public OrderDto addOrder(OrderDto orderdto) {
        if (orderdto == null
                || orderdto.getBookId() == null
                || orderdto.getQuantity() == null
                || orderdto.getCustomerId() == null)
            throw new InvalidDataException("order data can not be empty");
        if (orderdto.getBookId() < 0
                || orderdto.getQuantity() < 0
                || orderdto.getCustomerId() < 0)
            throw new InvalidDataException("order data not valid");
        Order order = mapper.addNewOrderDto(orderdto);
        Order savedOrder = orderRepository.save(order);
        Order newOrder = processOrders.addNewOrder(savedOrder);
        return mapper.OrderToOrderDto(newOrder);
    }

    public OrderDto getOrder(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return mapper.OrderToOrderDto(order);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(o -> mapper.OrderToOrderDto(o)).toList();
    }

    public List<OrderDto> getAllOrdersforBook(int bookId) {
        return orderRepository.findByBookId(bookId).stream()
                .map(o -> mapper.OrderToOrderDto(o)).toList();
    }

    public OrderDto updateOrder(OrderDto orderDto, int id) {
        Order order = mapper.OrderDtoToOrder(orderDto);
        order.setId(id);

//        Order updatedOrder = switch (order.getStatus()){
//            case PENDING -> processOrders.updatePendingOrder(order);
//            case PROCESSING -> processOrders.updateProcessingOrder(order);
//            case SHIPPED -> processOrders.updateShippedOrder(order);
//            case DELIVERED -> processOrders.updateDileveredOrder(order);
//            case CANCELED -> processOrders.updateCanceledOrder(order);
//            case RETURNED -> processOrders.updateReturnedOrder(order);
//            case REFUNDED -> processOrders.updateRefundedOrder(order);
//            case ON_HOLD -> processOrders.updateOnHoldOrder(order);
//            case COMPLETED -> processOrders.updateCompletedOrder(order);
//        throw new IllegalArgumentException("Invalid order status: " + existingOrder.getStatus().getName());
//        };

        return mapper.OrderToOrderDto(orderRepository.save(order));
    }

    @Override
    public OrderDto cancelOrder(OrderDto orderDto, int id) {
        return null;
    }
}
