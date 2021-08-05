package com.shopping.cart.dto;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO {

    private Long id;
    private Long personId;
    private List<ProductInCartDTO> products;
    private BigDecimal totalPrice;

    public CartDTO(Cart cart) {
        id = cart.getId();
        if (cart.getPerson() != null) {
            personId = cart.getPerson().getId();
        }
        totalPrice = cart.getTotalPrice();
        products = new ArrayList<>();
        if (cart.getCartItems() != null) {
            for (CartItem productInCart : cart.getCartItems()) {
                products.add(new ProductInCartDTO(productInCart));
            }
        }
    }
}
