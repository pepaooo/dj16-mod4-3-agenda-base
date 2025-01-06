package dj16.com.clubdeportivo.service;

import dj16.com.clubdeportivo.model.Miembro;

import java.util.List;

public interface MiembroService {
    Miembro getMiembroById(Integer id);

    List<Miembro> getAllMiembros();

    Boolean insertMiembro(Miembro Miembro);

    Boolean updateMiembro(Miembro Miembro);

    Boolean deleteMiembro(Miembro Miembro);
}
