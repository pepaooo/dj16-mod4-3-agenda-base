package edu.unam.agenda.dao;

import edu.unam.agenda.model.Contact;
import edu.unam.agenda.model.MeansContacts;
import edu.unam.agenda.model.PhoneType;

import java.util.List;

public interface ContactDAO {

	Contact getContactById(Integer id);

	List<Contact> getAllContacts();

	Boolean insertContact(Contact contact);

	Boolean updateContact(Contact contact);

	Boolean deleteContact(Contact contact);
	
	List<MeansContacts> getPhoneTypes(PhoneType phoneType);
}
