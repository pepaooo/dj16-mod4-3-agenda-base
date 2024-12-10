package edu.unam.agenda.service.impl;

import edu.unam.agenda.dao.ContactDAO;
import edu.unam.agenda.dao.impl.ContactDAOImpl;
import edu.unam.agenda.model.Contact;
import edu.unam.agenda.model.MeansContacts;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {
	
	private static ContactServiceImpl instance;
	private static final ContactDAO contactDAO = ContactDAOImpl.getInstance();
	
	private ContactServiceImpl() {}
	
	public static ContactServiceImpl getInstance() {
		if (instance == null) {
			instance = new ContactServiceImpl();
		}
		return instance;
	}

	@Override
	public Contact getContactById(Integer id) {
		return contactDAO.getContactById(id);
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactDAO.getAllContacts();
	}

	@Override
	public Boolean insertContact(Contact contact) {
		return contactDAO.insertContact(contact);
	}

	@Override
	public Boolean updateContact(Contact contact) {
		return contactDAO.updateContact(contact);
	}

	@Override
	public Boolean deleteContact(Contact contact) {
		return contactDAO.deleteContact(contact);
	}
	
	@Override
	public List<MeansContacts> getPhoneTypes(PhoneType phoneType) {
		return contactDAO.getPhoneTypes(phoneType);
	}

}
