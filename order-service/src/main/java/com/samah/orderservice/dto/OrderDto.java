package com.samah.orderservice.dto;

import com.samah.orderservice.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer bookId;
    private Integer customerId;
    private Integer userId;
    private LocalDate createdAt;
    private OrderStatus status;
    private double totalAmount;

}
