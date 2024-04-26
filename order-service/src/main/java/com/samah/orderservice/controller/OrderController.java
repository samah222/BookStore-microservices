package com.samah.orderservice.controller;

import com.samah.orderservice.client.BooksClient;
import com.samah.orderservice.dto.BookDto;
import com.samah.orderservice.dto.OrderDto;
import com.samah.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Tag(name = "Order Controller", description = "All CRUD operations for Order service and many APIs to be used by other micro-services")
public class OrderController {
    @Autowired
    BooksClient booksClient;
    @Autowired
    OrderService orderService;
    @Operation(
            summary = "Get an order",
            description = "Get an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@Parameter(description = "ID of an order to be retrieved",
            required = true) @PathVariable int id){
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Get all orders",
            description = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return new ResponseEntity<List<OrderDto>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @Operation(
            summary = "Add a new order ",
            description = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")
    })
    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Validated @RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.addOrder(orderDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an order",
            description = "Edit an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Validated @RequestBody OrderDto orderDto, @Parameter(description = "ID of order to be updated",
            required = true) @PathVariable int id){
        return new ResponseEntity<>(orderService.updateOrder(orderDto,id), HttpStatus.OK);
    }

    /*
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@RequestBody OrderDto orderDto, @PathVariable int id){
        return new ResponseEntity<>(orderService.cancelOrder(orderDto,id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<BookDto> getAllOrdersForBook(@PathVariable int bookId){
        return new ResponseEntity<>(booksClient.getBooksDetails(bookId), HttpStatus.OK);
    } */

}
