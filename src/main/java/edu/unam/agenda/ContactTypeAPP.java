package edu.unam.agenda;

import edu.unam.agenda.hibernate.HibernateUtil;
import edu.unam.agenda.model.ContactType;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.ContactService;
import edu.unam.agenda.service.ContactTypeService;
import edu.unam.agenda.service.PhoneTypeService;
import edu.unam.agenda.service.impl.ContactServiceImpl;
import edu.unam.agenda.service.impl.ContactTypeServiceImpl;
import edu.unam.agenda.service.impl.PhoneTypeServiceImpl;

import java.util.List;

public class ContactTypeAPP {
	public static void main(String[] args) {
		HibernateUtil.init();

		ContactTypeService contactTypeService = ContactTypeServiceImpl.getInstance();
		PhoneTypeService phoneTypeService = PhoneTypeServiceImpl.getInstance();
		ContactService contactService = ContactServiceImpl.getInstance();

		List<ContactType> listContactType = contactTypeService.getAllContactType();
		System.out.println("Tipos de contacto: " + listContactType.size());
		for (ContactType contactType : listContactType)
			System.out.println(contactType.toString());
		System.out.println();

		System.out.println("Carga Tipos Contacto por Id");
		ContactType contactType1 = contactTypeService.getContactTypeById(1);
		System.out.println(contactType1.toString());
		System.out.println();

		// Creación de un ContactType
//		ContactType contactType2 = new ContactType();
//		contactType2.setName("Amigos");
//		contactType2.setStatus("ACTIVO");
//		contactTypeService.insertContactType(contactType2);


		ContactType familiar = contactTypeService.getContactTypeById(1);
		System.out.println("El contacto familiar===" + familiar);
		List<PhoneType> mediosContacto = phoneTypeService.getAllPhoneTypes();
		for (PhoneType phoneType : mediosContacto) {
			System.out.println("El medio contacto es: " + phoneType.getName());
		}
		System.out.println();

		PhoneType casa = phoneTypeService.getPhoneTypeById(1);
		System.out.println("El medio casa===" + casa);

	}
}
