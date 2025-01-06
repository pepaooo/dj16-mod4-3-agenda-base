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
import java.util.Scanner;

public class ClubDeportivoAPP {

    private final Scanner scanner = new Scanner(System.in);

    private final MiembroService miembroService = MiembroServiceImpl.getInstance();
    private final TipoMembresiaService tipoMembresiaService = TipoMembresiaServiceImpl.getInstance();

    public static void main(String[] args) {
        HibernateUtil.init();
        new ClubDeportivoAPP().showMainMenu();
    }

    /**
     * Menú principal de la aplicación.
     */
    public void showMainMenu() {
        while (true) {
            printMenu("Menú Principal", "Catálogos", "Miembros", "Salir");
            int option = getUserOption();
            switch (option) {
                case 1 -> showCatalogMenu();
                case 2 -> showMiembrosMenu();
                case 3 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Menú de catálogos (Tipos de Membresia).
     */
    private void showCatalogMenu() {
        while (true) {
            printMenu("Menú Catálogos", "Tipos de Membresia", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> showTipoMembresiaMenu();
                case 2 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Menú de Miembros (Miembro y Medios de Miembro).
     */
    private void showMiembrosMenu() {
        while (true) {
            printMenu("Menú Miembros", "Miembros", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> manageMiembros();
                case 2 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /* ----------------------------------------------------------------------------------
     *                               CRUD Menú para MIEMBROS
     * ---------------------------------------------------------------------------------- */
    private void manageMiembros() {
        while (true) {
            printMenu(
                    "Gestión de Miembros",
                    "Ver todos",
                    "Buscar por ID",
                    "Insertar",
                    "Actualizar",
                    "Eliminar",
                    "Regresar"
            );
            int option = getUserOption();
            switch (option) {
                case 1 -> listAll(miembroService.getAllMiembros());
                case 2 -> findById(miembroService::getMiembroById);
                case 3 -> insertMiembro();
                case 4 -> updateMiembro();
                case 5 -> deleteById(
                        id -> miembroService.deleteMiembro(miembroService.getMiembroById(id)),
                        miembroService::getMiembroById
                );
                case 6 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void insertMiembro() {
        Miembro miembro = new Miembro();

        System.out.print("Ingresa el nombre del miembro: ");
        miembro.setNombre(scanner.nextLine());

        System.out.print("Ingresa los apellidos del miembro: ");
        miembro.setApellidos(scanner.nextLine());

        System.out.print("Ingresa la direccion del miembro: ");
        miembro.setDireccion(scanner.nextLine());

        System.out.print("Ingresa el telefono del miembro: ");
        miembro.setTelefono(scanner.nextLine());

        System.out.print("Ingresa el correo electronico del miembro: ");
        miembro.setCorreo(scanner.nextLine());

        System.out.print("Ingresa la fecha de nacimiento del miembro [YYYY-MM-DD]: ");
        try {
            miembro.setFechaNacimiento(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Formato de fecha no valido.");
            return;
        }

        System.out.print("Ingresa el genero del miembro [M, F, O]: ");
        String genero = scanner.nextLine();
        if (!genero.equalsIgnoreCase("M") && !genero.equalsIgnoreCase("F") && !genero.equalsIgnoreCase("O")) {
            System.out.println("Genero no valido.");
            return;
        }
        miembro.setGenero(genero.toUpperCase());

        // Fecha de Inscripcion
        miembro.setFechaInscripcion(LocalDate.now());

        System.out.println("Tipos de Membresia a elegir: ");
        listAll(tipoMembresiaService.getAllTipoMembresia());

        System.out.print("Ingresa el ID del tipo de membresia: ");
        int tipoMembresiaId = Integer.parseInt(scanner.nextLine());
        TipoMembresia tipoMembresia = tipoMembresiaService.getTipoMembresiaById(tipoMembresiaId);

        if (tipoMembresia == null) {
            System.out.println("No se encontró un registro del tipo de miembro con el ID: " + tipoMembresiaId);
            return;
        }
        miembro.setTipoMembresia(tipoMembresia);

        boolean success = miembroService.insertMiembro(miembro);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updateMiembro() {
        System.out.print("Ingresa el ID del miembro a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Miembro miembro = miembroService.getMiembroById(id);
        if (miembro == null) {
            System.out.println("No se encontró un miembro con el ID: " + id);
            return;
        }

        System.out.print("Ingresa el nuevo nombre del miembro [Valor actual: " + miembro.getNombre() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            miembro.setNombre(name);
        }

        System.out.print("Ingresa los nuevos apellidos del miembro [Valor actual: " + miembro.getApellidos() + "]: ");
        String apellidos = scanner.nextLine();
        if (!apellidos.isEmpty()) {
            miembro.setApellidos(apellidos);
        }

        System.out.print("Ingresa la nueva direccion del miembro [Valor actual: " + miembro.getDireccion() + "]: ");
        String direccion = scanner.nextLine();
        if (!direccion.isEmpty()) {
            miembro.setDireccion(direccion);
        }

        System.out.print("Ingresa el nuevo telefono del miembro [Valor actual: " + miembro.getTelefono() + "]: ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) {
            miembro.setTelefono(telefono);
        }

        System.out.print("Ingresa el nuevo correo electronico del miembro [Valor actual: " + miembro.getCorreo() + "]: ");
        String correo = scanner.nextLine();
        if (!correo.isEmpty()) {
            miembro.setCorreo(correo);
        }

        System.out.print("Ingresa la nueva fecha de nacimiento del miembro [YYYY-MM-DD] [Valor actual: " + miembro.getFechaNacimiento() + "]: ");
        String fechaNacimiento = scanner.nextLine();
        if (!fechaNacimiento.isEmpty()) {
            try {
                miembro.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
            } catch (Exception e) {
                System.out.println("Formato de fecha no valido.");
                return;
            }
        }

        System.out.print("Ingresa el nuevo genero del miembro [M, F, O] [Valor actual: " + miembro.getGenero() + "]: ");
        String genero = scanner.nextLine();
        if (!genero.isEmpty()) {
            if (!genero.equalsIgnoreCase("M") && !genero.equalsIgnoreCase("F") && !genero.equalsIgnoreCase("O")) {
                System.out.println("Genero no valido.");
                return;
            }
            miembro.setGenero(genero.toUpperCase());
        }

        System.out.print("Ingresa la nueva fecha de inscripcion del miembro [YYYY-MM-DD] [Valor actual: " + miembro.getFechaInscripcion() + "]: ");
        String fechaInscripcion = scanner.nextLine();
        if (!fechaInscripcion.isEmpty()) {
            try {
                miembro.setFechaInscripcion(LocalDate.parse(fechaInscripcion));
            } catch (Exception e) {
                System.out.println("Formato de fecha no valido.");
                return;
            }
        }

        System.out.print("¿Deseas cambiar el tipo de membresia? (Si = 1, No != 1) ");
        if (getUserOption() == 1) {
            System.out.println("Tipos de Membresia a elegir: ");
            listAll(tipoMembresiaService.getAllTipoMembresia());

            System.out.print("Ingresa el ID del tipo de miembro: ");
            int tipoMembresiaId = Integer.parseInt(scanner.nextLine());

            TipoMembresia tipoMembresia = tipoMembresiaService.getTipoMembresiaById(tipoMembresiaId);

            if (tipoMembresia == null) {
                System.out.println("No se encontró un registro del tipo de miembro con el ID: " + tipoMembresiaId);
                return;
            }
            miembro.setTipoMembresia(tipoMembresia);
        }

        boolean success = miembroService.updateMiembro(miembro);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

    /* ----------------------------------------------------------------------------------
     *                             CRUD Menú para TIPOS DE MEMBRESIA
     * ---------------------------------------------------------------------------------- */
    private void showTipoMembresiaMenu() {
        while (true) {
            printMenu(
                    "Gestión Tipos de Membresia",
                    "Ver todos",
                    "Buscar por ID",
                    "Insertar",
                    "Actualizar",
                    "Eliminar",
                    "Regresar"
            );
            int option = getUserOption();
            switch (option) {
                case 1 -> listAll(tipoMembresiaService.getAllTipoMembresia());
                case 2 -> findById(tipoMembresiaService::getTipoMembresiaById);
                case 3 -> insertTipoMembresia();
                case 4 -> updateTipoMembresia();
                case 5 -> deleteById(
                        id -> tipoMembresiaService.deleteTipoMembresia(tipoMembresiaService.getTipoMembresiaById(id)),
                        tipoMembresiaService::getTipoMembresiaById
                );
                case 6 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void insertTipoMembresia() {
        System.out.print("Ingresa el nombre del tipo de membresia: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa la tarifa de la membresia: ");
        Double tarifa = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingresa la duración de la membresia en dias: ");
        Integer duracionDias = Integer.parseInt(scanner.nextLine());

        TipoMembresia tipoMembresia = new TipoMembresia();
        tipoMembresia.setNombre(nombre);
        tipoMembresia.setTarifa(tarifa);
        tipoMembresia.setDuracionDias(duracionDias);

        boolean success = tipoMembresiaService.insertTipoMembresia(tipoMembresia);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updateTipoMembresia() {
        System.out.print("Ingresa el ID del tipo de membresia: ");
        int id = Integer.parseInt(scanner.nextLine());
        TipoMembresia tipoMembresia = tipoMembresiaService.getTipoMembresiaById(id);

        if (tipoMembresia == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
            return;
        }

        System.out.print("Nuevo nombre [Actual: " + tipoMembresia.getNombre() + "]: ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            tipoMembresia.setNombre(nuevoNombre);
        }

        System.out.println("Nueva tarifa [Actual: " + tipoMembresia.getTarifa() + "]: ");
        String nuevaTarifa = scanner.nextLine();
        if (!nuevaTarifa.isEmpty()) {
            tipoMembresia.setTarifa(Double.parseDouble(nuevaTarifa));
        }

        System.out.println("Nueva duración de la membresia en dias [Actual: " + tipoMembresia.getDuracionDias() + "]: ");
        String nuevaDuracionDias = scanner.nextLine();
        if (!nuevaDuracionDias.isEmpty()) {
            tipoMembresia.setDuracionDias(Integer.parseInt(nuevaDuracionDias));
        }

        boolean success = tipoMembresiaService.updateTipoMembresia(tipoMembresia);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

    /* ----------------------------------------------------------------------------------
     *                              MÉTODOS DE UTILIDAD
     * ---------------------------------------------------------------------------------- */

    private void printMenu(String title, String... options) {
        System.out.println("\n-- " + title + " --");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Selecciona una opción: ");
    }

    /**
     * Lee una opción del usuario de la entrada estándar y valida que sea un número.
     */
    private int getUserOption() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingresa un número válido: ");
            }
        }
    }

    /**
     * Lista todos los elementos de una lista o muestra un mensaje si está vacía.
     */
    private void listAll(List<?> items) {
        if (items.isEmpty()) {
            System.out.println("No hay registros para mostrar.");
        } else {
            items.forEach(System.out::println);
        }
    }

    /**
     * Encuentra un objeto por ID usando una función de búsqueda, mostrando el resultado o un mensaje si no existe.
     */
    private void findById(ServiceFunction<Integer, ?> finder) {
        System.out.print("Ingresa el ID: ");
        int id = getUserOption();
        var result = finder.apply(id);
        if (result == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
        } else {
            System.out.println(result);
        }
    }

    /**
     * Elimina un objeto por ID usando la función de borrado y la de búsqueda para validar su existencia.
     */
    private void deleteById(ServiceFunction<Integer, Boolean> deleter, ServiceFunction<Integer, ?> finder) {
        System.out.print("Ingresa el ID: ");
        int id = getUserOption();
        var entity = finder.apply(id);
        if (entity == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
            return;
        }
        boolean success = deleter.apply(id);
        System.out.println(success ? "Registro eliminado correctamente." : "Error al eliminar el registro.");
    }

    /**
     * FuncionalInterface para pasar métodos que reciben un parámetro y devuelven un resultado.
     */
    @FunctionalInterface
    interface ServiceFunction<T, R> {
        R apply(T t);
    }
}
