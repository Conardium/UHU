package familias;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ismael Da Palma Fernandez
 */
public class Usuario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sc_St = new Scanner(System.in);

        Interfaz_Usuario iu = new Interfaz_Usuario();
 
        ArrayList<Familia_Prototype> familias = new ArrayList();
        Familia_Prototype anteriorFamiliar = null;
        Familia_Prototype clon;
        Familia_Prototype nuevoFamiliar;
        
        //Familiar de ejemplo
        /*Familia_Prototype f1 = new Padre("Ismael", "Da Palma", "123456789", "Calle Alcalá", "21600", "isma@gmail.com", "Universidad");
        familias.add(f1);
        anteriorFamiliar = f1.clonarTodo();*/
        
        String nombre, apellido, telefono, direccion, codPostal, correo, estudios, rol;

        int opcion = 0;

        do {
            iu.menuPrincipal();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: //Clonar el ultimo familiar
                    if(familias.size() == 0)
                        System.out.println("<<Todavía no existe ningun familiar para clonar>>");
                    else{
                        System.out.println("\n!Clonacion realizada con exito!");
                        clon = anteriorFamiliar.clonarTodo();
                        familias.add(clon);
                    }
                    break;
                    
                case 2: //Añadir un nuevo familiar
                    if(familias.size() == 0){ //Si todavia no existe ningun familiar, creamos uno desde cero
                        System.out.print("Introduce nombre: ");
                        nombre = sc_St.nextLine();
                        System.out.print("Introduce apellido: ");
                        apellido = sc_St.nextLine();
                        System.out.print("Introduce telefono: ");
                        telefono = sc_St.nextLine();
                        System.out.print("Introduce direccion: ");
                        direccion = sc_St.nextLine();
                        System.out.print("Introduce codigo postal: ");
                        codPostal = sc_St.nextLine();
                        System.out.print("Introduce correo: ");
                        correo = sc_St.nextLine();
                        System.out.print("Introduce estudios: ");
                        estudios = sc_St.nextLine();
                        System.out.print("Indica el rol del familiar(1.-Padre, 2.-Madre, 3.-Hijo, 4.-Hija): ");
                        rol = sc_St.nextLine();
                        
                        switch (rol) {
                            case "1":
                                nuevoFamiliar = new Padre(nombre, apellido, telefono, direccion, codPostal, correo, estudios);
                                System.out.println("...Familiar creado correctamente...");
                                nuevoFamiliar.mostrarDatos();
                                familias.add(nuevoFamiliar);
                                anteriorFamiliar = nuevoFamiliar.clonarTodo();
                                break;
                                
                            case "2":
                                nuevoFamiliar = new Madre(nombre, apellido, telefono, direccion, codPostal, correo, estudios);
                                System.out.println("...Familiar creado correctamente...");
                                nuevoFamiliar.mostrarDatos();
                                familias.add(nuevoFamiliar);
                                anteriorFamiliar = nuevoFamiliar.clonarTodo();
                                break;
                                
                            case "3":
                                nuevoFamiliar = new Hijo(nombre, apellido, telefono, direccion, codPostal, correo, estudios);
                                System.out.println("...Familiar creado correctamente...");
                                nuevoFamiliar.mostrarDatos();
                                familias.add(nuevoFamiliar);
                                anteriorFamiliar = nuevoFamiliar.clonarTodo();
                                break;
                                
                            case "4":
                                nuevoFamiliar = new Hija(nombre, apellido, telefono, direccion, codPostal, correo, estudios);
                                System.out.println("...Familiar creado correctamente...");
                                nuevoFamiliar.mostrarDatos();
                                familias.add(nuevoFamiliar);
                                anteriorFamiliar = nuevoFamiliar.clonarTodo();
                                break;
                        }                      
                    }
                    else{ //En otro caso, copiamos los atributos comunes del ultimo familiar y cambiamos los no comunes
                        clon = anteriorFamiliar.clonar(); //Clonacion parcial

                        System.out.print("Introduce nombre: ");
                        clon.setNombre(sc_St.nextLine());
                        System.out.print("Introduce correo: ");
                        clon.setEmail(sc_St.nextLine());
                        System.out.print("Introduce estudios: ");
                        clon.setEstudios(sc_St.nextLine());
                        System.out.print("Indica el rol del familiar (Padre, Madre, Hijo, Hija): ");
                        clon.setRol(sc_St.nextLine());
                        
                        System.out.println("...Familiar creado correctamente...");
                        anteriorFamiliar = clon.clonarTodo();
                        familias.add(clon);
                    }
                    break;
                    
                case 3: //Mostrar los familiares (datos simplificados)
                    if(familias.size() == 0)
                        System.out.println("\n<<No hay ningun familiar que listar>>");
                    else {
                        System.out.println("\n##### Listado de la familia -" + familias.get(0).getApellido() + "- #####");
                        for (int i = 0; i < familias.size(); i++) {
                            System.out.println("Familiar " + i + " - " + familias.get(i).getNombre() + ", " + familias.get(i).getRol());
                        }
                    }
                    break;
                    
                case 4: //Mostrar los familiares (datos completos)
                    if(familias.size() == 0)
                        System.out.println("\n<<No hay ningun familiar que listar>>");
                    else {
                        System.out.println("\n##### Listado de la familia -" + familias.get(0).getApellido() + "- #####");
                        for (int i = 0; i < familias.size(); i++) {
                            familias.get(i).mostrarDatos();
                        }
                    }
                    break;
                    
                case 5: //Eliminar Familiar
                    System.out.print("Indica la posicion del familiar a eliminar: ");
                    opcion = sc.nextInt();
                    if (opcion < 0 || opcion > familias.size()) {
                        System.out.println("<<Elige una posicion correcta>>");
                    }
                    else
                        familias.remove(opcion);
                    break;
                    
                case 6: //Salir
                    break;
            }
            System.out.println("");
        } while (opcion != 6);
    }

}
