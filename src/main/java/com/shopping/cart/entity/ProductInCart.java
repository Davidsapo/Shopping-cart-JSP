package com.shopping.cart.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Embeddable
public class ProductInCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private Product product;
    private int quantity;
}
