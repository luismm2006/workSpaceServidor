package com.Converter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Luis.Service.ServiceCalc;
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
	public double controllerConvert(Optional<String> num1, Optional<String>) {
		
	}
}
