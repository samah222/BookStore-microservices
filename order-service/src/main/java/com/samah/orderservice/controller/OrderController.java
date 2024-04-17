package com.samah.orderservice.controller;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.model.Book;
import com.samah.orderservice.model.Order;
import com.samah.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @Autowired
    BooksClient booksClient;
    @Autowired
    OrderService orderService;
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id){
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<Book> getAllOrdersforBook(@PathVariable int bookId){
        return new ResponseEntity<>(booksClient.getBooksdetails(bookId), HttpStatus.OK);
    }

}
