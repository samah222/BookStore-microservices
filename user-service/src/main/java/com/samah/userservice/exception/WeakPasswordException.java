package com.samah.userservice.exception;

public class WeakPasswordException extends RuntimeException{
    public WeakPasswordException(String message){
        super(message);
    }

}
