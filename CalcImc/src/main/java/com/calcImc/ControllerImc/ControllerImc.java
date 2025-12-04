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
	public String calculator(@RequestParam Optional<String> weight, @RequestParam Optional<String> age,
			@RequestParam Optional<String> sex, @RequestParam Optional<String> height, Model model) throws Exception {
		try {
			if (weight.isEmpty() || age.isEmpty() || sex.isEmpty() || height.isEmpty()) {
				model.addAttribute("error", "No puedes enviar campos vacíos");
				return "imc";
			}
			String result = serviceImc.asignImc(weight, age, sex, height);
			model.addAttribute("result", result);
			model.addAttribute("weight", weight.get());
			model.addAttribute("age", age.get());
			model.addAttribute("sex", sex.get());
			model.addAttribute("height", height.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "imc";
	}

}
