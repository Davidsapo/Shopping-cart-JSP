package com.shopping.cart.exception;

import com.shopping.cart.exception.exceptions.IdException;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ModelAndView handleValidationException(BindException exception) {
        StringBuilder message = new StringBuilder();
        exception.getFieldErrors().forEach(fieldError -> message.append(String.format("%s : %s ", fieldError.getField(),
                fieldError.getDefaultMessage())));
        RestApiException restApiException = new RestApiException(HttpStatus.BAD_REQUEST, message.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", restApiException);
        modelAndView.setViewName("exception");
        return modelAndView;
    }

    @ExceptionHandler(IdException.class)
    public ModelAndView handleIdException(IdException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.NOT_FOUND, exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", restApiException);
        modelAndView.setViewName("exception");
        return modelAndView;
    }

    @ExceptionHandler(NonUniqueValueException.class)
    public ModelAndView handleNonUniqueValueException(NonUniqueValueException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.CONFLICT, exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", restApiException);
        modelAndView.setViewName("exception");
        return modelAndView;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(NoSuchElementException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.NOT_FOUND, exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", restApiException);
        modelAndView.setViewName("exception");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getClass().getName() + ": " + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", restApiException);
        modelAndView.setViewName("exception");
        return modelAndView;
    }

}
