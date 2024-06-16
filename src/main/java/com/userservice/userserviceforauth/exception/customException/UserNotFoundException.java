package com.userservice.userserviceforauth.exception.customException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
