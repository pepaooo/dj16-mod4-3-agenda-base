package edu.unam.agenda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;
	
	public static void init() {
		registry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml") // se carga la configuracion hibernate
				.build();
		try {
			// se crea una fabrica de sessiones hibernate
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	public static StandardServiceRegistry getRegistry() {
		return registry != null ? registry : null; 
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory != null ? sessionFactory : null; 
	}
	
	public static Session getSession() {
		return sessionFactory != null ? sessionFactory.openSession() : null; 
	}
}
