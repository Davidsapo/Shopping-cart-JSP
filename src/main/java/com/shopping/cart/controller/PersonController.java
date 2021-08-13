package com.shopping.cart.controller;

import com.shopping.cart.dto.PersonPostDTO;
import com.shopping.cart.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("shopping-cart/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("person", new PersonPostDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid PersonPostDTO personPostDTO) {
        personService.addPerson(personPostDTO);
        return "redirect:/shopping-cart/person/profile";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        model.addAttribute("person", personService.getAuthorizedPersonGetDTO());
        return "profile";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("personCount", personService.getAllPersons().size());
        return "personList";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        personService.deletePerson(id);
        return "redirect:/shopping-cart/person/list";
    }
}
