package edu.unam.agenda.model;

import java.util.Set;

public class Contact {
	private Integer id;
	private String name;
	private String lastName;
	private Integer age;
	private String address;
	private ContactType contactType;
	private Set<MeansContacts> meansContacts;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public Set<MeansContacts> getMeansContacts() {
		return meansContacts;
	}

	public void setMeansContacts(Set<MeansContacts> meansContacts) {
		this.meansContacts = meansContacts;
	}

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + name + ", apellidos=" + lastName + ", edad=" + age
				+ ", direccion=" + address + ", tipoContacto=" + contactType + ", contactosMedios=" + meansContacts
				+ "]";
	}
}
