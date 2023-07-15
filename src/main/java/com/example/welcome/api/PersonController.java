package com.example.welcome.api;

import com.example.welcome.model.Person;
import com.example.welcome.service.PersonService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> showAllPerson() {
        return personService.showAllPerson();
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);

    }


}