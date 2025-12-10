package com.calcImc.ControllerImc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.calcImc.ServiceImc.ServiceImc;
import com.calcImc.modelImc.ModelImc;

@Controller
public class ControllerImc {

	private final ServiceImc serviceImc;

	public ControllerImc(ServiceImc serviceImc) {
		super();
		this.serviceImc = serviceImc;
	}

	@GetMapping("/")
	public String showImc(Model model) {
		model.addAttribute("imc", new ModelImc());
		return "imc";
	}

	@PostMapping("/")
	public String calculator(Model model, @Validated @ModelAttribute("imc") ModelImc modelImc, BindingResult bindingResult ){
		
		if(bindingResult.hasErrors()) {
			return "imc";
		}
		String imc = serviceImc.asignImc(modelImc);
	
		model.addAttribute("result", imc);
		model.addAttribute("imcResul", modelImc);
		model.addAttribute("imc", new ModelImc());
	

		return "imc";
	}

}
