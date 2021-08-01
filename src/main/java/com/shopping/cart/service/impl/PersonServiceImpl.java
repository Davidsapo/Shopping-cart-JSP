package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Person;
import com.shopping.cart.exceptions.PersonException;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonValidator personValidator;

    @Override
    public void addPerson(Person person) {
        if (personValidator.validate(person)) {
            personRepository.save(person);
        }
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public Person updatePerson(long id, String name, String surname) {
        personValidator.checkIfExists(id);
        personValidator.validateName(name);
        personValidator.validateSurname(surname);
        Person person = personRepository.getById(id);
        if (name != null) {
            person.setFirstName(name);
        }
        if (surname != null) {
            person.setLastName(surname);
        }
        return person;
    }

    @Override
    public String deletePerson(long id) {
        personValidator.checkIfExists(id);
        personRepository.deleteById(id);
        return "Deleted";
    }
}
