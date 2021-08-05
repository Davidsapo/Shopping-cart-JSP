package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Person;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonValidator personValidator;

    @Override
    public Person addPerson(Person person) {
        personValidator.validate(person);
        person.setCart(new Cart());
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public Person updatePerson(Long id, String name, String surname) {
        personValidator.checkIfExists(id);
        personValidator.validateName(name);
        personValidator.validateSurname(surname);
        Person person = personRepository.getById(id);
        if (Objects.nonNull(name)) {
            person.setFirstName(name);
        }
        if (Objects.nonNull(surname)) {
            person.setLastName(surname);
        }
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        personValidator.checkIfExists(id);
        personRepository.deleteById(id);
    }
}
