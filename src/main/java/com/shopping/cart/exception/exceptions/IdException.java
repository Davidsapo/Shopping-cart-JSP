package com.shopping.cart.exception.exceptions;

public class IdException extends RuntimeException{

    private IdException(String message) {
        super(message);
    }

    public static IdException negativeId(){
        return new IdException("Id can not be negative.");
    }

    public static IdException noElementWithId(String elementName, Long id){
        return new IdException(String.format("%s with id %d not found.", elementName, id));
    }
}
