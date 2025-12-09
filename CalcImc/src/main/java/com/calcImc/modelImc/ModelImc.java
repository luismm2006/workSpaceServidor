package com.calcImc.modelImc;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ModelImc {
	@Min(value= 0, message="La altura debe ser mayor de 0")
	@Max(value= 300, message="La altura debe ser menor de 3 metros")
	@NotNull(message="No puede ser nulo")
	private Double height;
	
	@Min(value= 0, message="El peso debe ser mayor de 0")
	@Max(value= 300, message="El peso debe ser menor de 300")
	@NotNull(message="No puede ser nulo")
	private Double weight;
	
	@Min(value= 0, message="La edad debe ser mayor de 0")
	@Max(value= 120, message="La edad debe ser menor de 120")
	@NotNull(message="No puede ser nulo")
	private Integer age;
	
	@NotNull(message="No puede ser nulo")
	@Pattern(regexp="H|M", message="El sexo debe ser H o M")
	private String sex;
	
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
