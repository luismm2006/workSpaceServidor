package com.calcImc.ServiceImc;

import org.springframework.stereotype.Service;

import com.calcImc.modelImc.ModelImc;

@Service
public class ServiceImc {
	public String asignImc(ModelImc modelImc){
			
			double result = 0;
			double weightD = modelImc.getWeight();
			int ageD = modelImc.getAge();
			String sexD = modelImc.getSex();
			double heightD = modelImc.getHeight();

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
