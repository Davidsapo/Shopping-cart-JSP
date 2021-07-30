package com.shopping.cart.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        name = "email_unique",
        columnNames = "email"))
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @OneToMany(orphanRemoval = true, mappedBy = "person", cascade = CascadeType.REMOVE)
    private List<Cart> carts;

}
