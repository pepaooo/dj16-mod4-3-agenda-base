package edu.unam.agenda.dao;

import edu.unam.agenda.model.PhoneType;

import java.util.List;

public interface PhoneTypeDAO {
	List<PhoneType> getAllPhoneTypes();

	PhoneType getPhoneTypeById(Integer id);

	Boolean insertPhoneType(PhoneType phoneType);

	Boolean updatePhoneType(PhoneType phoneType);

	Boolean deletePhoneType(PhoneType phoneType);
}
