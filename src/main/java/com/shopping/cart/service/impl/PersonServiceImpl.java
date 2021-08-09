package com.shopping.cart.service.impl;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Person;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.request.UpdatePersonRequest;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.validator.IdValidator;
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
    private IdValidator idValidator;

    @Override
    public Person addPerson(Person person) {
        person.setCart(new Cart());
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPerson(Long id) {
        idValidator.validPersonId(id);
        return personRepository.getById(id);
    }

    @Transactional
    @Override
    public Person updatePerson(Long id, UpdatePersonRequest updatePersonRequest) {
        idValidator.validPersonId(id);
        Person person = personRepository.getById(id);
        String firstName = updatePersonRequest.getFirstName();
        String lastName = updatePersonRequest.getLastName();
        if (Objects.nonNull(firstName)) {
            person.setFirstName(firstName);
        }
        if (Objects.nonNull(lastName)) {
            person.setLastName(lastName);
        }
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        idValidator.validPersonId(id);
        personRepository.deleteById(id);
    }
}
