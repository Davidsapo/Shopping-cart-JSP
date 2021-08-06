package com.shopping.cart.service;

import com.shopping.cart.entity.Product;
import com.shopping.cart.request.UpdateProductRequest;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getProducts();

    Product updatePrice(Long id, UpdateProductRequest updateProductRequest);

    void delete(Long id);
}
