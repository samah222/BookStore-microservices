package com.samah.userservice.exception;

public class InvalidUserDataException extends RuntimeException{
    public InvalidUserDataException(String message){
        super(message);
    }
}
