package com.samah.orderservice.dto;

import com.samah.orderservice.entity.OrderStatuses;
import com.samah.orderservice.entity.PaymentMethods;
import com.samah.orderservice.entity.Shipper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto{
        private Integer id;
        private Integer bookId;
        private Integer customerId;
        private Integer quantity;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private double totalAmount;
        private LocalDateTime ShippingAt;
        private  OrderStatuses status;
        private  Shipper shipper;
        private  PaymentMethods paymentMethod;
}
