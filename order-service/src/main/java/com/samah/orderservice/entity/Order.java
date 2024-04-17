package com.samah.orderservice.entity;

import com.samah.orderservice.model.Customer;
import com.samah.orderservice.util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity(name = "order_table")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//@Transactional
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private Integer customerId;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private LocalDate updatedAt;
    private OrderStatus status;
//    @OneToMany
//    private Customer customer;
    private double totalAmount;

}
