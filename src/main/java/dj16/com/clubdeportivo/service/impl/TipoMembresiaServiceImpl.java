package dj16.com.clubdeportivo.service.impl;

import dj16.com.clubdeportivo.dao.TipoMembresiaDAO;
import dj16.com.clubdeportivo.dao.impl.TipoMembresiaDAOImpl;
import dj16.com.clubdeportivo.model.TipoMembresia;
import dj16.com.clubdeportivo.service.TipoMembresiaService;

import java.util.List;

public class TipoMembresiaServiceImpl implements TipoMembresiaService {

    private static TipoMembresiaServiceImpl instance;
    private static final TipoMembresiaDAO tipoMembresiaDAO = TipoMembresiaDAOImpl.getInstance();

    private TipoMembresiaServiceImpl() {

    }

    public static TipoMembresiaServiceImpl getInstance() {
        if (instance == null) {
            instance = new TipoMembresiaServiceImpl();
        }
        return instance;
    }

    @Override
    public List<TipoMembresia> getAllTipoMembresia() {
        return tipoMembresiaDAO.getAllTipoMembresia();
    }

    @Override
    public TipoMembresia getTipoMembresiaById(Integer id) {
        return tipoMembresiaDAO.getTipoMembresiaById(id);
    }

    @Override
    public Boolean insertTipoMembresia(TipoMembresia TipoMembresia) {
        return tipoMembresiaDAO.insertTipoMembresia(TipoMembresia);
    }

    @Override
    public Boolean updateTipoMembresia(TipoMembresia TipoMembresia) {
        return tipoMembresiaDAO.updateTipoMembresia(TipoMembresia);
    }

    @Override
    public Boolean deleteTipoMembresia(TipoMembresia TipoMembresia) {
        return tipoMembresiaDAO.deleteTipoMembresia(TipoMembresia);
    }
}
