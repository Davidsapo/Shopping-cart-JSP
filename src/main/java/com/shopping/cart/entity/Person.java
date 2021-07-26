package com.shopping.cart.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @ElementCollection
    private List<Cart> carts;

    public void addCart(Cart cart) {
        carts.add(cart);
    }
}
