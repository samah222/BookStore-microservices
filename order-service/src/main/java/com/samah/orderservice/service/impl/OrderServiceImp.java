package com.samah.orderservice.service.impl;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.entity.OrderStatuses;
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
                || orderdto.getCustomerId() == null
                || orderdto.getPaymentMethod() == null
                || orderdto.getShipper() == null)
            throw new InvalidDataException("order data can not be empty");
        if (orderdto.getCustomerId() < 0)
            throw new InvalidDataException("order data not valid");
        if(orderdto.getBookId().stream().allMatch(id -> id == null || id <= 0))
            throw new InvalidDataException("order data not valid");
        if(orderdto.getQuantity().stream().allMatch(q -> q == null || q <= 0))
            throw new InvalidDataException("order data not valid");
        if(orderdto.getDiscount()<=-1)
            throw new InvalidDataException("order data not valid");
        else {
            orderdto.setTotalAmount(processOrders.computeDiscount(orderdto.getTotalAmount(), orderdto.getDiscount()));
        }
        Order order = mapper.addNewOrderDto(orderdto);
        if(orderdto.isPaid()){
            order.setPaid(true);
        }
        Order newOrder = processOrders.addNewOrder(order);
        Order savedOrder = orderRepository.save(newOrder);
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

//    public List<OrderDto> getAllOrdersforBook(int bookId) {
//        return orderRepository.findByBookId(bookId).stream()
//                .map(o -> mapper.OrderToOrderDto(o)).toList();
//    }

    public OrderDto updateOrder(OrderDto orderDto, int id) {
        // Fetch existing order from DB
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Convert DTO to entity
        Order order = mapper.OrderDtoToOrder(orderDto);
        order.setId(id);

        OrderStatuses status  = order.getStatus();
        // Handle order status updates
        Order updatedOrder = switch (status.getName()) {
            case "PENDING" -> processOrders.updatePendingOrder(order);
            case "PROCESSING" -> processOrders.updateProcessingOrder(order);
            case "SHIPPED" -> processOrders.updateShippedOrder(order);
            case "DELIVERED" -> processOrders.updateDeliveredOrder(order);
            case "CANCELED" -> processOrders.updateCanceledOrder(order);
            case "RETURNED" -> processOrders.updateReturnedOrder(order);
            case "REFUNDED" -> processOrders.updateRefundedOrder(order);
            case "ON_HOLD" -> processOrders.updateOnHoldOrder(order);
            case "COMPLETED" -> processOrders.updateCompletedOrder(order);
            default -> throw new IllegalArgumentException("Invalid order status: " + order.getStatus());
        };

        // Save updated order to DB
        Order savedOrder = orderRepository.save(updatedOrder);

        // Convert to DTO and return
        return mapper.OrderToOrderDto(savedOrder);
    }


    @Override
    public OrderDto cancelOrder(OrderDto orderDto, int id) {
        return null;
    }
}
