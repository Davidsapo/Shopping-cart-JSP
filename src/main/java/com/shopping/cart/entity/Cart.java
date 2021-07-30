package com.shopping.cart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductInCart> products;

    private Double totalPrice;

    public Cart(Person person) {
        this.person = person;
    }

    public void addProduct(ProductInCart product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        countTotal();
    }

    public void removeProduct(Long productId) {
        for (ProductInCart productInCart : products) {
            if (productInCart.getProduct().getId().equals(productId)) {
                products.remove(productInCart);
                countTotal();
                return;
            }
        }
    }

    private void countTotal() {
        totalPrice = 0.0;
        for (ProductInCart productInChart : products) {
            totalPrice += productInChart.getPrice();
        }
    }

    public Double getTotalPrice() {
        if (products != null)
            countTotal();
        return totalPrice;
    }
}