package com.samah.orderservice.service;

import com.samah.orderservice.entity.Order;
import com.samah.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProcessOrders processOrders;

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

    public Order updateOrder(Order order, int id) {
        order.setId(id);
        Order updatedOrder = switch (order.getStatus()){
            case PENDING -> processOrders.updatePendingOrder(order);
            case PROCESSING -> processOrders.updateProcessingOrder(order);
            case SHIPPED -> processOrders.updateShippedOrder(order);
            case DELIVERED -> processOrders.updateDileveredOrder(order);
            case CANCELED -> processOrders.updateCanceledOrder(order);
            case RETURNED -> processOrders.updateReturnedOrder(order);
            case REFUNDED -> processOrders.updateRefundedOrder(order);
            case ON_HOLD -> processOrders.updateOnHoldOrder(order);
            case COMPLETED -> processOrders.updateCompletedOrder(order);
        };
        return orderRepository.save(order);
    }
}
