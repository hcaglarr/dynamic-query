package com.hcaglar.dynamicquery.controller;

import com.hcaglar.dynamicquery.dto.PersonDto;
import com.hcaglar.dynamicquery.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 15.05.2022
 */
@Controller
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String index(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer age, Model model) {
        final var persons = personService.dynamicPersonQuery(firstName, lastName, age);

        model.addAttribute("persons",persons);
        model.addAttribute("person", new PersonDto(firstName, lastName, age));

        return "persons/index";
    }
}