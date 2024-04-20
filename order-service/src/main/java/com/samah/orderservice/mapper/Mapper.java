package com.samah.orderservice.mapper;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Order OrderDtoToOrder(OrderDto dto){
        if(dto == null){
            throw new NullPointerException("The Order DTO should not be null");
        }
        Order order = new Order();
        order.setId(dto.id());
        order.setStatus(dto.status());
        order.setBookId(dto.bookId());
        order.setCreatedAt(dto.createdAt());
        order.setUpdatedAt(dto.updatedAt());
        order.setTotalAmount(dto.totalAmount());
        order.setCustomerId(dto.customerId());

        return order;
    }

    public OrderDto OrderToOrderDto(Order order){
        OrderDto OrderDto = new OrderDto(order.getId(), order.getBookId(), order.getCustomerId(),
                order.getUserId(), order.getCreatedAt(), order.getUpdatedAt(),order.getStatus(), order.getTotalAmount());
        return OrderDto;
    }

}
