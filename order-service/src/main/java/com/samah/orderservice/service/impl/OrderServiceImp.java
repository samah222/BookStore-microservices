package com.samah.orderservice.service.impl;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.mapper.Mapper;
import com.samah.orderservice.repository.OrderRepository;
import com.samah.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ProcessOrders processOrders;

    public OrderDto addOrder(OrderDto Orderdto) {
        Order order = mapper.addNewOrderDto(Orderdto);
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
//        };

        return mapper.OrderToOrderDto(orderRepository.save(order));
    }

    @Override
    public OrderDto cancelOrder(OrderDto orderDto, int id) {
        return null;
    }
}
