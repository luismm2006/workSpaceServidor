package com.persona.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.persona.model.PersonaModel;
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
		model.addAttribute("person", new PersonaModel());
		return "listPeople";
	}
	
	@PostMapping("/")
	public String postPeople(Model model, @Validated @ModelAttribute("person") PersonaModel personaModel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listPeople", personService.getPeople());
	        return "listPeople";
		}
		personService.postPeople(personaModel);
		//Uso de patrón PRG (Post Redirect Get) dice al navegador: “vete a esta URL como si el usuario la hubiera escrito en la barra de direcciones”.
		return "redirect:/people";
	}
	
	@PostMapping("/people/edit")
	public String editPerson(@RequestParam Integer id, Model model) {

	    PersonaModel persona = personService.editPeople(id); //trae esa persona por ese id, incluye el id y los demás campos, por eso accede al id en el html

	    model.addAttribute("person", persona); // carga datos en el formulario
	    model.addAttribute("listPeople", personService.getPeople()); //  mantiene la tabla

	    return "listPeople"; 
	    // misma vista para poder hacer el Post que está en el listPeople, al tener en el html un input hidden con el id, al mandar el id lo puede almacenar, sino hay lo crea bbdd
	}

	@PostMapping("/people/delete")
	public String deletePerson(@RequestParam Integer id, Model model) {
		personService.deletePeople(id);
		model.addAttribute("listPeople", personService.getPeople());
		return "redirect:/people";
	}
	
}
