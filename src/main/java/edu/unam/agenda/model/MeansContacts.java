package edu.unam.agenda.model;

import javax.persistence.*;

@Entity
@Table(name = "means_contact")
public class MeansContacts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "means_contact_id")
	private Integer id;

	@Column(name = "value", length = 300)
	private String value;

	@ManyToOne
	@JoinColumn(name = "contact_id", nullable = false)
	private Contact contact;

	@ManyToOne
	@JoinColumn(name = "phone_type_id", nullable = false)
	private PhoneType phoneType;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public PhoneType getPhoneType() {
		return phoneType;
	}
	
	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	@Override
	public String toString() {
		return "MedioContacto [id=" + id + ", valor=" + value +
				", contacto=" + contact + ", tipoTelefono=" + phoneType +
				"]";
	}
}
