package edu.unam.agenda.service.impl;

import edu.unam.agenda.dao.PhoneTypeDAO;
import edu.unam.agenda.dao.impl.PhoneTypeDAOImpl;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.PhoneTypeService;

import java.util.List;

public class PhoneTypeServiceImpl implements PhoneTypeService {
	private static PhoneTypeServiceImpl instance;
	private static final PhoneTypeDAO phoneTypeDAO = PhoneTypeDAOImpl.getInstance();
	
	private PhoneTypeServiceImpl() {
	}
	
	public static PhoneTypeServiceImpl getInstance() {
		if (instance == null) {
			instance = new PhoneTypeServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<PhoneType> getAllPhoneTypes() {
		return phoneTypeDAO.getAllPhoneTypes();
	}

	@Override
	public PhoneType getPhoneTypeById(Integer id) {
		return phoneTypeDAO.getPhoneTypeById(id);
	}
	
	@Override
	public Boolean insertPhoneType(PhoneType phoneType) {
		return phoneTypeDAO.insertPhoneType(phoneType);
	}
	
	@Override
	public Boolean updatePhoneType(PhoneType phoneType) {
		return phoneTypeDAO.updatePhoneType(phoneType);
	}
	
	@Override
	public Boolean deletePhoneType(PhoneType phoneType) {
		return phoneTypeDAO.deletePhoneType(phoneType);
	}
}
