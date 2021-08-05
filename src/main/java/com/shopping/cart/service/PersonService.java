package com.shopping.cart.service;

import com.shopping.cart.entity.Person;

import java.util.List;

public interface PersonService {

    Person addPerson(Person person);

    List<Person> getAllPersons();

    Person updatePerson(Long id, String name, String surname);

    void deletePerson(Long id);
}
