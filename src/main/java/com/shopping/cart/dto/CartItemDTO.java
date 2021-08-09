package com.shopping.cart.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private ProductGetDTO product;

    private Integer quantity;

}
