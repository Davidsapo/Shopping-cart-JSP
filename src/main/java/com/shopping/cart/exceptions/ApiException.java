package com.shopping.cart.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiException {

    private HttpStatus httpStatus;
    private Integer errorCode;
    private String message;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime localDateTime;

    public ApiException(HttpStatus httpStatus, Integer errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
        localDateTime = LocalDateTime.now();
    }
}
