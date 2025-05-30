package com.samah.orderservice.mapper;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.entity.OrderStatuses;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Order OrderDtoToOrder(OrderDto dto){
        if(dto == null){
            throw new NullPointerException("The Order DTO should not be null");
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setBookId(dto.getBookId());
        order.setCreatedAt(dto.getCreatedAt());
        order.setUpdatedAt(dto.getUpdatedAt());
        order.setTotalAmount(dto.getTotalAmount());
        order.setCustomerId(dto.getCustomerId());
        order.setStatus(dto.getStatus());
        order.setShippingAt(dto.getShippingAt());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setShipper(dto.getShipper());
        order.setQuantity(dto.getQuantity());
        order.setPaid(dto.isPaid());
        return order;
    }

    public OrderDto OrderToOrderDto(Order order){
        if(order == null){
            throw new NullPointerException("The Order should not be null");
        }
        OrderDto OrderDto = new OrderDto(order.getId(),order.getBookId(),order.getCustomerId(),order.getQuantity()
                ,order.getCreatedAt(), order.getUpdatedAt(),order.getTotalAmount(),order.getShippingAt(),order.getStatus(),
                order.getShipper(), order.getPaymentMethod(), order.isPaid(), order.getDiscount());
        return OrderDto;
    }

    public Order addNewOrderDto(OrderDto orderdto) {
        if(orderdto == null){
            throw new NullPointerException("The Order DTO should not be null");
        }

        Order order = Order.builder()
                        .bookId(orderdto.getBookId())
                        .customerId(orderdto.getCustomerId())
                        .quantity(orderdto.getQuantity())
                        .paymentMethod(orderdto.getPaymentMethod())
                        .shipper(orderdto.getShipper())
                        .shippingAt(orderdto.getShippingAt())
                        .status(new OrderStatuses(1, "PENDING"))
                        .paid(false)
                        .discount(orderdto.getDiscount())
                        .build();
        return order;
    }
}
