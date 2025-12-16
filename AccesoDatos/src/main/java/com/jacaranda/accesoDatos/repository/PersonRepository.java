package com.jacaranda.accesoDatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.accesoDatos.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
