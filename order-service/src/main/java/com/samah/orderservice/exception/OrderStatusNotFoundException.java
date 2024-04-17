package com.samah.orderservice.exception;

public class OrderStatusNotFoundException extends RuntimeException{
    public OrderStatusNotFoundException(String message){
        super(message);
    }
}
