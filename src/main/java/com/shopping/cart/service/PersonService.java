package com.shopping.cart.service;

import com.shopping.cart.dto.PersonGetDTO;
import com.shopping.cart.dto.PersonPostDTO;
import com.shopping.cart.entity.Person;
import com.shopping.cart.request.UpdatePersonRequest;

import java.util.List;

public interface PersonService {

    PersonGetDTO addPerson(PersonPostDTO personPostDTO);

    List<PersonGetDTO> getAllPersons();

    Person getPerson(Long id);

    PersonGetDTO updatePerson(Long id, UpdatePersonRequest updatePersonRequest);

    void deletePerson(Long id);
}
