package com.samah.orderservice.dto;

import com.samah.orderservice.entity.OrderStatuses;
import com.samah.orderservice.entity.PaymentMethods;
import com.samah.orderservice.entity.Shipper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto{
        private Integer id;
        private List<Integer> bookId;
        private Integer customerId;
        private List<Integer> quantity;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private double totalAmount;
        private LocalDateTime shippingAt;
        private  OrderStatuses status;
        private  Shipper shipper;
        private  PaymentMethods paymentMethod;
        private boolean paid;
}
