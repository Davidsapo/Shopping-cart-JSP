package com.shopping.cart.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestApiException {

    private HttpStatus httpStatus;
    private String message;
    private int errorCode;

    public RestApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        errorCode = httpStatus.value();
    }
}
