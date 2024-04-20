package com.samah.orderservice.controller;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.dto.OrderDto;
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
    public ResponseEntity<OrderDto> getOrder(@PathVariable int id){
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return new ResponseEntity<List<OrderDto>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int id){
        return new ResponseEntity<>(orderService.updateOrder(orderDto,id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<Book> getAllOrdersForBook(@PathVariable int bookId){
        return new ResponseEntity<>(booksClient.getBooksDetails(bookId), HttpStatus.OK);
    }

}
