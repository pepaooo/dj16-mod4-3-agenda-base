package edu.unam.agenda;

import edu.unam.agenda.model.Contact;
import edu.unam.agenda.model.ContactType;
import edu.unam.agenda.hibernate.HibernateUtil;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.ContactService;
import edu.unam.agenda.service.ContactTypeService;
import edu.unam.agenda.service.impl.ContactServiceImpl;
import edu.unam.agenda.service.impl.ContactTypeServiceImpl;

import java.util.List;

public class ContactTypeAPP {
	public static void main(String[] args) {
		HibernateUtil.init();
		
		ContactTypeService contactTypeService = ContactTypeServiceImpl.getInstance();
		ContactService contactService = ContactServiceImpl.getInstance();

		System.out.println("Carga Tipos Contacto");
		List<ContactType> listContactType = contactTypeService.getAllContactType();
		for (ContactType contactType : listContactType)
			System.out.println(contactType.toString());

//		System.out.println("Carga Tipos Contacto por Id");
//		ContactType contactType = contactTypeService.getContactTypeById(1);
//		System.out.println(contactType.toString());

		// Creación de un ContactType
//		ContactType contactType2 = new ContactType();
//		contactType2.setName("Amigos");
//		contactType2.setStatus("ACTIVO");
//		contactTypeService.insertContactType(contactType2);

//		System.out.println("Carga Tipos Contacto");
//		listContactType = contactTypeService.getAllContactType();
//		for (ContactType contactType3 : listContactType)
//			System.out.println(contactType3.toString());

		// Actualización de un ContactType

//		List<Contact> allContacts = contactService.getAllContacts();
//		System.out.println("Los contactos son:" + allContacts);
//
//		if (allContacts != null && ! allContacts.isEmpty()) {
//			System.out.println("Los contactos son:");
//			for (Contact contact : allContacts) {
//				System.out.println(contact.toString());
//			}
//		}

	}
}
