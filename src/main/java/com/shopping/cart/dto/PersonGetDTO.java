package com.shopping.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonGetDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private CartDTO cart;
}
