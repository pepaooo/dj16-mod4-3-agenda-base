package dj16.com.clubdeportivo;

import dj16.com.clubdeportivo.hibernate.HibernateUtil;
import dj16.com.clubdeportivo.model.TipoMembresia;
import dj16.com.clubdeportivo.service.TipoMembresiaService;
import dj16.com.clubdeportivo.service.impl.TipoMembresiaServiceImpl;

import java.util.List;

public class TipoMembresiaAPP {
    public static void main(String[] args) {
        HibernateUtil.init();

        TipoMembresiaService TipoMembresiaService = TipoMembresiaServiceImpl.getInstance();

        // Listar todos los Tipos de Membresia
        List<TipoMembresia> listTipoMembresia = TipoMembresiaService.getAllTipoMembresia();
        for (TipoMembresia tipoMembresia : listTipoMembresia)
            System.out.println(tipoMembresia.toString());

        // Buscar Tipo de Membresia por Id
        System.out.println("Buscando Tipo de Membresia por Id");
        System.out.println(TipoMembresiaService.getTipoMembresiaById(1));

        // Insertar Tipo de Membresia
        System.out.println("Insertando Tipo de Membresia");
        TipoMembresia tipoMembresia = new TipoMembresia();
        tipoMembresia.setNombre("Tipo de Membresia 3");
        tipoMembresia.setDuracionDias(30);
        tipoMembresia.setTarifa(50.0);
        System.out.println(TipoMembresiaService.insertTipoMembresia(tipoMembresia));

        // Actualizar Tipo de Membresia
        System.out.println("Actualizando Tipo de Membresia");
        tipoMembresia.setNombre("Tipo de Membresia 4");
        System.out.println(TipoMembresiaService.updateTipoMembresia(tipoMembresia));

        // Eliminar Tipo de Membresia
        System.out.println("Eliminando Tipo de Membresia");
        System.out.println(TipoMembresiaService.deleteTipoMembresia(tipoMembresia));
    }
}
