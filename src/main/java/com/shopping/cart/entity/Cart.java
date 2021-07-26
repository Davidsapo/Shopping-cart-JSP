package com.shopping.cart.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Embeddable
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ElementCollection
    private List<ProductInCart> products;
    private long personId;
    private double totalPrice;

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
        totalPrice = 0;
        for (ProductInCart productInChart : products) {
            totalPrice += productInChart.getProduct().getPrice() * productInChart.getQuantity();
        }
    }
}
