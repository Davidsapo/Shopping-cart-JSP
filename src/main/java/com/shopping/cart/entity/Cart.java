package com.shopping.cart.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence")
    private Long id;

    @ManyToOne
    private Person person;

    @OneToMany
    private List<ProductInCart> products;

    private Double totalPrice;

    public void addProduct(ProductInCart product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        countTotal();
    }

    public void removeProduct(ProductInCart product) {
        if (products != null) {
            products.remove(product);
            countTotal();
        }
    }

    private void countTotal() {
        totalPrice = 0.0;
        for (ProductInCart productInChart : products) {
            totalPrice += productInChart.getProduct().getPrice() * productInChart.getQuantity();
        }
    }
}