package com.watchworthy.api.service;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.PersonDTO;
import com.watchworthy.api.dto.TvShowDTO;
import com.watchworthy.api.model.PageModel;

import java.util.List;

public interface PersonService {
   void save(PersonDTO personDTO);
   void update(Integer personId ,PersonDTO personDTO);
   PersonDTO getPerson(Integer id);
   PageModel<PersonDTO> getPersons(Integer page, Integer size, String q);
   void delete(Integer id);
}
