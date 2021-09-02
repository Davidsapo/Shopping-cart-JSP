package com.shopping.cart.service.impl;

import com.shopping.cart.dto.PersonGetDTO;
import com.shopping.cart.dto.PersonPostDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Person;
import com.shopping.cart.enums.Role;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import com.shopping.cart.logger.AdvancedLogger;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.PersonRepository;
import com.shopping.cart.service.PersonService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final AdvancedLogger log = new AdvancedLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;
    private final IdValidator idValidator;
    private final Mapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, IdValidator idValidator, Mapper mapper,
                             BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.idValidator = idValidator;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PersonGetDTO registerPerson(PersonPostDTO personPostDTO) {
        if (personRepository.existsByEmailIgnoreCase(personPostDTO.getEmail())) {
            RuntimeException exception = new NonUniqueValueException("Person", "email", personPostDTO.getEmail());
            log.warn("Registration fail. Email is already exists.", exception);
            throw exception;
        }
        if (personRepository.existsByUsernameIgnoreCase(personPostDTO.getUsername())) {
            RuntimeException exception = new NonUniqueValueException("Person", "username", personPostDTO.getUsername());
            log.warn("Registration fail. Username is already exists.", exception);
            throw exception;
        }
        Person person = mapper.personPostDTOToPerson(personPostDTO);
        person.setCart(new Cart());
        person.setRole(Role.USER);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person = personRepository.save(person);
        PersonGetDTO getDTO = mapper.personToPersonGetDto(person);
        log.info("Registration successful. New user: " + getDTO);
        return getDTO;
    }

    @Override
    public List<PersonGetDTO> getAllPersons() {
        log.info("Request for person list.");
        return mapper.personsToPersonGetDTOs(personRepository.findAll());
    }

    @Override
    public Person getAuthorizedPerson() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return personRepository.getByUsername(userName);
    }

    @Override
    public PersonGetDTO getAuthorizedPersonDto() {
        return mapper.personToPersonGetDto(getAuthorizedPerson());
    }

    @Override
    public void deletePerson(Long id) {
        try {
            idValidator.validPersonId(id);
            Person person = personRepository.deleteByIdWithReturn(id);
            log.info("User deleted. " + mapper.personToPersonGetDto(person));
        } catch (Exception e) {
            log.warn("Deleting user with id " + id + " fails.", e);
            throw e;
        }
    }
}
