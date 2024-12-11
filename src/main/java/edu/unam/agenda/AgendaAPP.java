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
    private Scanner sn;
    private final PhoneTypeService phoneTypeService = PhoneTypeServiceImpl.getInstance();
    private final ContactTypeService contactTypeService = ContactTypeServiceImpl.getInstance();
    private final ContactService contactService = ContactServiceImpl.getInstance();
    private final MeansContactsService meansContactsService = MeansContactsServiceImpl.getInstance();

    public static void main(String[] args) {
        HibernateUtil.init();
        AgendaAPP catalogosAPP = new AgendaAPP();
        catalogosAPP.cargaMenu();
    }

    public void cargaMenu() {
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        do {
            System.out.println("1. Catálogos");
            System.out.println("2. Contactos");
            System.out.println("3. Salir");
            try {
                sn = new Scanner(System.in);
                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();
                sn.nextLine();//limpieza
                switch (opcion) {
                    case 1:
                        System.out.println("-- Entrando menú catálogos --");
                        // Menu tipo teléfono y tipo contacto
                        System.out.println("1. Tipos Teléfonos");
                        System.out.println("2. Tipos Contactos");
                        System.out.println("3. Salir");
                        try {
                            opcion = sn.nextInt();
                            sn.nextLine();
                            switch (opcion) {
                                case 1:
                                    cargaMenuPhoneType();
                                    break;
                                case 2:
                                    cargaMenuContactType();
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Opción no existe");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debes insertar un número");
                            sn.next();
                        }
                        break;
                    case 2:
                        System.out.println("-- Entrando Contactos --");
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no existe");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
        while (!salir);
    }

    private void cargaMenuPhoneType() {
        System.out.println("Tipo de teléfono");
        System.out.println("1. Ver todos los tipos de teléfono");
        System.out.println("2. Buscar tipo de teléfono por ID");
        System.out.println("3. Insertar tipo de teléfono");
        System.out.println("4. Actualizar tipo de teléfono por ID");
        System.out.println("5. Eliminar tipo de teléfono por ID");
        System.out.println("6. Salir");
        System.out.print("Escribe una de las opciones: ");
        int menu2 = sn.nextInt();
        switch (menu2) {
            case 1:
                System.out.println("** Ver todos los tipos de teléfono **");
                List<PhoneType> listPhoneType = phoneTypeService.getAllPhoneTypes();
                for (PhoneType phone : listPhoneType)
                    System.out.println(phone.toString());
                break;
            case 2:
                System.out.println("** Buscar tipo de teléfono por ID **");
                System.out.print("Escribe el ID del tipo de teléfono: ");
                int id = sn.nextInt();
                PhoneType phoneType = phoneTypeService.getPhoneTypeById(id);
                if (phoneType == null)
                    System.out.println("No existe el ID " + id);
                else
                    System.out.println(phoneType.toString());
                break;
            case 3:
                System.out.println("** Insertar tipo de teléfono **");
                System.out.println("Ingresa el nombre del tipo de teléfono: ");
                String name = sn.next();
                System.out.println("Ingresa el estatus del tipo de teléfono (ACTIVO, INACTIVO): ");
                String status = sn.next();
                PhoneType phoneType1 = new PhoneType();
                phoneType1.setName(name);
                phoneType1.setStatus(status);
                Boolean b1 = phoneTypeService.insertPhoneType(phoneType1);
                System.out.println("Registro insertado: " + b1);
                break;
            case 4:
                System.out.println("** Actualizar tipo de teléfono por ID **");
                System.out.println("Ingresa el ID del tipo de teléfono: ");
                int id2 = sn.nextInt();
                PhoneType phoneType2 = phoneTypeService.getPhoneTypeById(id2);
                if (phoneType2 == null)
                    System.out.println("No existe el ID " + id2);
                else {
                    System.out.println("Ingresa el nombre del tipo de teléfono: ");
                    String name2 = sn.next();
                    System.out.println("Ingresa el estatus del tipo de teléfono (ACTIVO, INACTIVO): ");
                    String status2 = sn.next();
                    phoneType2.setName(name2);
                    phoneType2.setStatus(status2);
                    Boolean b2 = phoneTypeService.updatePhoneType(phoneType2);
                    System.out.println("Registro actualizado: " + b2);
                }
                break;
            case 5:
                System.out.println("** Eliminar tipo de teléfono por ID **");
                System.out.println("Ingresa el ID del tipo de teléfono: ");
                int id3 = sn.nextInt();
                PhoneType phoneType3 = phoneTypeService.getPhoneTypeById(id3);
                if (phoneType3 == null)
                    System.out.println("No existe el ID " + id3);
                else {
                    Boolean b3 = phoneTypeService.deletePhoneType(phoneType3);
                    System.out.println("Registro eliminado: " + b3);
                }
                break;
            case 6:
                System.out.println("** Salir **");
                break;
            default:
                System.out.println("Opción no existe");
        }
    }

    private void cargaMenuContactType() {
        System.out.println("Tipo de contacto");
        System.out.println("1. Ver todos los tipos de contactos");
        System.out.println("2. Buscar tipo de contacto por ID");
        System.out.println("3. Insertar tipo de contacto");
        System.out.println("4. Actualizar tipo de contacto por ID");
        System.out.println("5. Eliminar tipo de contacto por ID");
        System.out.println("6. Salir");
        System.out.print("Escribe una de las opciones: ");
        int menu2 = sn.nextInt();
        switch (menu2) {
            case 1:
                System.out.println("** Ver todos los tipos de contactos **");
                List<ContactType> listContactType = contactTypeService.getAllContactType();
                for (ContactType contactType : listContactType)
                    System.out.println(contactType.toString());
                break;
            case 2:
                System.out.println("** Buscar tipo de contacto por ID **");
                System.out.print("Escribe el ID del tipo de contacto: ");
                int id = sn.nextInt();
                ContactType contactType = contactTypeService.getContactTypeById(id);
                if (contactType == null)
                    System.out.println("No existe el ID " + id);
                else
                    System.out.println(contactType.toString());
                break;
            case 3:
                System.out.println("** Insertar tipo de contacto **");
                System.out.println("Ingresa el nombre del tipo de contacto: ");
                String name = sn.next();
                System.out.println("Ingresa el estatus del tipo de contacto (ACTIVO, INACTIVO): ");
                String status = sn.next();
                ContactType contactType1 = new ContactType();
                contactType1.setName(name);
                contactType1.setStatus(status);
                Boolean b1 = contactTypeService.insertContactType(contactType1);
                System.out.println("Registro insertado: " + b1);
                break;
            case 4:
                System.out.println("** Actualizar tipo de contacto por ID **");
                System.out.println("Ingresa el ID del tipo de contacto: ");
                int id2 = sn.nextInt();
                ContactType contactType2 = contactTypeService.getContactTypeById(id2);
                if (contactType2 == null)
                    System.out.println("No existe el ID " + id2);
                else {
                    System.out.println("Ingresa el nombre del tipo de contacto: ");
                    String name2 = sn.next();
                    System.out.println("Ingresa el estatus del tipo de contacto (ACTIVO, INACTIVO): ");
                    String status2 = sn.next();
                    contactType2.setName(name2);
                    contactType2.setStatus(status2);
                    Boolean b2 = contactTypeService.updateContactType(contactType2);
                    System.out.println("Registro actualizado: " + b2);
                }
                break;
            case 5:
                System.out.println("** Eliminar tipo de contacto por ID **");
                System.out.println("Ingresa el ID del tipo de contacto: ");
                int id3 = sn.nextInt();
                ContactType contactType3 = contactTypeService.getContactTypeById(id3);
                if (contactType3 == null)
                    System.out.println("No existe el ID " + id3);
                else {
                    Boolean b3 = contactTypeService.deleteContactType(contactType3);
                    System.out.println("Registro eliminado: " + b3);
                }
                break;
            case 6:
                System.out.println("** Salir **");
                break;
            default:
                System.out.println("Opción no existe");
        }
    }
}
