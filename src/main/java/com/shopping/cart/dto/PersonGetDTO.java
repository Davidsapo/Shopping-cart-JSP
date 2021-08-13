package com.shopping.cart.dto;

import com.shopping.cart.enums.Role;
import lombok.Data;

@Data
public class PersonGetDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Role role;

    private String email;

}
