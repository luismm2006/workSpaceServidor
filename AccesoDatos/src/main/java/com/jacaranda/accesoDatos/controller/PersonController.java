package com.jacaranda.accesoDatos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jacaranda.accesoDatos.model.Person;
import com.jacaranda.accesoDatos.service.PersonService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/person") // Prefijo para todas las rutas de este controlador
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/list")
	public String listPeople(Model model) {
		try {
			List<Person> people = personService.findAll();
			model.addAttribute("people", people);
		} catch (Exception e) {
			model.addAttribute("error", "Error al cargar el listado de personas: " + e.getMessage());
			model.addAttribute("people", new ArrayList<Person>()); // Asegura que la vista no falle
		}
		return "person/list";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("action", "create");
		return "person/form";
	}

	@PostMapping("/new")
	public String createPerson(Model model, RedirectAttributes redirectAttributes, @Valid @ModelAttribute Person person,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("action", "create");
			return "person/form";
		}

		try {
			personService.save(person);
			redirectAttributes.addFlashAttribute("success", "Persona creada exitosamente.");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error al crear la persona: " + e.getMessage());
		}

		return "redirect:/person/list";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Person person = personService.findById(id);
			model.addAttribute("person", person);
			model.addAttribute("action", "edit");
			return "person/form";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/person/list";
		}
	}

	@PostMapping("/edit/{id}")
	public String updatePerson(Model model, @PathVariable Integer id,
			@Valid @ModelAttribute("person") Person personDetails, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("action", "edit");
			return "person/form";
		}

		try {
			personService.update(id, personDetails); // Usa el método update con verificación
			redirectAttributes.addFlashAttribute("success", "Persona con ID " + id + " actualizada correctamente.");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());

		}
		return "redirect:/person/list";
	}

	@GetMapping("/delete")
    public String showDeleteForm(Model model, @RequestParam Integer id, RedirectAttributes redirectAttributes) {
		try {
			Person person = personService.findById(id);
			model.addAttribute("person", person);
			model.addAttribute("action", "delete");
			return "person/form";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/person/list";
		}
    }
	
	@PostMapping("/delete")
	public String deletePerson(Model model, @RequestParam Integer id,RedirectAttributes redirectAttributes) {
 
	   try {
		   personService.deleteById(id);
		   redirectAttributes.addFlashAttribute("success", "Persona con ID " + id + " eliminada correctamente.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());

		}
		return "redirect:/person/list";
	}
}

