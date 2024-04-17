package com.samah.orderservice.controller;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.model.Book;
import com.samah.orderservice.entity.Order;
import com.samah.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.application.version}")
    private String applicationVersion;
    @GetMapping("/info")
    public String getInfo(){
        return "This application is "+applicationName+" and this version: "+applicationVersion ;
    }
    @Autowired
    BooksClient booksClient;
    @Autowired
    OrderService orderService;
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id){
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable int id){
        return new ResponseEntity<>(orderService.updateOrder(order,id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<Book> getAllOrdersForBook(@PathVariable int bookId){
        return new ResponseEntity<>(booksClient.getBooksDetails(bookId), HttpStatus.OK);
    }

}
