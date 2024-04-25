package com.samah.orderservice.mapper;

import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.entity.OrderStatuses;
import com.samah.orderservice.entity.PaymentMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    Mapper mapper;
    OrderStatuses status;
    PaymentMethods paymentMethod;
    OrderDto dto;
    Order order;
    @BeforeEach
    void setup(){
        mapper = new Mapper();
        status = new OrderStatuses(1,"PENDING");
        paymentMethod = new PaymentMethods(1,"Cash");
    }
    @Test
    @DisplayName("Should map from Order to OrderDto successfully")
    void  should_map_order_To_OrderDto() {
        order = new Order(1,2,3,8, LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),
                status,null, paymentMethod, BigDecimal.valueOf(100));
        dto = mapper.OrderToOrderDto(order);
        assertEquals(dto.getId(), order.getId());
        assertEquals(dto.getBookId(), order.getBookId());
        assertEquals(dto.getQuantity(), order.getQuantity());
        assertEquals(dto.getCustomerId(), order.getCustomerId());
        assertEquals(dto.getCreatedAt(), order.getCreatedAt());
        assertEquals(dto.getUpdatedAt(), order.getUpdatedAt());
        assertEquals(dto.getShippingAt(), order.getShippingAt());
        assertEquals(dto.getShipper(), order.getShipper());
        assertEquals(dto.getStatus(), order.getStatus());
        assertEquals(dto.getPaymentMethod(), order.getPaymentMethod());
        assertEquals(dto.getTotalAmount(), order.getTotalAmount());
    }
    @Test
    @DisplayName("Should map from OrderDto to Order successfully")
    void should_map_orderDto_To_Order() {
        dto = new OrderDto(1,2,3,8, LocalDateTime.now(),LocalDateTime.now(),
                BigDecimal.valueOf(100), LocalDateTime.now(), status,null, paymentMethod);
        order = mapper.OrderDtoToOrder(dto);
        assertEquals(order.getId(), dto.getId());
        assertEquals(order.getBookId(), dto.getBookId());
        assertEquals(order.getQuantity(), dto.getQuantity());
        assertEquals(order.getCustomerId(), dto.getCustomerId());
        assertEquals(order.getCreatedAt(), dto.getCreatedAt());
        assertEquals(order.getUpdatedAt(), dto.getUpdatedAt());
        assertEquals(order.getShippingAt(), dto.getShippingAt());
        assertEquals(order.getShipper(), dto.getShipper());
        assertEquals(order.getStatus(), dto.getStatus());
        assertEquals( order.getPaymentMethod(), dto.getPaymentMethod());
        assertEquals(order.getTotalAmount(), dto.getTotalAmount());
    }

    @Test
    public void should_throw_Null_Pointer_Exception_when_OrderDto_is_null(){
        dto = null;
        var msg = assertThrows(NullPointerException.class, () -> mapper.OrderDtoToOrder(dto));
        assertEquals(msg.getMessage(),"The Order DTO should not be null");
    }

    @Test
    public void should_throw_Null_Pointer_Exception_when_Order_is_null(){
        order = null;
        var msg = assertThrows(NullPointerException.class, () -> mapper.OrderToOrderDto(order));
        assertEquals(msg.getMessage(),"The Order should not be null");
    }

    @Test
    void addNewOrderDto() {
    }
}