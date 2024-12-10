package edu.unam.agenda.dao;

import edu.unam.agenda.model.ContactType;

import java.util.List;

public interface ContactTypeDAO {
	ContactType getContactTypeById(Integer id);

	List<ContactType> getAllContactType();

	Boolean insertContactType(ContactType contactType);

	Boolean updateContactType(ContactType contactType);

	Boolean deleteContactType(ContactType contactType);
}
