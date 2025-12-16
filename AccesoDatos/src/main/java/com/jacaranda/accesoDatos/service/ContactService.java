package com.jacaranda.accesoDatos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jacaranda.accesoDatos.model.Contact;
import com.jacaranda.accesoDatos.model.Person;
import com.jacaranda.accesoDatos.repository.ContactRepository;

import jakarta.validation.Valid;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
	
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public List<Contact> findAll() throws Exception {
		try {
	     return contactRepository.findAll();
		} catch (Exception e) {
			throw new Exception("Error de acceso a la base de datos. Contacte con el administrador");
		}
	}

	public Contact save(Contact contact) throws Exception {
		try {
			return contactRepository.save(contact);
		} catch (Exception e) {
			throw new Exception("Error al añadir el registro");
		}
	}

	public Contact findById(Integer id) throws Exception {
		try {
			return contactRepository.findById(id).get();
		} catch (Exception e) {
			throw new Exception("Error al buscar un contacto");
		}
	}

	public Contact update(Integer id, Contact contactDetails) throws Exception {
		Contact existingContact = findById(id); 
        
		existingContact.setTypeContact(contactDetails.getTypeContact());
		existingContact.setValue(contactDetails.getValue());
		
        return this.save(existingContact);
	}

	public void deleteById(Integer id) throws Exception {
		Contact contactDelete = findById(id);
		
		try {
			contactRepository.delete(contactDelete);
		} catch (Exception e) {
            throw new Exception("Error al borrar el registro");
		}
	}
	
	

}
