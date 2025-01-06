package dj16.com.clubdeportivo;

import dj16.com.clubdeportivo.hibernate.HibernateUtil;
import dj16.com.clubdeportivo.model.Miembro;
import dj16.com.clubdeportivo.model.TipoMembresia;
import dj16.com.clubdeportivo.service.MiembroService;
import dj16.com.clubdeportivo.service.TipoMembresiaService;
import dj16.com.clubdeportivo.service.impl.MiembroServiceImpl;
import dj16.com.clubdeportivo.service.impl.TipoMembresiaServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class MiembroAPP {
    public static void main(String[] args) {
        HibernateUtil.init();

        MiembroService miembroService = MiembroServiceImpl.getInstance();
        TipoMembresiaService tipoMembresiaService = TipoMembresiaServiceImpl.getInstance();

        // Listar todos los Miembros
        List<Miembro> listMiembro = miembroService.getAllMiembros();
        for (Miembro miembro : listMiembro)
            System.out.println(miembro.toString());

        // Buscar Miembro por Id
        System.out.println("Buscando Miembro por Id");
        System.out.println(miembroService.getMiembroById(1));

        // Insertar Miembro
        System.out.println("Insertando Miembro");
        Miembro miembro = new Miembro();
        miembro.setNombre("Miembro 3");
        miembro.setApellidos("Apellido Miembro 3");
        miembro.setTelefono("123456789");
        miembro.setDireccion("Direccion Miembro 3");
        miembro.setCorreo("miembro3@example.com");
        miembro.setFechaNacimiento(LocalDate.of(1988, 1, 1));
        miembro.setFechaInscripcion(LocalDate.now());
        miembro.setGenero("F");
        TipoMembresia membresia = tipoMembresiaService.getTipoMembresiaById(1);
        miembro.setTipoMembresia(membresia);
        System.out.println(miembroService.insertMiembro(miembro));
        System.out.println(miembroService.getMiembroById(11));

        // Actualizar Miembro
        System.out.println("Actualizando Miembro");
        miembro.setNombre("Miembro 4");
        miembro.setApellidos("Apellido Miembro 4");
        miembro.setTelefono("987654321");
        miembro.setDireccion("Direccion Miembro 4");
        TipoMembresia membresia2 = tipoMembresiaService.getTipoMembresiaById(2);
        miembro.setTipoMembresia(membresia2);
        System.out.println(miembroService.updateMiembro(miembro));
        System.out.println(miembroService.getMiembroById(11));

        // Eliminar Miembro
        System.out.println("Eliminando Miembro");
        System.out.println(miembroService.deleteMiembro(miembro));
        System.out.println(miembroService.getMiembroById(11));

    }
}
