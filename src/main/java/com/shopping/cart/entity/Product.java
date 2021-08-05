package com.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@ToString(exclude = "cartItemList")
@Table(uniqueConstraints = @UniqueConstraint(
        name = "product_name_unique",
        columnNames = "name"))
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItemList;
}