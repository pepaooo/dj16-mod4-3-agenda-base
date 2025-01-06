package dj16.com.clubdeportivo.dao.impl;

import dj16.com.clubdeportivo.dao.MiembroDAO;
import dj16.com.clubdeportivo.hibernate.HibernateUtil;
import dj16.com.clubdeportivo.model.Miembro;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class MiembroDAOImpl implements MiembroDAO {
    private static MiembroDAOImpl instance;

    private MiembroDAOImpl() {
    }

    public static MiembroDAOImpl getInstance() {
        if (instance == null)
            instance = new MiembroDAOImpl();
        return instance;
    }

    @Override
    public Miembro getMiembroById(Integer id) {
        Miembro Miembro = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            Miembro = session.get(Miembro.class, id);
            // se realiza el commit
            session.getTransaction().commit();
            // se cierra la session hibernate
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
        }
        return Miembro;
    }

    @Override
    public List<Miembro> getAllMiembros() {
        List<Miembro> MiembroList = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            MiembroList = session.createQuery("SELECT t FROM Miembro t", Miembro.class).getResultList();
            // se realiza el commit
            session.getTransaction().commit();
            // se cierra la session hibernate
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
        }
        return MiembroList;
    }

    @Override
    public Boolean insertMiembro(Miembro miembro) {
        Boolean save = Boolean.FALSE;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            session.persist(miembro);
            save = miembro.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
    public Boolean updateMiembro(Miembro miembro) {
        Boolean update = Boolean.FALSE;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            session.merge(miembro);
            update = miembro.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
    public Boolean deleteMiembro(Miembro miembro) {
        Boolean delete = Boolean.FALSE;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            session.remove(miembro);
            delete = miembro.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
