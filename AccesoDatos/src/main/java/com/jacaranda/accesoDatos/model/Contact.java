package com.jacaranda.accesoDatos.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contactos")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contacto")
	private Integer idContact;
	
	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Person person;
	
	@NotNull
	@Column(name = "tipo_contacto")
	private String typeContact;
	
	@NotNull
	@Column(name = "valor")
	private String value;

	public Integer getIdContact() {
		return idContact;
	}

	public void setIdContact(Integer idContact) {
		this.idContact = idContact;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTypeContact() {
		return typeContact;
	}

	public void setTypeContact(String typeContact) {
		this.typeContact = typeContact;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idContact, person, typeContact, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(idContact, other.idContact) && Objects.equals(person, other.person)
				&& Objects.equals(typeContact, other.typeContact) && Objects.equals(value, other.value);
	}

	
	
}
