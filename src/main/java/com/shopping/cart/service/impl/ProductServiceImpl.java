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
    public Product updatePrice(long id, Double price) {
        productValidator.checkIfExists(id);
        productValidator.validatePrice(price);
        Product product = productRepository.getById(id);
        product.setPrice(price);
        return product;
    }

    @Override
    public String delete(long id) {
       productValidator.checkIfExists(id);
        try {
            productRepository.deleteById(id);
        } catch (Exception exception) {
            throw new ProductException("Can not delete product because it presents in carts!");
        }
        return "Deleted";
    }
}
