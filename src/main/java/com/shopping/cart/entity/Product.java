package com.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull(message = "Product name required.")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Product name does not match pattern.")
    private String name;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Product price can not be less then 0!")
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItemList;
}