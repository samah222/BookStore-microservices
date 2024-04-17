package com.samah.orderservice.service;

import com.samah.orderservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class ProcessOrders {
    public Order updatePendingOrder(Order order){
        return  order;
    }

    public Order updateOnHoldOrder(Order order){
        return  order;
    }

    public Order updateProcessingOrder(Order order){
        return  order;
    }

    public Order updateShippedOrder(Order order){
        return  order;
    }

    public Order updateDileveredOrder(Order order){
        return  order;
    }

    public Order updateCanceledOrder(Order order){
        return  order;
    }

    public Order updateReturnedOrder(Order order){
        return  order;
    }

    public Order updateRefundedOrder(Order order){
        return  order;
    }

    public Order updateCompletedOrder(Order order){
        return  order;
    }
}
