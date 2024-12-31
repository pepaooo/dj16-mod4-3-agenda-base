package edu.unam.agenda;

import edu.unam.agenda.hibernate.HibernateUtil;
import edu.unam.agenda.model.Contact;
import edu.unam.agenda.model.ContactType;
import edu.unam.agenda.model.MeansContacts;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.ContactService;
import edu.unam.agenda.service.ContactTypeService;
import edu.unam.agenda.service.PhoneTypeService;
import edu.unam.agenda.service.impl.ContactServiceImpl;
import edu.unam.agenda.service.impl.ContactTypeServiceImpl;
import edu.unam.agenda.service.impl.PhoneTypeServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactTypeAPP {
	public static void main(String[] args) {
		HibernateUtil.init();

		ContactTypeService contactTypeService = ContactTypeServiceImpl.getInstance();

		List<ContactType> listContactType = contactTypeService.getAllContactType();
		for (ContactType contactType : listContactType)
			System.out.println(contactType.toString());

	}
}
