package com.calcImc.ServiceImc;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ServiceImc {
	public String asignImc(Optional<String> weight, Optional<String> age, Optional<String> sex, Optional<String> height) throws Exception {
			if(weight.get().isEmpty() || age.get().isEmpty() || sex.get().isEmpty() || height.get().isEmpty()) {
				throw new Exception("No puedes mandar datos vacíos");
			}
			double result = 0;
			double weightD;
			int ageD;
			String sexD;
			double heightD;
			try {
				weightD = Double.parseDouble(weight.get());
				ageD = Integer.parseInt(age.get());
				sexD = sex.get();
				heightD = Double.parseDouble(height.get());				
			} catch (Exception e) {
				throw new Exception("Datos inválidos");
			}
			
			if(weightD <= 0) {
				throw new Exception("El peso no es válido");
			}
			if(ageD <= 0 || ageD > 100) {
				throw new Exception("La edad no es válida");
			}
			if(heightD <= 0) {
				throw new Exception("La altura no es válida");
			}
			
			result = calculateImc(weightD, heightD);
			
			String imc = "";
			
			if(ageD >= 25) {
				if(result < 18.5) {
					imc = "Bajo peso " + result;
				}else if(result >= 18.5 && result <= 24.9) {
					imc = "Peso normal " + result;
				}else if(result >= 25 && result <= 29.9) {
					imc = "Sobre peso " + result;
				}else {
					imc = "Obesidad " + result;
				}
				return imc;
			}else{
				if(result < 17.5) {
					imc = "Bajo peso " + result;
				}else if(result >= 17.5 && result <= 26.9) {
					imc = "Peso normal " + result;
				}else if(result >= 27 && result <= 32.9) {
					imc = "Sobre peso " + result;
				}else {
					imc = "Obesidad " + result;
				}
			}
			return imc;
	}
	public double calculateImc(double weight, double height) {
		return weight / (height*height);
	}
	
}
