package edu.unam.agenda.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "last_name", length = 200)
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    //	@ManyToOne (targetEntity = ContactType.class, optional = false, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "contact_type_id", nullable = false)
	@Fetch(FetchMode.JOIN)
    private ContactType contactType;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
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
                + ", direccion=" + address +
                ", tipoContacto=" + contactType +
                //", mediosContacto=" + meansContacts +
                 "]";
    }
}
