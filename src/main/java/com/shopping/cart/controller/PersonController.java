package com.shopping.cart.controller;

import com.shopping.cart.entity.Person;
import com.shopping.cart.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("add")
    public ResponseEntity<Person> add(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("list")
    public ResponseEntity<List<Person>> list() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Person> update(@PathVariable long id, String name, String surname){
        return ResponseEntity.ok(personService.updatePerson(id, name, surname));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        return ResponseEntity.ok(personService.deletePerson(id));
    }
}
