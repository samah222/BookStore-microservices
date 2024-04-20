package com.samah.orderservice.entity;

import com.samah.orderservice.util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "order_table")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//@Transactional
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer bookId;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer customerId;
    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;
    private OrderStatus status;
//    @OneToMany
//    private Customer customer;
    private double totalAmount;

}
