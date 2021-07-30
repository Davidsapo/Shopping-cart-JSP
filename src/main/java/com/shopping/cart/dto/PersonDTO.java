package com.shopping.cart.dto;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Person;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CartDTO> carts;

    public PersonDTO(Person person) {
        id = person.getId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        email = person.getEmail();
        carts = new ArrayList<>();
        if (person.getCarts() != null) {
            for (Cart cart : person.getCarts()) {
                carts.add(new CartDTO(cart));
            }
        }
    }
}
