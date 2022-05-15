package com.example.dynamicquery.controller;

import com.example.dynamicquery.entity.Person;
import com.example.dynamicquery.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.UUID;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 15.05.2022
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<?> index(
            @RequestParam(required= false) String firstName,
            @RequestParam(required= false) String lastName,
            @RequestParam(required= false) Integer age){

        return ResponseEntity.ok(personService.dynamicPersonQuery(firstName, lastName, age));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> show(@PathVariable UUID id){
        return ResponseEntity.ok(personService.findByPersonId(id));
    }

    @PostMapping
    public ResponseEntity<URI> save(@RequestBody Person person){
        return ResponseEntity.created(personService.save(person)).build();
    }

}
