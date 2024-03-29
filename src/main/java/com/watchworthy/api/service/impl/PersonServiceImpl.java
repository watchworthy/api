package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.PersonDTO;
import com.watchworthy.api.entity.Person;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.*;
import com.watchworthy.api.service.PersonService;
import com.watchworthy.api.service.StorageService;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final MoviePersonRepository moviePersonRepository;
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;
    private final TvShowPersonRepository tvShowPersonRepository;
    private final StorageService storageService;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, MovieRepository movieRepository, TvShowRepository tvShowRepository, MoviePersonRepository moviePersonRepository, MovieRepository movieRepository1, TvShowRepository tvShowRepository1, TvShowPersonRepository tvShowPersonRepository, StorageService storageService, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.moviePersonRepository = moviePersonRepository;
        this.movieRepository = movieRepository1;
        this.tvShowRepository = tvShowRepository1;
        this.tvShowPersonRepository = tvShowPersonRepository;
        this.storageService = storageService;
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

    @Override
    public void addPoster(Integer personId, MultipartFile file) {
        String posterPath = storageService.uploadFile(file);
        Person person = personRepository.findById(personId).orElse(null);
        if(person != null){
            person.setPosterPath(posterPath);
            personRepository.save(person);
        }
    }

    public PersonDTO convertToDto(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertToEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
