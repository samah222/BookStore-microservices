package com.samah.userservice.exception;

public class UserNotActivatedException extends RuntimeException{
    public UserNotActivatedException(String message){
        super(message);
    }
}
