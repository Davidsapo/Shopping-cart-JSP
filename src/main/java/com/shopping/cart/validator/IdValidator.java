package com.shopping.cart.validator;

import com.shopping.cart.exceptions.ProductException;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdValidator {

    @Autowired
    private ProductRepository productRepository;

    public void validProductId(Long productId) {
        if (productId < 0) {
            throw new ProductException("Id can not be less than zero.");
        }
        if (!productRepository.existsById(productId)) {
            throw new ProductException("Product with id " + productId + " does not exist.");
        }
    }
}
