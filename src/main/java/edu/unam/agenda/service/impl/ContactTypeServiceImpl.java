package edu.unam.agenda.service.impl;

import edu.unam.agenda.dao.ContactTypeDAO;
import edu.unam.agenda.dao.impl.ContactTypeDAOImpl;
import edu.unam.agenda.model.ContactType;
import edu.unam.agenda.service.ContactTypeService;

import java.util.List;

public class ContactTypeServiceImpl implements ContactTypeService {
	
	private static ContactTypeServiceImpl instance;
	private static final ContactTypeDAO contactTypeDAO = ContactTypeDAOImpl.getInstance();
	
	private ContactTypeServiceImpl() {
		
	}
	
	public static ContactTypeServiceImpl getInstance() {
		if (instance == null) {
			instance = new ContactTypeServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<ContactType> getAllContactType() {
		return contactTypeDAO.getAllContactType();
	}
	
	@Override
	public ContactType getContactTypeById(Integer id) {
		return contactTypeDAO.getContactTypeById(id);
	}

	@Override
	public Boolean insertContactType(ContactType contactType) {
		return contactTypeDAO.insertContactType(contactType);
	}
	
	@Override
	public Boolean updateContactType(ContactType contactType) {
		return contactTypeDAO.updateContactType(contactType);
	}
	
	@Override
	public Boolean deleteContactType(ContactType contactType) {
		return contactTypeDAO.deleteContactType(contactType);
	}
}
