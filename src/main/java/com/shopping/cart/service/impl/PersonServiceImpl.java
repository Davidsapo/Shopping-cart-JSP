package com.shopping.cart.service.impl;

import com.shopping.cart.dto.PersonGetDTO;
import com.shopping.cart.dto.PersonPostDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Person;
import com.shopping.cart.enums.Role;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.request.UpdatePersonRequest;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final IdValidator idValidator;

    private final Mapper mapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, IdValidator idValidator, Mapper mapper) {
        this.personRepository = personRepository;
        this.idValidator = idValidator;
        this.mapper = mapper;
    }

    @Override
    public PersonGetDTO addPerson(PersonPostDTO personPostDTO) {
        if (personRepository.existsByEmailIgnoreCase(personPostDTO.getEmail())) {
            throw new NonUniqueValueException("Person", "email", personPostDTO.getEmail());
        }
        Person person = mapper.personPostDTOToPerson(personPostDTO);
        person.setCart(new Cart());
        person.setRole(Role.USER);
        return mapper.personToPersonGetDto(personRepository.save(person));
    }

    @Override
    public List<PersonGetDTO> getAllPersons() {
        return mapper.personsToPersonGetDTOs(personRepository.findAll());
    }

    @Override
    public Person getPerson(Long id) {
        idValidator.validPersonId(id);
        return personRepository.getById(id);
    }

    @Override
    public PersonGetDTO getAuthorizedPersonGetDTO() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = personRepository.getByUsername(userName);
        return mapper.personToPersonGetDto(person);
    }

    @Override
    public Person getAuthorizedPerson() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return personRepository.getByUsername(userName);
    }

    @Transactional
    @Override
    public PersonGetDTO updatePerson(Long id, UpdatePersonRequest updatePersonRequest) {
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
        return mapper.personToPersonGetDto(person);
    }

    @Override
    public void deletePerson(Long id) {
        idValidator.validPersonId(id);
        personRepository.deleteById(id);
    }
}
