package com.jacaranda.accesoDatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.accesoDatos.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
