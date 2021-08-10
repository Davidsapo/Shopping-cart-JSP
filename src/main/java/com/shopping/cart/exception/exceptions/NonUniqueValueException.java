package com.shopping.cart.exception.exceptions;

public class NonUniqueValueException extends RuntimeException {

    public NonUniqueValueException(String entityName, String columnName, String value) {
        super(String.format("%s must be unique. %s with %s '%s' is already exists.", columnName, entityName, columnName, value));
    }

    public NonUniqueValueException(String value) {
        super(String.format("Value '%s' is already exists.", value));
    }

    public NonUniqueValueException() {
        super("Value is not unique.");
    }
}
