package com.shopping.cart.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CartItem {

    @Id
    @SequenceGenerator(
            name = "product_in_cart_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_in_cart_sequence")
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}