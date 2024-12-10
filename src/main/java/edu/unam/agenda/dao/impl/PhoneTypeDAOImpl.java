package edu.unam.agenda.dao.impl;

import edu.unam.agenda.dao.PhoneTypeDAO;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class PhoneTypeDAOImpl implements PhoneTypeDAO {
	private static PhoneTypeDAOImpl instance;

	private PhoneTypeDAOImpl() {
	}

	public static PhoneTypeDAOImpl getInstance() {
		if (instance == null)
			instance = new PhoneTypeDAOImpl();
		return instance;
	}

	@Override
	public PhoneType getPhoneTypeById(Integer id) {
		PhoneType phoneType = null;
		try {
            Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			phoneType = session.get(PhoneType.class, id);
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return phoneType;
	}
	
	@Override
	public List<PhoneType> getAllPhoneTypes() {
		List<PhoneType> phoneTypeList = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			//listPhoneType = session.createQuery("SELECT t FROM PhoneType t WHERE t.estatus = 'ACTIVO' ").list();
			phoneTypeList = session.createQuery("SELECT t FROM PhoneType t WHERE t.status = 'ACTIVO' ", PhoneType.class).getResultList();
			// se realiza el commit
			session.getTransaction().commit();
			// se cierra la session hibernate
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
		}
		return phoneTypeList;
	}
	
	@Override
	public Boolean insertPhoneType(PhoneType phoneType) {
		Boolean save = Boolean.FALSE;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			session.persist(phoneType);
			save = phoneType.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
	public Boolean updatePhoneType(PhoneType phoneType) {
		Boolean update = Boolean.FALSE;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			session.merge(phoneType);
			update = phoneType.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
	public Boolean deletePhoneType(PhoneType phoneType) {
		Boolean delete = Boolean.FALSE;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// se inicia una transaccion
			session.beginTransaction();
			session.remove(phoneType);
			delete = phoneType.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
