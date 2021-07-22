package com.shopping.cart.validator;

import com.shopping.cart.entity.Person;
import com.shopping.cart.exceptions.PersonException;
import com.shopping.cart.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {

    @Autowired
    private PersonRepository personRepository;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public boolean validate(Person person) {
        if (person.getName().isEmpty()) {
            throw new PersonException("Name can not be empty!");
        }
        if (person.getSurname().isEmpty()) {
            throw new PersonException("Surname can not be empty!");
        }
        if (!person.getEmail().matches(EMAIL_PATTERN)) {
            throw new PersonException("Wrong email!");
        }
        if (personRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new PersonException("Person with such email is already exists!");
        }
        return true;
    }
}
