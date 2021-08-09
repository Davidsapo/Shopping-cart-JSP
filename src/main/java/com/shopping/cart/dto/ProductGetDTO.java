package com.shopping.cart.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductGetDTO {

    private Long id;

    private String name;

    private BigDecimal price;

}
