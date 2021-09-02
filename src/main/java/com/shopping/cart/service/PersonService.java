package com.shopping.cart.service;

import com.shopping.cart.dto.PersonGetDTO;
import com.shopping.cart.dto.PersonPostDTO;
import com.shopping.cart.entity.Person;
import com.shopping.cart.request.UpdatePersonRequest;

import java.util.List;

public interface PersonService {

    PersonGetDTO registerPerson(PersonPostDTO personPostDTO);

    List<PersonGetDTO> getAllPersons();

    Person getAuthorizedPerson();

    PersonGetDTO getAuthorizedPersonDto();

    void deletePerson(Long id);
}
