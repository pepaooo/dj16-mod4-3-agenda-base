package edu.unam.agenda;

import edu.unam.agenda.hibernate.HibernateUtil;
import edu.unam.agenda.model.Contact;
import edu.unam.agenda.model.ContactType;
import edu.unam.agenda.model.MeansContacts;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.*;
import edu.unam.agenda.service.impl.*;

import java.util.*;

// José Luis Angulo Cabello
public class AgendaAPP {

    private final Scanner scanner = new Scanner(System.in);

    private final PhoneTypeService phoneTypeService = PhoneTypeServiceImpl.getInstance();
    private final ContactTypeService contactTypeService = ContactTypeServiceImpl.getInstance();
    private final ContactService contactService = ContactServiceImpl.getInstance();
    private final MeansContactsService meansContactsService = MeansContactsServiceImpl.getInstance();

    public static void main(String[] args) {
        HibernateUtil.init();
        new AgendaAPP().showMainMenu();
    }

    /**
     * Menú principal de la aplicación.
     */
    public void showMainMenu() {
        while (true) {
            printMenu("Menú Principal", "Catálogos", "Contactos", "Salir");
            int option = getUserOption();
            switch (option) {
                case 1 -> showCatalogMenu();
                case 2 -> showContactsMenu();
                case 3 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Menú de catálogos (Tipos de Teléfono y Tipos de Contacto).
     */
    private void showCatalogMenu() {
        while (true) {
            printMenu("Menú Catálogos", "Tipos de Teléfono", "Tipos de Contacto", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> showPhoneTypesMenu();
                case 2 -> showContactTypesMenu();
                case 3 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Menú de Contactos (Contacto y Medios de Contacto).
     */
    private void showContactsMenu() {
        while (true) {
            printMenu("Menú Contactos", "Contacto", "Medios de Contacto", "Regresar");
            int option = getUserOption();
            switch (option) {
                case 1 -> manageContacts();
                case 2 -> manageMeansContacts();
                case 3 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /* ----------------------------------------------------------------------------------
     *                        CRUD Menú para MEDIOS DE CONTACTO
     * ---------------------------------------------------------------------------------- */
    private void manageMeansContacts() {
        while (true) {
            printMenu(
                    "Gestión de Medios de Contacto",
                    "Ver todos",
                    "Buscar por ID",
                    "Buscar por Contacto",
                    "Insertar",
                    "Actualizar",
                    "Eliminar",
                    "Regresar"
            );
            int option = getUserOption();
            switch (option) {
                case 1 -> listAll(meansContactsService.getAllMeansContacts());
                case 2 -> findById(meansContactsService::getMeansContactsById);
                case 3 -> findById(meansContactsService::getMeansContactsByContact);
                case 4 -> insertMeansContacts();
                case 5 -> updateMeansContacts();
                case 6 -> deleteById(
                        id -> meansContactsService.deleteMeansContacts(meansContactsService.getMeansContactsById(id)),
                        meansContactsService::getMeansContactsById
                );
                case 7 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void insertMeansContacts() {
        System.out.print("Ingresa el ID del contacto: ");
        int contactId = Integer.parseInt(scanner.nextLine());
        Contact contact = contactService.getContactById(contactId);

        if (contact == null) {
            System.out.println("No se encontró un contacto con el ID: " + contactId);
            return;
        }

        System.out.println("Tipos de teléfono a elegir: ");
        List<PhoneType> phoneTypes = phoneTypeService.getAllPhoneTypes();
        phoneTypes.forEach(System.out::println);

        System.out.print("Ingresa el ID del tipo de teléfono: ");
        int phoneTypeId = Integer.parseInt(scanner.nextLine());
        PhoneType phoneType = phoneTypeService.getPhoneTypeById(phoneTypeId);

        if (phoneType == null) {
            System.out.println("No se encontró un tipo de teléfono con el ID: " + phoneTypeId);
            return;
        }

        System.out.print("Ingresa el valor del medio de contacto: ");
        String value = scanner.nextLine();

        MeansContacts meansContacts = new MeansContacts();
        meansContacts.setContact(contact);
        meansContacts.setPhoneType(phoneType);
        meansContacts.setValue(value);

        boolean success = meansContactsService.insertMeansContacts(meansContacts);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updateMeansContacts() {
        System.out.print("Ingresa el ID del contacto: ");
        int contactId = Integer.parseInt(scanner.nextLine());
        Contact contact = contactService.getContactById(contactId);

        if (contact == null) {
            System.out.println("No se encontró un contacto con el ID: " + contactId);
            return;
        }

        System.out.println("Medios de contacto asociados al contacto: ");
        Set<MeansContacts> currentMeansContacts = contact.getMeansContacts();
        currentMeansContacts.forEach(System.out::println);

        System.out.print("Ingresa el ID del medio de contacto a actualizar: ");
        int meansContactsId = Integer.parseInt(scanner.nextLine());
        MeansContacts meansContacts = currentMeansContacts
                .stream()
                .filter(m -> m.getId() == meansContactsId)
                .findFirst()
                .orElse(null);

        if (meansContacts == null) {
            System.out.println("No se encontró un medio de contacto con el ID: " + meansContactsId
                    + " asociado al contacto con el ID: " + contactId);
            return;
        }

        System.out.print("Ingresa el nuevo valor del medio de contacto [Valor actual: "
                + meansContacts.getValue() + "]: ");
        String value = scanner.nextLine();
        if (!value.isEmpty()) {
            meansContacts.setValue(value);
        }

        System.out.print("¿Deseas cambiar el tipo de teléfono? (Si = 1, No != 1) ");
        if (getUserOption() == 1) {
            System.out.println("Tipos de teléfono a elegir: ");
            List<PhoneType> phoneTypes = phoneTypeService.getAllPhoneTypes();
            phoneTypes.forEach(System.out::println);

            System.out.print("Ingresa el ID del tipo de teléfono: ");
            int phoneTypeId = Integer.parseInt(scanner.nextLine());
            PhoneType phoneType = phoneTypeService.getPhoneTypeById(phoneTypeId);

            if (phoneType == null) {
                System.out.println("No se encontró un tipo de teléfono con el ID: " + phoneTypeId);
                return;
            }
            meansContacts.setPhoneType(phoneType);
        }

        boolean success = meansContactsService.updateMeansContacts(meansContacts);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

    /* ----------------------------------------------------------------------------------
     *                               CRUD Menú para CONTACTOS
     * ---------------------------------------------------------------------------------- */
    private void manageContacts() {
        while (true) {
            printMenu(
                    "Gestión de Contactos",
                    "Ver todos",
                    "Buscar por ID",
                    "Insertar",
                    "Actualizar",
                    "Eliminar",
                    "Regresar"
            );
            int option = getUserOption();
            switch (option) {
                case 1 -> listAll(contactService.getAllContacts());
                case 2 -> findById(contactService::getContactById);
                case 3 -> insertContact();
                case 4 -> updateContact();
                case 5 -> deleteById(
                        id -> contactService.deleteContact(contactService.getContactById(id)),
                        contactService::getContactById
                );
                case 6 -> {
                    System.out.println("Regresando...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void insertContact() {
        Contact contact = new Contact();

        System.out.print("Ingresa el nombre del contacto: ");
        contact.setName(scanner.nextLine());

        System.out.print("Ingresa los apellidos del contacto: ");
        contact.setLastName(scanner.nextLine());

        System.out.print("Ingresa la edad del contacto: ");
        contact.setAge(Integer.parseInt(scanner.nextLine()));

        System.out.print("Ingresa la dirección del contacto: ");
        contact.setAddress(scanner.nextLine());

        System.out.println("Tipos de Contactos a elegir: ");
        List<ContactType> contactTypes = contactTypeService.getAllContactType();
        contactTypes.forEach(System.out::println);

        System.out.print("Ingresa el ID del tipo de contacto: ");
        int contactTypeId = Integer.parseInt(scanner.nextLine());
        ContactType contactType = contactTypeService.getContactTypeById(contactTypeId);

        if (contactType == null) {
            System.out.println("No se encontró un registro del tipo de contacto con el ID: " + contactTypeId);
            return;
        }
        contact.setContactType(contactType);

        // Se asume que al insertar un Contacto, de una vez agregamos un MeansContacts
        System.out.println("Tipos de Teléfono a elegir: ");
        List<PhoneType> phoneTypes = phoneTypeService.getAllPhoneTypes();
        phoneTypes.forEach(System.out::println);

        System.out.print("Ingresa el ID del tipo de teléfono: ");
        int phoneTypeId = Integer.parseInt(scanner.nextLine());
        PhoneType phoneType = phoneTypeService.getPhoneTypeById(phoneTypeId);

        if (phoneType == null) {
            System.out.println("No se encontró un registro del medio de contacto con el ID: " + phoneTypeId);
            return;
        }

        System.out.print("Ingresa el número del medio de contacto: ");
        String number = scanner.nextLine();
        MeansContacts contactoMedio = new MeansContacts();
        contactoMedio.setValue(number);
        contactoMedio.setPhoneType(phoneType);
        contactoMedio.setContact(contact);

        Set<MeansContacts> mediosContacto = new HashSet<>();
        mediosContacto.add(contactoMedio);
        contact.setMeansContacts(mediosContacto);

        boolean success = contactService.insertContact(contact);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updateContact() {
        System.out.print("Ingresa el ID del contacto a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            System.out.println("No se encontró un contacto con el ID: " + id);
            return;
        }

        System.out.print("Ingresa el nuevo nombre del contacto [Valor actual: " + contact.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            contact.setName(name);
        }

        System.out.print("Ingresa los nuevos apellidos del contacto [Valor actual: "
                + contact.getLastName() + "]: ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            contact.setLastName(lastName);
        }

        System.out.print("Ingresa la nueva edad del contacto [Valor actual: "
                + contact.getAge() + "]: ");
        String strAge = scanner.nextLine();
        if (!strAge.isEmpty()) {
            contact.setAge(Integer.parseInt(strAge));
        }

        System.out.print("Ingresa la nueva dirección del contacto [Valor actual: "
                + contact.getAddress() + "]: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            contact.setAddress(address);
        }

        System.out.print("¿Deseas cambiar el tipo de contacto? (Si = 1, No != 1) ");
        if (getUserOption() == 1) {
            System.out.println("Tipos de Contactos a elegir: ");
            List<ContactType> contactTypes = contactTypeService.getAllContactType();
            contactTypes.forEach(System.out::println);

            System.out.print("Ingresa el ID del tipo de contacto: ");
            int contactTypeId = Integer.parseInt(scanner.nextLine());;
            ContactType contactType = contactTypeService.getContactTypeById(contactTypeId);

            if (contactType == null) {
                System.out.println("No se encontró un registro del tipo de contacto con el ID: " + contactTypeId);
                return;
            }
            contact.setContactType(contactType);
        }

        boolean success = contactService.updateContact(contact);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

    /* ----------------------------------------------------------------------------------
     *                             CRUD Menú para TIPOS DE TELÉFONO
     * ---------------------------------------------------------------------------------- */
    private void showPhoneTypesMenu() {
        while (true) {
            printMenu(
                    "Gestión Tipos de Teléfono",
                    "Ver todos",
                    "Buscar por ID",
                    "Insertar",
                    "Actualizar",
                    "Eliminar",
                    "Regresar"
            );
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

    private void insertPhoneType() {
        System.out.print("Ingresa el nombre del tipo de teléfono: ");
        String name = scanner.nextLine();
        System.out.print("Ingresa el estatus (ACTIVO, INACTIVO): ");
        String status = scanner.nextLine();

        PhoneType phoneType = new PhoneType();
        phoneType.setName(name);
        phoneType.setStatus(status);

        boolean success = phoneTypeService.insertPhoneType(phoneType);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updatePhoneType() {
        System.out.print("Ingresa el ID del tipo de teléfono: ");
        int id = Integer.parseInt(scanner.nextLine());
        PhoneType phoneType = phoneTypeService.getPhoneTypeById(id);

        if (phoneType == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
            return;
        }

        System.out.print("Nuevo nombre [Actual: " + phoneType.getName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            phoneType.setName(newName);
        }

        System.out.print("Nuevo estatus (ACTIVO, INACTIVO) [Actual: " + phoneType.getStatus() + "]: ");
        String newStatus = scanner.nextLine();
        if (!newStatus.isEmpty()) {
            phoneType.setStatus(newStatus);
        }

        boolean success = phoneTypeService.updatePhoneType(phoneType);
        System.out.println(success ? "Registro actualizado correctamente." : "Error al actualizar el registro.");
    }

    /* ----------------------------------------------------------------------------------
     *                           CRUD Menú para TIPOS DE CONTACTO
     * ---------------------------------------------------------------------------------- */
    private void showContactTypesMenu() {
        while (true) {
            printMenu(
                    "Gestión Tipos de Contacto",
                    "Ver todos",
                    "Buscar por ID",
                    "Insertar",
                    "Actualizar",
                    "Eliminar",
                    "Regresar"
            );
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

    private void insertContactType() {
        System.out.print("Ingresa el nombre del tipo de contacto: ");
        String name = scanner.nextLine();
        System.out.print("Ingresa el estatus (ACTIVO, INACTIVO): ");
        String status = scanner.nextLine();

        ContactType contactType = new ContactType();
        contactType.setName(name);
        contactType.setStatus(status);

        boolean success = contactTypeService.insertContactType(contactType);
        System.out.println(success ? "Registro insertado correctamente." : "Error al insertar el registro.");
    }

    private void updateContactType() {
        System.out.print("Ingresa el ID del tipo de contacto: ");
        int id = Integer.parseInt(scanner.nextLine());
        ContactType contactType = contactTypeService.getContactTypeById(id);

        if (contactType == null) {
            System.out.println("No se encontró un registro con el ID: " + id);
            return;
        }

        System.out.print("Nuevo nombre [Actual: " + contactType.getName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            contactType.setName(newName);
        }

        System.out.print("Nuevo estatus (ACTIVO, INACTIVO) [Actual: " + contactType.getStatus() + "]: ");
        String newStatus = scanner.nextLine();
        if (!newStatus.isEmpty()) {
            contactType.setStatus(newStatus);
        }

        boolean success = contactTypeService.updateContactType(contactType);
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
