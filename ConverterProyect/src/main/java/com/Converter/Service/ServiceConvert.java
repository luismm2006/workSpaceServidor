package com.Converter.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ServiceConvert {
	
	public double convert(Optional<String> number , Optional<String> typeConvert) throws Exception {
		double result = 0;
		try {
			double numberConvert = Double.parseDouble(number.orElse("0"));
			String typeConvertExtract = typeConvert.get();
			if(typeConvertExtract.equals("km")) {
				result = convertToKm(numberConvert);
			}else if(typeConvertExtract.equals("m")) {
				result = convertToM(numberConvert);
			}
		} catch (Exception e) {
			throw new Exception("Número no válido");
		}
		return result;
	}
	
	public double convertToKm(double number) {
		return number / 1000;
	}
	public double convertToM(double number) {
		return number * 1000;
	}
}
