package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.request.UpdateProductRequest;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IdValidator idValidator;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        idValidator.validProductId(id);
        return productRepository.getById(id);
    }

    @Transactional
    @Override
    public Product updatePrice(Long id, UpdateProductRequest updateProductRequest) {
        idValidator.validProductId(id);
        Product productFromDB = productRepository.getById(id);
        String updatedName = updateProductRequest.getName();
        BigDecimal updatedPrice = updateProductRequest.getPrice();
        if (Objects.nonNull(updatedName)) {
            productFromDB.setName(updatedName);
        }
        if (Objects.nonNull(updatedPrice)) {
            productFromDB.setPrice(updatedPrice);
        }
        return productFromDB;
    }

    @Override
    public void delete(Long id) {
        idValidator.validProductId(id);
        productRepository.deleteById(id);
    }
}
