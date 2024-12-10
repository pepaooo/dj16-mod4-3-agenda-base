package edu.unam.agenda.dao.impl;

import edu.unam.agenda.dao.MeansContactsDAO;
import edu.unam.agenda.model.MeansContacts;
import edu.unam.agenda.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class MeansContactsDAOImpl implements MeansContactsDAO {

	private static MeansContactsDAOImpl instance;

	private MeansContactsDAOImpl() {
	}

	public static MeansContactsDAOImpl getInstance() {
		if (instance == null)
			instance = new MeansContactsDAOImpl();
		return instance;
	}

	@Override
	public MeansContacts getMeansContactsById(Integer id) {
		MeansContacts meansContacts = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			meansContacts = session.get(MeansContacts.class, id);
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return meansContacts;
	}

	@Override
	public List<MeansContacts> getAllMeansContacts() {
		List<MeansContacts> meansContactsList = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			//catalogo = session.createQuery("FROM Contact").list();
			meansContactsList = session.createQuery("FROM MeansContacts", MeansContacts.class).getResultList();
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return meansContactsList;
	}
	
	@Override
	public List<MeansContacts> getMeansContactsByContact(Integer id) {
		List<MeansContacts> meansContactsList = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			meansContactsList = session.createQuery("SELECT u FROM MeansContacts u WHERE u.contact.id = :id", MeansContacts.class)
					.setParameter("id", id)
					.getResultList();
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return meansContactsList;
	}

	@Override
	public Boolean insertMeansContacts(MeansContacts contacto) {
		Boolean save = Boolean.FALSE;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			//session.save(contacto);
			session.persist(contacto);
			save = contacto.getId() != null ? Boolean.TRUE : Boolean.FALSE;
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return save;
	}

	@Override
	public Boolean updateMeansContacts(MeansContacts contacto) {
		Boolean update = Boolean.FALSE;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			//session.update(contacto);
			session.merge(contacto);
			update = contacto.getId() != null ? Boolean.TRUE : Boolean.FALSE;
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return update;
	}

	@Override
	public Boolean deleteMeansContacts(MeansContacts contacto) {
		Boolean delete = Boolean.FALSE;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			session.remove(contacto);
			delete = contacto.getId() != null ? Boolean.TRUE : Boolean.FALSE;
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return delete;
	}
}
