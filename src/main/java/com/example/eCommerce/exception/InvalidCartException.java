package com.example.eCommerce.exception;

public class InvalidCartException extends Exception{
    public InvalidCartException(String msg){
        super(msg);
    }
}
