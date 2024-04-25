package com.samah.orderservice.service;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
}
