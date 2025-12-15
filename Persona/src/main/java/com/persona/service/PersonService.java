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
		
		public List<PersonaModel> getPeople() throws Exception {
			try {
				return personRepository.findAll();				
			} catch (Exception e) {
				throw new Exception("Error al traer las personas");
			}
		}
		
		public void postPeople(PersonaModel personaModel) throws Exception {
			try {
				personRepository.save(personaModel);
			} catch (Exception e) {
				throw new Exception("Error al mandar las personas");
			}
		}
		public PersonaModel findById(Integer id) throws Exception {
			try {
				return personRepository.findById(id).get();
			} catch (Exception e) {
				throw new Exception("Error al buscar las personas");
			}
		}
		
		public void updatePeople(PersonaModel personaModel) throws Exception {
			try {
				personRepository.save(personaModel);
			} catch (Exception e) {
				throw new Exception("Error al actualizar las personas");
			}
		}
		
		public void deletePeople(Integer id) throws Exception {
			try {
				personRepository.deleteById(id);
			} catch (Exception e) {
				throw new Exception("Error al eliminar las personas");
			}
		}
}
