package com.shopping.cart.dto;

import com.shopping.cart.entity.CartItem;
import lombok.Data;

@Data
public class ProductInCartDTO {

    private Long id;
    private ProductDTO product;
    private int quantity;

    public ProductInCartDTO(CartItem productInCart) {
        id = productInCart.getId();
        product = new ProductDTO(productInCart.getProduct());
        quantity = productInCart.getQuantity();
    }
}
