package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.PersonDTO;
import com.watchworthy.api.entity.Person;
import com.watchworthy.api.repository.PersonRepository;
import com.watchworthy.api.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void save(PersonDTO personDTO) {
        System.out.println(personDTO.getGender());
        personRepository.save(convertToEntity(personDTO));
    }

    @Override
    public void update(Integer personId, PersonDTO personDTO) {
        Person person = personRepository.findById(personId).orElse(null);
        if (person != null) {
            personDTO.setId(personId);
            personRepository.save(convertToEntity(personDTO));
        }
    }

    @Override
    public PersonDTO getPerson(Integer id) {
        Person person = personRepository.findById(id).orElse(null);
        return person != null ? convertToDto(person) : null;
    }

    @Override
    public List<PersonDTO> getPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        personRepository.findById(id).ifPresent(person -> personRepository.deleteById(id));
    }

    public PersonDTO convertToDto(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertToEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
