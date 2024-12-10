package edu.unam.agenda;

import edu.unam.agenda.hibernate.HibernateUtil;
import edu.unam.agenda.model.PhoneType;
import edu.unam.agenda.service.MeansContactsService;
import edu.unam.agenda.service.ContactService;
import edu.unam.agenda.service.PhoneTypeService;
import edu.unam.agenda.service.ContactTypeService;
import edu.unam.agenda.service.impl.MeansContactsServiceImpl;
import edu.unam.agenda.service.impl.ContactServiceImpl;
import edu.unam.agenda.service.impl.PhoneTypeServiceImpl;
import edu.unam.agenda.service.impl.ContactTypeServiceImpl;

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
                System.out.println("Escribe una de las opciones");
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
                                    System.out.println("Tipo de teléfono");
                                    System.out.println("1. Ver todos los tipos de teléfono");
                                    System.out.println("2. Buscar tipo de telefón por ID");
                                    System.out.println("3. Insertar tipo de teléfono");
                                    System.out.println("4. Actualizar tipo de teléfono por ID");
                                    System.out.println("5. Eliminar tipo de teléfono por ID");
                                    System.out.println("6. Salir");
                                    System.out.println("Escribe una de las opciones");
                                    int menu2 = sn.nextInt();
                                    switch (menu2) {
                                        case 1:
                                            System.out.println("Find All");
                                            List<PhoneType> listPhoneType = phoneTypeService.getAllPhoneTypes();
                                            for (PhoneType phone : listPhoneType)
                                                System.out.println(phone.toString());
                                            break;
                                        case 2:
                                            PhoneType phoneType = phoneTypeService.getPhoneTypeById(1);
                                            System.out.println(phoneType.toString());
                                            break;
                                        case 3:
                                            PhoneType phoneType2 = new PhoneType();
                                            phoneType2.setName("Casa");
                                            phoneType2.setStatus("ACTIVO");
                                            phoneTypeService.insertPhoneType(phoneType2);
                                            break;
                                        case 4:
                                            PhoneType phoneType3 = new PhoneType();
                                            phoneType3.setId(1);
                                    }
                                    //phoneTypeService.cargaMenu();
                                    break;
                                case 2:
                                    System.out.println("Tipo de contacto");
                                    //contactTypeService.cargaMenu();
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
}
