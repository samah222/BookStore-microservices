package com.samah.orderservice.dto;

import com.samah.orderservice.util.OrderStatus;

import java.time.LocalDateTime;

public record OrderDto(Integer id,
                       Integer bookId,
                       Integer customerId,
                       Integer userId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt,
                       OrderStatus status,
                       double totalAmount) { }
