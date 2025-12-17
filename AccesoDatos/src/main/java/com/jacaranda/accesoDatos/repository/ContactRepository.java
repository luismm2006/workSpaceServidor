package com.jacaranda.accesoDatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.accesoDatos.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
	List<Contact> findByPersonId(Integer id);
}
