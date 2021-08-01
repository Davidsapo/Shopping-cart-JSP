package com.shopping.cart.dto;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.ProductInCart;
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
        personId = cart.getPerson().getId();
        totalPrice = cart.getTotalPrice();
        products = new ArrayList<>();
        for (ProductInCart productInCart : cart.getProducts()) {
            products.add(new ProductInCartDTO(productInCart));
        }
    }
}
