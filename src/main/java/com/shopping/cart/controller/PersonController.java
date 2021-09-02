package com.shopping.cart.controller;

import com.shopping.cart.dto.PersonGetDTO;
import com.shopping.cart.dto.PersonPostDTO;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("shopping-cart/person")
public class PersonController {

    private final PersonService personService;
    private final Mapper mapper;

    @Autowired
    public PersonController(PersonService personService, Mapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("person", new PersonPostDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid PersonPostDTO personPostDTO) {
        personService.registerPerson(personPostDTO);
        return "redirect:/shopping-cart/person/profile";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        model.addAttribute("person", mapper.personToPersonGetDto(personService.getAuthorizedPerson()));
        return "profile";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<PersonGetDTO> persons = personService.getAllPersons();
        model.addAttribute("persons", persons);
        model.addAttribute("personCount", persons.size());
        return "personList";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        personService.deletePerson(id);
        return "redirect:/shopping-cart/person/list";
    }
}
