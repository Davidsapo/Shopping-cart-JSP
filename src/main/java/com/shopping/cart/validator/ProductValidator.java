package com.shopping.cart.validator;

import com.shopping.cart.entity.Product;
import com.shopping.cart.exceptions.ProductException;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    @Autowired
    private ProductRepository productRepository;

    public boolean validate(Product product) {
        if (product.getName().isEmpty()) {
            throw new ProductException("Product name can not be empty!");
        }
        if (product.getPrice() < 0) {
            throw new ProductException("Product price can not be less then 0!");
        }
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ProductException("Product with such name is already exists!");
        }
        return true;
    }
}
