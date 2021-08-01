package com.shopping.cart.dto;

import com.shopping.cart.entity.Product;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
