package com.persona.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.persona.service.PersonService;

@Controller
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}
	
	
	@GetMapping({"/", "/people"})
	public String getPeople(Model model) {
		model.addAttribute("listPeople", this.personService.getPeople());
		return "listPeople";
	}
}
