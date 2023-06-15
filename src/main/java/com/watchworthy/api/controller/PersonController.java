package com.watchworthy.api.controller;

import com.watchworthy.api.dto.PersonDTO;
import com.watchworthy.api.dto.TvShowDTO;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(path = "", method = RequestMethod.GET)
    public PageModel<PersonDTO> getPersons(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size,
            @RequestParam(name = "q", defaultValue = "") String q
    ) {
        return personService.getPersons(page, size, q);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.delete(id);
    }

    @RequestMapping(path = "{personId}/poster",method = RequestMethod.POST)
    public void addPoster(@PathVariable Integer personId,@RequestParam("file") MultipartFile file){
        personService.addPoster(personId,file);
    }
}
