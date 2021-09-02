package com.shopping.cart.entity;

import com.shopping.cart.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "email_unique",
        columnNames = "email"),
        @UniqueConstraint(
                name = "username_unique",
                columnNames = "username")})

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
    @NotNull(message = "First name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name does not match pattern.")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "Last name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name does not match pattern.")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "Email required")
    @Email(message = "Invalid email address.")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Username can not be empty.")
    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = "Username does not match pattern.")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password required")
    private String password;

    @Column(nullable = false)
    @NotNull
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Cart cart;
}
