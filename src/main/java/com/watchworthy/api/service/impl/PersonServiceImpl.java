package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.PersonDTO;
import com.watchworthy.api.dto.TvShowDTO;
import com.watchworthy.api.entity.Person;
import com.watchworthy.api.entity.TvShow;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.PersonRepository;
import com.watchworthy.api.repository.PersonSpecification;
import com.watchworthy.api.repository.TvShowSpecification;
import com.watchworthy.api.service.PersonService;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public PageModel<PersonDTO> getPersons(Integer page, Integer size, String q) {
        page = page != null ? Math.max(page - 1, 0) : 0;
        size = size != null && size > 0 ? size : 20;

        Pageable pageable = PageRequest.of(page, size);

        Specification<Person> specification = StringUtils.isBlank(q) ? null : new PersonSpecification(q);
        Page<Person> persons = personRepository.findAll(specification, pageable);

        return PageModel.<PersonDTO>builder()
                .total(persons.getTotalElements())
                .size(persons.getSize())
                .page(persons.getNumber() + 1)
                .data(persons.map(this::convertToDto).getContent())
                .build();
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
