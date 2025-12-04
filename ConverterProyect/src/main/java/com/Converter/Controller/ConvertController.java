package com.Converter.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Converter.Service.ServiceConvert;

@Controller
public class ConvertController {
	
	private final ServiceConvert serviceConvert;
	
	
	public ConvertController(ServiceConvert serviceConvert) {
		super();
		this.serviceConvert = serviceConvert;
	}
	
	
	@GetMapping({ "/form", "/" })
	public String showForm() {
		return "formConvert";
	}
	
	@PostMapping("/")
	public String controllerConvert(@RequestParam Optional<String> number, @RequestParam Optional<String> typeConvert, Model model) throws Exception {
		try {
			if (number.isPresent() && typeConvert.isPresent()) {
				double result = serviceConvert.convert(number, typeConvert);
				model.addAttribute("result", result);
			}
		} catch(IllegalArgumentException e){
			model.addAttribute("error", e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "formConvert";
	}
}
