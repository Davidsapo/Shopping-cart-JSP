package com.shopping.cart.service;

import com.shopping.cart.entity.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    List<Product> getProducts();

    Product updatePrice(long id, Double price);

    String delete(long id);
}
