package edu.unam.agenda.model;

public class ContactType {
	private Integer id;
	private String name;
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
		return "TipoContacto [id=" + id + ", nombre=" + name + ", estatus=" + status + ", hashCode()=" + hashCode()
				+ "]";
	}
}
