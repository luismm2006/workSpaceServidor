package com.calcImc.ServiceImc;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ServiceImc {
	public String asignImc(Optional<String> weight, Optional<String> age, Optional<String> sex, Optional<String> height) throws Exception {
		try {
			double result = 0;
			double weightD = Double.parseDouble(weight.get());
			int ageD = Integer.parseInt(age.get());
			String sexD = sex.get();
			double heightD = Double.parseDouble(height.get());
			result = calculateImc(weightD, heightD);
			
			if(weightD <= 0) {
				throw new Exception("El peso no es válido");
			}
			if(ageD <= 0 || ageD > 100) {
				throw new Exception("La edad no es válida");
			}
			if(heightD <= 0) {
				throw new Exception("La altura no es válida");
			}
			
			String imc = "";
			
			if(ageD > 25) {
				if(result < 18.5) {
					imc = "Bajo peso";
				}else if(result > 18.5 || result < 24.9) {
					imc = "Peso normal";
				}else if(result > 25 || result < 29.9) {
					imc = "Sobre peso";
				}else {
					imc = "Obesidad";
				}
				return imc;
			}
			if(ageD < 25) {
				if(result < 17.5) {
					imc = "Bajo peso";
				}else if(result > 17.5 || result < 26.9) {
					imc = "Peso normal";
				}else if(result > 27 || result < 32.9) {
					imc = "Sobre peso";
				}else {
					imc = "Obesidad";
				}
			}
			
			return imc;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public double calculateImc(double weight, double height) {
		return weight / (height*2);
	}
	
}
