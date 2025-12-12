package com.persona.service;

import java.util.List;
import java.util.Optional;

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
		
		public void postPeople(PersonaModel personaModel) {
			personRepository.save(personaModel);
		}
		public PersonaModel editPeople(Integer id) {
			return personRepository.findById(id).get();
		}
		public void deletePeople(Integer id) {
			personRepository.deleteById(id);
		}
}
