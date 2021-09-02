package com.shopping.cart.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString(exclude = "password")
public class PersonPostDTO {

    @NotNull(message = "First name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name must start with a capital letter.")
    private String firstName;

    @NotNull(message = "Last name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name must start with a capital letter.")
    private String lastName;

    @NotNull(message = "Email required")
    @Email(message = "Invalid email address.")
    private String email;

    @NotNull(message = "Username can not be empty.")
    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = "Username can contains upper case and lower case letters, '_', '.' and must be at least 4 characters.")
    private String username;

    @NotNull(message = "Password required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$)[a-zA-Z1-9]{4,8}$", message = "Password must contains at least one letters and digit and must be at least 4 characters.")
    private String password;

}
