package edu.unam.agenda.service;

import edu.unam.agenda.model.MeansContacts;

import java.util.List;

public interface MeansContactsService {
	MeansContacts getMeansContactsById(Integer id);
	List<MeansContacts> getAllMeansContacts();
	Boolean insertMeansContacts(MeansContacts contacto);
	Boolean updateMeansContacts(MeansContacts contacto);
	Boolean deleteMeansContacts(MeansContacts contacto);
	List<MeansContacts> getMeansContactsByContact(Integer id);
}
