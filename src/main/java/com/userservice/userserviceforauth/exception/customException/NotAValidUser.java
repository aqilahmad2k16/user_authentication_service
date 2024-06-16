package com.userservice.userserviceforauth.exception.customException;

public class NotAValidUser extends RuntimeException{
    public NotAValidUser(String message){
        super(message);
    }
}
