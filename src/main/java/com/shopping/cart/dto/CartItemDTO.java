package com.shopping.cart.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private Long id;

    private ProductGetDTO product;

    private Integer quantity;

}
