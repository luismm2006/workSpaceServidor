package com.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persona.model.PersonaModel;

public interface PersonRepository extends JpaRepository<PersonaModel, Integer>{
	
}
