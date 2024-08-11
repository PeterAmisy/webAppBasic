package com.amisyp.basicwebapp.controller;

import com.amisyp.basicwebapp.model.Person;
import com.amisyp.basicwebapp.service.ServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final ServiceImpl personService;

    public PersonController(ServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> Persons() {
        return personService.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> Persons(@RequestBody Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonsbyId(@PathVariable Long id) {
        Person person = personService.findById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Persons(@PathVariable Long id) {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> Persons(@PathVariable Long id, @RequestBody Person person) {
        Person currentPerson = personService.findById(id);
        currentPerson.setName(person.getName());
        currentPerson.setAge(person.getAge());
        currentPerson.setCountry(person.getCountry());
        return new ResponseEntity<>(personService.save(currentPerson), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Person> getPersonsByName(@PathVariable String name) {
        Person person = personService.findByName(name);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}
