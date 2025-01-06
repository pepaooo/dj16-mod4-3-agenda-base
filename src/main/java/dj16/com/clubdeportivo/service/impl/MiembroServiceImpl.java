package dj16.com.clubdeportivo.service.impl;

import dj16.com.clubdeportivo.dao.MiembroDAO;
import dj16.com.clubdeportivo.dao.impl.MiembroDAOImpl;
import dj16.com.clubdeportivo.model.Miembro;
import dj16.com.clubdeportivo.service.MiembroService;

import java.util.List;

public class MiembroServiceImpl implements MiembroService {

    private static MiembroServiceImpl instance;
    private static final MiembroDAO miembroDAO = MiembroDAOImpl.getInstance();

    private MiembroServiceImpl() {
    }

    public static MiembroServiceImpl getInstance() {
        if (instance == null) {
            instance = new MiembroServiceImpl();
        }
        return instance;
    }

    @Override
    public Miembro getMiembroById(Integer id) {
        return miembroDAO.getMiembroById(id);
    }

    @Override
    public List<Miembro> getAllMiembros() {
        return miembroDAO.getAllMiembros();
    }

    @Override
    public Boolean insertMiembro(Miembro Miembro) {
        return miembroDAO.insertMiembro(Miembro);
    }

    @Override
    public Boolean updateMiembro(Miembro Miembro) {
        return miembroDAO.updateMiembro(Miembro);
    }

    @Override
    public Boolean deleteMiembro(Miembro Miembro) {
        return miembroDAO.deleteMiembro(Miembro);
    }

}
