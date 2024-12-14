package edu.unam.agenda.model;

import javax.persistence.*;

@Entity
@Table(name = "phone_type")
public class PhoneType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_type_id")
	private Integer id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "status", length = 50)
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TipoTelefono [id=" + id + ", nombre=" + name + ", estatus=" + status + "]";
	}
}
