package com.shopping.cart.controller;

import com.shopping.cart.dto.PersonGetDTO;
import com.shopping.cart.dto.PersonPostDTO;
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

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    public ResponseEntity<PersonGetDTO> add(@RequestBody @Valid PersonPostDTO personPostDTO) {
        return ResponseEntity.ok(personService.addPerson(personPostDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PersonGetDTO>> list() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonGetDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UpdatePersonRequest updatePersonRequest) {
        return ResponseEntity.ok(personService.updatePerson(id, updatePersonRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted successfully.");
    }
}
