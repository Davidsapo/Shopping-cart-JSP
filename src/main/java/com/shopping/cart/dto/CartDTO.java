package com.shopping.cart.dto;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.ProductInCart;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private Long id;
    private Long personId;
    private List<ProductInCart> products;
    private Double totalPrice;

    public CartDTO(Cart cart) {
        id = cart.getId();
        personId = cart.getPerson().getId();
        products = cart.getProducts();
        totalPrice = cart.getTotalPrice();
    }
}
