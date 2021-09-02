package com.shopping.cart.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;


public class AdvancedLogger {

    private final Logger logger;

    public AdvancedLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String message) {
        logger.info(advanceMessage(message));
    }

    public void warn(String message, Throwable exception) {
        logger.warn(advanceMessage(message), exception);
    }

    public void error(String message, Throwable exception) {
        logger.error(advanceMessage(message), exception);
    }

    private String advanceMessage(String message) {
        return message + "\nBy: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
