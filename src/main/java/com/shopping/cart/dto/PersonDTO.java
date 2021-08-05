package com.shopping.cart.dto;

import com.shopping.cart.entity.Person;
import lombok.Data;

@Data
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CartDTO cart;

    public PersonDTO(Person person) {
        id = person.getId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        email = person.getEmail();
        if (person.getCart() != null) {
            cart = new CartDTO(person.getCart());
        }
    }
}
