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

import com.jacaranda.accesoDatos.model.Contact;
import com.jacaranda.accesoDatos.service.ContactService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contact") // Prefijo para todas las rutas de este controlador
public class ContactsController {
	
		private final ContactService contactService;

		public ContactsController(ContactService contactService) {
			this.contactService = contactService;
		}

		@GetMapping("/listContacts")
		public String listContacts(Model model) {
			try {
				List<Contact> contact = contactService.findAll();
				model.addAttribute("contact", contact);
			} catch (Exception e) {
				model.addAttribute("error", "Error al cargar el listado de contactos: " + e.getMessage());
				model.addAttribute("contact", new ArrayList<Contact>()); // Asegura que la vista no falle
			}
			return "contact/listContacts";
		}

		/*@GetMapping("/new")
		public String showNewForm(Model model) {
			model.addAttribute("contact", new Contact());
			model.addAttribute("action", "create");
			return "contact/form";
		}

		@PostMapping("/new")
		public String createPerson(Model model, RedirectAttributes redirectAttributes, @Valid @ModelAttribute Contact contact,
				BindingResult result) {

			if (result.hasErrors()) {
				model.addAttribute("action", "create");
				return "contact/form";
			}

			try {
				contactService.save(contact);
				redirectAttributes.addFlashAttribute("success", "Contacto creado exitosamente.");

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", "Error al crear el contacto: " + e.getMessage());
			}

			return "redirect:/contact/listContacts";
		}*/

		@GetMapping("/edit/{id}")
		public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
			try {
				Contact contact = contactService.findById(id);
				model.addAttribute("contact", contact);
				model.addAttribute("action", "edit");
				return "contact/formContact";

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", e.getMessage());
				return "redirect:/contact/listContacts";
			}
		}

		@PostMapping("/edit/{id}")
		public String updateContact(Model model, @PathVariable Integer id,
				@Valid @ModelAttribute("contact") Contact contactDetails, BindingResult result,
				RedirectAttributes redirectAttributes) {

			if (result.hasErrors()) {
				model.addAttribute("action", "edit");
				return "contact/formContact";
			}

			try {
				contactService.update(id, contactDetails); // Usa el método update con verificación
				redirectAttributes.addFlashAttribute("success", "Persona con ID " + id + " actualizada correctamente.");

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", e.getMessage());

			}
			return "redirect:/contact/listContacts";
		}

		@GetMapping("/delete")
	    public String showDeleteForm(Model model, @RequestParam Integer id, RedirectAttributes redirectAttributes) {
			try {
				Contact contact = contactService.findById(id);
				model.addAttribute("contact", contact);
				model.addAttribute("action", "delete");
				return "contact/formContact";

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", e.getMessage());
				return "redirect:/contact/listContacts";
			}
	    }
		
		@PostMapping("/delete")
		public String deleteContact(Model model, @RequestParam Integer id,RedirectAttributes redirectAttributes) {
	 
		   try {
			   contactService.deleteById(id);
			   redirectAttributes.addFlashAttribute("success", "Contacto con ID " + id + " eliminado correctamente.");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", e.getMessage());

			}
			return "redirect:/contact/listContacts";
		}
}
