package com.calcImc.ControllerImc;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.calcImc.ServiceImc.ServiceImc;

@Controller
public class ControllerImc {

	private final ServiceImc serviceImc;
	
	
	public ControllerImc(ServiceImc serviceImc) {
		super();
		this.serviceImc = serviceImc;
	}
	
	@GetMapping("/")
	public String showImc() {
		return "imc";
	}
	
	@PostMapping("/")
	public String calculator(@RequestParam Optional<String> weight, @RequestParam Optional<String> age, @RequestParam Optional<String> sex, @RequestParam Optional<String> height, Model model) throws Exception {
		try {
			if(weight.isPresent() && age.isPresent() && sex.isPresent() && height.isPresent()) {
				String result = serviceImc.asignImc(weight, age, sex, height);
				model.addAttribute("result", result);
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "imc";
	}
	
}
