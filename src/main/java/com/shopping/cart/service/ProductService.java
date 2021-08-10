package com.shopping.cart.service;

import com.shopping.cart.dto.ProductGetDTO;
import com.shopping.cart.dto.ProductPostDTO;
import com.shopping.cart.entity.Product;
import com.shopping.cart.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductGetDTO createProduct(ProductPostDTO productPostDTO);

    List<ProductGetDTO> getProducts();

    Product getProduct(Long id);

    ProductGetDTO updateProduct(Long id, UpdateProductRequest updateProductRequest);

    void delete(Long id);
}
