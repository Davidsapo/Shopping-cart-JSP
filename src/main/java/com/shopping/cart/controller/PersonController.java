package com.shopping.cart.controller;

import com.shopping.cart.entity.Person;
import com.shopping.cart.request.UpdatePersonRequest;
import com.shopping.cart.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<Person> add(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Person>> list() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") Long id, @RequestBody @Valid UpdatePersonRequest updatePersonRequest) {
        return ResponseEntity.ok(personService.updatePerson(id, updatePersonRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted successfully.");
    }
}
