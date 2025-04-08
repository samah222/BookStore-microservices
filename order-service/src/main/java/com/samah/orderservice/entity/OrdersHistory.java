package com.samah.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "orders_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatuses status;

    @CreationTimestamp
    @Column(name = "changed_at", nullable = false, updatable = false)
    private LocalDateTime changedAt;

    @Column(name = "changed_by")
    private String changedBy;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;


    /*Table: order_status_history
--------------------------------
    id              INT PK
    order_id        INT FK to orders(id)
    status_id       INT FK to status(status_id)
    changed_at      DATETIME
    changed_by      VARCHAR(100) -- optional: user/admin/system
    note            TEXT          -- optional comments */

}
