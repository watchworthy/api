package com.watchworthy.api.controller;

import com.watchworthy.api.dto.PersonDTO;
import com.watchworthy.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void savePerson(@RequestBody PersonDTO personDTO) {
        personService.save(personDTO);
    }

    @PutMapping("/{personId}")
    public void updatePerson(@PathVariable Integer personId, @RequestBody PersonDTO personDTO) {
        personService.update(personId, personDTO);
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Integer id) {
        return personService.getPerson(id);
    }

    @GetMapping
    public List<PersonDTO> getPersons() {
        return personService.getPersons();
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.delete(id);
    }
}
