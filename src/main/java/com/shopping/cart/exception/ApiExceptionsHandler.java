package com.shopping.cart.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionsHandler {

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleValidateExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getAllErrors().forEach(e -> errorMessage.append(String.format("%s : %s; ", ((FieldError) e).getField(), e.getDefaultMessage())));
        return new ResponseEntity<>(new ApiException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiException> handleDatabaseExceptions(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ApiException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "Non-unique value: " + ex.getSQLException().getMessage()), HttpStatus.BAD_REQUEST);
    }*/
}
