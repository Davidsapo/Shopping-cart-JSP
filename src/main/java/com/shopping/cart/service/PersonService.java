package com.shopping.cart.service;

import com.shopping.cart.entity.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);

    List<Person> getAllPersons();

    Person updatePerson(long id, String name, String surname);

    String deletePerson(long id);
}
