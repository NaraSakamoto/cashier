package com.cashier.controller.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message){
        super(message);
    }

}
