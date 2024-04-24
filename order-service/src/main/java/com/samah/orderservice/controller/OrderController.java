package com.samah.orderservice.controller;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.dto.BookDto;
import com.samah.orderservice.dto.OrderDto;
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
    public ResponseEntity<OrderDto> getOrder(@PathVariable int id){
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return new ResponseEntity<List<OrderDto>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.addOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int id){
        return new ResponseEntity<>(orderService.updateOrder(orderDto,id), HttpStatus.OK);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@RequestBody OrderDto orderDto, @PathVariable int id){
        return new ResponseEntity<>(orderService.cancelOrder(orderDto,id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<BookDto> getAllOrdersForBook(@PathVariable int bookId){
        return new ResponseEntity<>(booksClient.getBooksDetails(bookId), HttpStatus.OK);
    }

}
