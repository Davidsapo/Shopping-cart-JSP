package com.shopping.cart.controller;

import com.shopping.cart.dto.PersonDTO;
import com.shopping.cart.entity.Person;
import com.shopping.cart.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("add")
    public ResponseEntity<PersonDTO> add(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok(new PersonDTO(person));
    }

    @GetMapping("list")
    public ResponseEntity<List<PersonDTO>> list() {
        List<PersonDTO> personList = new ArrayList<>();
        for (Person person : personService.getAllPersons()) {
            personList.add(new PersonDTO(person));
        }
        return ResponseEntity.ok(personList);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable long id, String name, String surname) {
        return ResponseEntity.ok(new PersonDTO(personService.updatePerson(id, name, surname)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(personService.deletePerson(id));
    }
}
