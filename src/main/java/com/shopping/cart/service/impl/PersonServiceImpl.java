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
import java.util.Optional;

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
        if (!personRepository.existsById(id)) {
            throw new PersonException("No person with such id!");
        }
        if (name != null && name.isEmpty()) {
            throw new PersonException("Name can not be empty!");
        }
        if (surname != null && surname.isEmpty()) {
            throw new PersonException("Surname can not be empty!");
        }
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (name != null) {
            optionalPerson.get().setName(name);
        }
        if (surname != null) {
            optionalPerson.get().setSurname(surname);
        }
        return optionalPerson.get();
    }

    @Override
    public String deletePerson(long id) {
        if (!personRepository.existsById(id)) {
            return "Person with id " + id + " does not exists.";
        }
        personRepository.deleteById(id);
        return "Deleted";
    }
}
