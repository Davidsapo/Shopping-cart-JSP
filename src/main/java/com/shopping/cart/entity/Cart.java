package com.shopping.cart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private List<ProductInCart> products = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Cart(Person person) {
        this.person = person;
    }

    public void refreshTotalPrice() {
        totalPrice = BigDecimal.ZERO;
        for (ProductInCart productInChart : products) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(productInChart.getProduct().getPrice() * productInChart.getQuantity()));
        }
    }
}