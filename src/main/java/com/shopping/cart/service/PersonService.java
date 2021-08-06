package com.shopping.cart.service;

import com.shopping.cart.entity.Person;
import com.shopping.cart.request.UpdatePersonRequest;

import java.util.List;

public interface PersonService {

    Person addPerson(Person person);

    List<Person> getAllPersons();

    Person updatePerson(Long id, UpdatePersonRequest updatePersonRequest);

    void deletePerson(Long id);
}
