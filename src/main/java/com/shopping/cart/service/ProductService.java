package com.shopping.cart.service;

import com.shopping.cart.entity.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    List<Product> getProducts();

    Product updatePrice(long price, int quantity);

    String delete(long id);
}
