package com.jacaranda.accesoDatos.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.jacaranda.accesoDatos.model.Person;
import com.jacaranda.accesoDatos.repository.PersonRepository;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

     public List<Person> findAll() throws Exception {
    	 try {
        	 return personRepository.findAll();
		} catch (Exception e) {
			throw new Exception("Error de acceso a la base de datos. Contacte con el administrador");
		}
    }

    public Person findById(Integer id) throws Exception {
    		return personRepository.findById(id).orElseThrow(() -> new Exception("Persona con id " + id + " no encontrado"));
        
    }

    public Person save(Person person) throws Exception {
    	try {
        	return personRepository.save(person);
		} catch (Exception e) {
			throw new Exception("Error al añadir el registro");
		}
    }

    public Person update(Integer id, Person personDetails) throws Exception {

    	Person existingPerson = findById(id); 
        
        existingPerson.setName(personDetails.getName());
        existingPerson.setLastName(personDetails.getLastName());
        existingPerson.setSex(personDetails.getSex());
        
        return this.save(existingPerson);
    }
    

    public void deleteById(Integer id) throws Exception {

        Person personToDelete = findById(id);
        
        try {
             personRepository.deleteById(id);
        } catch (Exception e) {
             throw new Exception("Error al borrar el registro");
        }
 
    }
}