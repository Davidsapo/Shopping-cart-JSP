package com.shopping.cart.validator;

import com.shopping.cart.entity.Product;
import com.shopping.cart.exceptions.ProductException;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductValidator {

    @Autowired
    private ProductRepository productRepository;

    public void validate(Product product) {
        if (product.getName().isEmpty()) {
            throw new ProductException("Product name can not be empty!");
        }
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ProductException("Product with such name is already exists!");
        }
        validatePrice(product.getPrice());
    }

    public void validatePrice(BigDecimal price) {
        if (price.doubleValue() < 0) {
            throw new ProductException("Product price can not be less then 0!");
        }
    }

    public void checkIfExists(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductException("No product with id " + productId);
        }
    }
}
