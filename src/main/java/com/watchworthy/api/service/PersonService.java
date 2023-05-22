package com.watchworthy.api.service;

import com.watchworthy.api.dto.PersonDTO;

import java.util.List;

public interface PersonService {
   void save(PersonDTO personDTO);
   void update(Integer personId ,PersonDTO personDTO);
   PersonDTO getPerson(Integer id);
   List<PersonDTO> getPersons();
   void delete(Integer id);
}
