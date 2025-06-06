package com.samah.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Transactional
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private List<Integer> bookId;
    @Column(nullable = false)
    private List<Integer> quantity;
    //@Column(nullable = false)
    private Integer customerId;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "shipping_at") //nullable = true
    private LocalDateTime shippingAt;

    //private LocalDateTime shippedDate;
    //private String comments;
    @ManyToOne
    private OrderStatuses status;
    //    @OneToMany
    //    private Customer customer;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Shipper shipper;
    @ManyToOne
    private PaymentMethods paymentMethod;
    private double totalAmount;
    private boolean paid;

    private double discount;
}
