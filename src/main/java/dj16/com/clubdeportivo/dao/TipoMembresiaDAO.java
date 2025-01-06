package dj16.com.clubdeportivo.dao;

import dj16.com.clubdeportivo.model.TipoMembresia;

import java.util.List;

public interface TipoMembresiaDAO {
	TipoMembresia getTipoMembresiaById(Integer id);

	List<TipoMembresia> getAllTipoMembresia();

	Boolean insertTipoMembresia(TipoMembresia TipoMembresia);

	Boolean updateTipoMembresia(TipoMembresia TipoMembresia);

	Boolean deleteTipoMembresia(TipoMembresia TipoMembresia);
}
