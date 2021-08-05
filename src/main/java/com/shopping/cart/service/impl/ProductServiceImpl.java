package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidator productValidator;

    @Override
    public Product createProduct(Product product) {
        productValidator.validate(product);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Product updatePrice(Long id, BigDecimal price) {
        productValidator.checkIfExists(id);
        productValidator.validatePrice(price);
        Product product = productRepository.getById(id);
        product.setPrice(price);
        return product;
    }

    @Override
    public void delete(Long id) {
        productValidator.checkIfExists(id);
        productRepository.deleteById(id);
    }
}
