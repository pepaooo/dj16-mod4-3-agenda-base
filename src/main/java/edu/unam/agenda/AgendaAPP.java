package edu.unam.agenda;

import edu.unam.agenda.hibernate.HibernateUtil;
import edu.unam.agenda.model.ContactType;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.ContactService;
import edu.unam.agenda.service.ContactTypeService;
import edu.unam.agenda.service.MeansContactsService;
import edu.unam.agenda.service.PhoneTypeService;
import edu.unam.agenda.service.impl.ContactServiceImpl;
import edu.unam.agenda.service.impl.ContactTypeServiceImpl;
import edu.unam.agenda.service.impl.MeansContactsServiceImpl;
import edu.unam.agenda.service.impl.PhoneTypeServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AgendaAPP {
    private final Scanner scanner = new Scanner(System.in);
    private final PhoneTypeService phoneTypeService = PhoneTypeServiceImpl.getInstance();
    private final ContactTypeService contactTypeService = ContactTypeServiceImpl.getInstance();
    private final ContactService contactService = ContactServiceImpl.getInstance();
    private final MeansContactsService meansContactsService = MeansContactsServiceImpl.getInstance();

    public static void main(String[] args) {
        HibernateUtil.init();
        new AgendaAPP().startMenu();
    }

    public void startMenu() {
        while (true) {
            printMenu("Menú Principal", "Catálogos", "Contactos", "Salir");
            int option = getUserOption();
            switch (option) {
                case 1 -> catalogMenu();
                case 2 -> contactMenu();
                case 3 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void catalogMenu() {
        while (true) {
            printMenu("Menú Catálogos", "Tipos Teléfonos", "Tipos Contactos", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> managePhoneTypes();
                case 2 -> manageContactTypes();
                case 3 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void contactMenu() {
        while (true) {
            printMenu("Menú Contactos", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void managePhoneTypes() {
        while (true) {
            printMenu("Gestión Tipos de Teléfono", "Ver todos", "Buscar por ID", "Insertar", "Actualizar", "Eliminar", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> listAll(phoneTypeService.getAllPhoneTypes());
                case 2 -> findById(phoneTypeService::getPhoneTypeById);
                case 3 -> insertPhoneType();
                case 4 -> updatePhoneType();
                case 5 -> deleteById(
                        id -> phoneTypeService.deletePhoneType(phoneTypeService.getPhoneTypeById(id)),
                        phoneTypeService::getPhoneTypeById
                );
                case 6 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void manageContactTypes() {
        while (true) {
            printMenu("Gestión Tipos de Contacto", "Ver todos", "Buscar por ID", "Insertar", "Actualizar", "Eliminar", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> listAll(contactTypeService.getAllContactType());
                case 2 -> findById(contactTypeService::getContactTypeById);
                case 3 -> insertContactType();
                case 4 -> updateContactType();
                case 5 -> deleteById(
                        id -> contactTypeService.deleteContactType(contactTypeService.getContactTypeById(id)),
                        contactTypeService::getContactTypeById
                );
                case 6 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void printMenu(String title, String... options) {
        System.out.println("-- " + title + " --");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Selecciona una opción: ");
    }

    private int getUserOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingresa un número válido.");
            scanner.next(); // Clear invalid input
            return -1;
        }
    }

    private void listAll(List<?> items) {
        if (items.isEmpty()) {
            System.out.println("No hay registros para mostrar.");
        } else {
            items.forEach(System.out::println);
        }
    }

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

    private void insertPhoneType() {
        System.out.print("Ingresa el nombre del tipo de teléfono: ");
        String name = scanner.next();
        System.out.print("Ingresa el estatus (ACTIVO, INACTIVO): ");
        String status = scanner.next();
        PhoneType phoneType = new PhoneType();
        phoneType.setName(name);
        phoneType.setStatus(status);
        boolean success = phoneTypeService.insertPhoneType(phoneType);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updatePhoneType() {
        System.out.print("Ingresa el ID del tipo de teléfono: ");
        int id = getUserOption();
        PhoneType phoneType = phoneTypeService.getPhoneTypeById(id);
        if (phoneType == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
            return;
        }
        System.out.print("Nuevo nombre: ");
        phoneType.setName(scanner.next());
        System.out.print("Nuevo estatus (ACTIVO, INACTIVO): ");
        phoneType.setStatus(scanner.next());
        boolean success = phoneTypeService.updatePhoneType(phoneType);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

    private void insertContactType() {
        System.out.print("Ingresa el nombre del tipo de contacto: ");
        String name = scanner.next();
        System.out.print("Ingresa el estatus (ACTIVO, INACTIVO): ");
        String status = scanner.next();
        ContactType contactType = new ContactType();
        contactType.setName(name);
        contactType.setStatus(status);
        boolean success = contactTypeService.insertContactType(contactType);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updateContactType() {
        System.out.print("Ingresa el ID del tipo de contacto: ");
        int id = getUserOption();
        ContactType contactType = contactTypeService.getContactTypeById(id);
        if (contactType == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
            return;
        }
        System.out.print("Nuevo nombre: ");
        contactType.setName(scanner.next());
        System.out.print("Nuevo estatus (ACTIVO, INACTIVO): ");
        contactType.setStatus(scanner.next());
        boolean success = contactTypeService.updateContactType(contactType);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

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


    @FunctionalInterface
    interface ServiceFunction<T, R> {
        R apply(T t);
    }
}
