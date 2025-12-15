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
		try {
			model.addAttribute("listPeople", this.personService.getPeople());
		} catch (Exception e) {
			model.addAttribute("result", e.getMessage());
		}
		model.addAttribute("person", new PersonaModel());
		return "listPeople";
	}
	
	
	@GetMapping("/addPeople")
	public String getAddPeople(Model model) {
		model.addAttribute("person", new PersonaModel());
		return "form";
	}
	
	@PostMapping("/addPeople")
	public String postPeople(Model model, @Validated @ModelAttribute("person") PersonaModel personaModel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			try {
				model.addAttribute("listPeople", personService.getPeople());
			} catch (Exception e) {
				model.addAttribute("result", e.getMessage());
			}
			return "form";
		}
		try {
			personService.postPeople(personaModel);
		} catch (Exception e) {
			model.addAttribute("result", e.getMessage());
		}
		//Uso de patrón PRG (Post Redirect Get) dice al navegador: “vete a esta URL como si el usuario la hubiera escrito en la barra de direcciones”.
		return "redirect:/people";
	}
	
	@GetMapping("/editPeople")
	public String getEditPeople(@RequestParam Integer id, Model model) {
		model.addAttribute("person", new PersonaModel());
		PersonaModel people;
		try {
			people = personService.findById(id);
			model.addAttribute("person", people); // carga datos en el formulario
		} catch (Exception e) {
			model.addAttribute("result", e.getMessage());
		} 	
		return "form";
	}
	
	@PostMapping("/editPeople")
	public String editPerson(Model model, @Validated @ModelAttribute("person") PersonaModel personaModel, BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) {
		        return "form";
		 }
		 try {
		    personService.updatePeople(personaModel);
		 } catch (Exception e) {
		    model.addAttribute("result", e.getMessage());
		       return "form";
		 }
		 return "redirect:/people";
	}

	
	@GetMapping("/deletePeople")
	public String getDeletePeople(@RequestParam Integer id, Model model) {
		model.addAttribute("person", new PersonaModel());
		model.addAttribute("mode", "delete");
		PersonaModel people; 
		try {
			people = personService.findById(id);
			model.addAttribute("person", people);
		} catch (Exception e) {
			model.addAttribute("result", e.getMessage());
		}
		return "form";
	}
	
	@PostMapping("/deletePeople")
	public String deletePerson(@RequestParam Integer id, Model model) {
		try {
			personService.deletePeople(id);
		} catch (Exception e) {
			model.addAttribute("result", e.getMessage());
		}
		try {
			model.addAttribute("listPeople", personService.getPeople());
		} catch (Exception e) {
			model.addAttribute("result", e.getMessage());
		}
		return "redirect:/people";
	}
	
}
