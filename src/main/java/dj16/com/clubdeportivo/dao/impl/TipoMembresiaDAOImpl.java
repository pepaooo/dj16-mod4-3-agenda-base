package dj16.com.clubdeportivo.dao.impl;

import dj16.com.clubdeportivo.dao.TipoMembresiaDAO;
import dj16.com.clubdeportivo.hibernate.HibernateUtil;
import dj16.com.clubdeportivo.model.TipoMembresia;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class TipoMembresiaDAOImpl implements TipoMembresiaDAO {

    private static TipoMembresiaDAOImpl instance;

    private TipoMembresiaDAOImpl() {
    }

    public static TipoMembresiaDAOImpl getInstance() {
        if (instance == null)
            instance = new TipoMembresiaDAOImpl();
        return instance;
    }

    @Override
    public TipoMembresia getTipoMembresiaById(Integer id) {
        TipoMembresia TipoMembresia = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            TipoMembresia = session.get(TipoMembresia.class, id);
            // se realiza el commit
            session.getTransaction().commit();
            // se cierra la session hibernate
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
        }
        return TipoMembresia;
    }

    @Override
    public List<TipoMembresia> getAllTipoMembresia() {
        List<TipoMembresia> TipoMembresiaList = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            TipoMembresiaList = session.createQuery("FROM TipoMembresia", TipoMembresia.class).getResultList();
            // se realiza el commit
            session.getTransaction().commit();
            // se cierra la session hibernate
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(HibernateUtil.getRegistry());
        }
        return TipoMembresiaList;
    }

    @Override
    public Boolean insertTipoMembresia(TipoMembresia TipoMembresia) {
        Boolean save = Boolean.FALSE;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            session.persist(TipoMembresia);
            save = TipoMembresia.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
    public Boolean updateTipoMembresia(TipoMembresia TipoMembresia) {
        Boolean update = Boolean.FALSE;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            session.merge(TipoMembresia);
            update = TipoMembresia.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
    public Boolean deleteTipoMembresia(TipoMembresia TipoMembresia) {
        Boolean delete = Boolean.FALSE;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // se inicia una transaccion
            session.beginTransaction();
            session.remove(TipoMembresia);
            delete = TipoMembresia.getId() != null ? Boolean.TRUE : Boolean.FALSE;
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
