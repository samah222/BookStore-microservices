package com.samah.orderservice.service.impl;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.dto.BookDto;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.entity.OrderStatuses;
import com.samah.orderservice.exception.BookNotFoundException;
import com.samah.orderservice.exception.InvalidDataException;
import com.samah.orderservice.repository.OrderRepository;
import com.samah.orderservice.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

//@Component

@Service
public class ProcessOrders {

//    @Autowired
//    private OrderRepository orderRepository;

    @Autowired
    BooksClient booksClient;
    public Order addNewOrder(Order order){
        int bookId = order.getBookId();
        BookDto bookDto = booksClient.getBooksDetails(bookId);
        if(bookDto.getQuantity() - order.getQuantity() >= 0){
            bookDto.setQuantity(bookDto.getQuantity() - order.getQuantity());
            booksClient.updateBookQuantity(bookDto, bookDto.getId());
        }
        else {
            throw new BookNotFoundException("Book is not available now");
        }
        // status:processing shipping [set shipper, time for shipping] -> confirm order

        return order;
    }
    public Order updatePendingOrder(Order order) {
        if (!order.getStatus().getName().equals("PENDING")) {
            throw new InvalidDataException("Order must be in PENDING state to update.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("PROCESSING");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }

    public Order updateProcessingOrder(Order order) {
        if (!order.getStatus().getName().equals("PROCESSING")) {
            throw new InvalidDataException("Order must be in PROCESSING state to update.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("SHIPPED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }

    public Order updateShippedOrder(Order order) {
        if (!order.getStatus().getName().equals("SHIPPED")) {
            throw new InvalidDataException("Order must be in SHIPPED state to mark as DELIVERED.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("DELIVERED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }

    public Order updateDeliveredOrder(Order order) {
        if (!order.getStatus().getName().equals("DELIVERED")) {
            throw new InvalidDataException("Order must be in DELIVERED state to complete.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("COMPLETED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }

    public Order updateCanceledOrder(Order order) {
        if (order.getStatus().getName().equals("DELIVERED") || order.getStatus().getName().equals("COMPLETED")) {
            throw new InvalidDataException("Cannot cancel an order that has already been delivered or completed.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("CANCELED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        // TODO: Trigger refund process if payment was already made
        return order;
    }

    public Order updateReturnedOrder(Order order) {
        if (!order.getStatus().getName().equals("DELIVERED")) {
            throw new InvalidDataException("Only delivered orders can be returned.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("RETURNED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        // TODO: Handle inventory restocking if applicable
        return order;
    }

    public Order updateRefundedOrder(Order order) {
        if (!order.getStatus().getName().equals("RETURNED") && !order.getStatus().getName().equals("CANCELED")) {
            throw new InvalidDataException("Only returned or canceled orders can be refunded.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("REFUNDED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        // TODO: Call payment gateway to issue refund
        return order;
    }

    public Order updateOnHoldOrder(Order order) {
        if (order.getStatus().getName().equals("SHIPPED") || order.getStatus().getName().equals("DELIVERED")) {
            throw new InvalidDataException("Cannot place an already shipped or delivered order on hold.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("ON_HOLD");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }

    public Order updateCompletedOrder(Order order) {
        if (! order.getStatus().getName().equals("DELIVERED")) {
            throw new InvalidDataException("Only delivered orders can be marked as completed.");
        }
        OrderStatuses status = new OrderStatuses();
        status.setName("COMPLETED");
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }
}


/*
public class ProcessOrders {
    @Autowired
    BooksClient booksClient;
    public Order addNewOrder(Order order){
        int bookId = order.getBookId();
        BookDto bookDto = booksClient.getBooksDetails(bookId);
        if(bookDto.getQuantity() - order.getQuantity() >= 0){
            bookDto.setQuantity(bookDto.getQuantity() - order.getQuantity());
            booksClient.updateBookQuantity(bookDto, bookDto.getId());
        }
        else {
            throw new BookNotFoundException("Book is not available now");
        }
        // status:processing shipping [set shipper, time for shipping] -> confirm order

        return order;
    }
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

    public Order updateDeliveredOrder(Order order) {
        return  order;
    }
}
*/
