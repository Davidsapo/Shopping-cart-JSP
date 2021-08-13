package com.shopping.cart.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {

    private List<CartItemDTO> cartItems;

    private BigDecimal totalPrice = BigDecimal.ZERO;


}
