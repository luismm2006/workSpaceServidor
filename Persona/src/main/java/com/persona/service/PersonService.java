package com.persona.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.persona.model.PersonaModel;
import com.persona.repository.PersonRepository;

@Service
public class PersonService {

		private final PersonRepository personRepository;

		public PersonService(PersonRepository personRepository) {
			super();
			this.personRepository = personRepository;
		}
		
		public List<PersonaModel> getPeople() {
			return personRepository.findAll();
		}
}
