package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Product;
import com.shopping.cart.exceptions.ProductException;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidator productValidator;

    @Override
    public void createProduct(Product product) {
        if (productValidator.validate(product)) {
            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Product updatePrice(long id, int price) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductException("No product with id " + id);
        }
        if (price < 0) {
            throw new ProductException("Product price can not be less then 0!");
        }
        productOptional.get().setPrice(price);
        return productOptional.get();
    }

    @Override
    public String delete(long id) {
        if (!productRepository.existsById(id))
            return "No product with id " + id;
        productRepository.deleteById(id);
        return "Deleted";
    }
}
