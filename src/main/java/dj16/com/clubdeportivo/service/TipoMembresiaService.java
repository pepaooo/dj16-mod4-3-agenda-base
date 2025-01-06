package dj16.com.clubdeportivo.service;

import dj16.com.clubdeportivo.model.TipoMembresia;

import java.util.List;

public interface TipoMembresiaService {
    TipoMembresia getTipoMembresiaById(Integer id);

    List<TipoMembresia> getAllTipoMembresia();

    Boolean insertTipoMembresia(TipoMembresia TipoMembresia);

    Boolean updateTipoMembresia(TipoMembresia TipoMembresia);

    Boolean deleteTipoMembresia(TipoMembresia TipoMembresia);
}
