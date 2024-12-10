package edu.unam.agenda.service;

import edu.unam.agenda.model.ContactType;

import java.util.List;

public interface ContactTypeService {
	ContactType getContactTypeById(Integer id);
	List<ContactType> getAllContactType();
	Boolean insertContactType(ContactType contactType);
	Boolean updateContactType(ContactType contactType);
	Boolean deleteContactType(ContactType contactType);
}
