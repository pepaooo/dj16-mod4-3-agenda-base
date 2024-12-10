package edu.unam.agenda.service;

import edu.unam.agenda.model.PhoneType;

import java.util.List;

public interface PhoneTypeService {
	List<PhoneType> getAllPhoneTypes();
	PhoneType getPhoneTypeById(Integer id);
	Boolean insertPhoneType(PhoneType phoneType);
	Boolean updatePhoneType(PhoneType phoneType);
	Boolean deletePhoneType(PhoneType phoneType);
}
