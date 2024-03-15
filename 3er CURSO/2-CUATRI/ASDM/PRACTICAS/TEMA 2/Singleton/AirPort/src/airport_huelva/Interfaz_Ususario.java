
package airport_huelva;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Interfaz_Ususario {
    
    private Aeropuerto Aero; //Instancia Singleton
    private ArrayList<Vuelo> Vuelos; //Instancia del valor del singleton
    
    Interfaz_Ususario(int tipoUser){
        Aero = Aeropuerto.getAeropuerto();
        Vuelos = Aero.getVuelos();
        
        int opcion = 0;
        int continuar = -1;
        Scanner sc=new Scanner(System.in);
        Scanner sc1=new Scanner(System.in);
        
        //Mostramos el menu segun el tipo de usuario
        if(tipoUser == 0){
            do {
                menuPasajero();
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n\n\n");
                        muestraVuelos();
                        break;
                        
                    case 2:
                        System.out.println("\nNos vemos en otro momento :)");
                        break;
                        
                    default:
                        System.out.println("!! Elige una opcion correcta ¡¡");;
                }
                
            } while (opcion != 2);
        }
        else{
            do {                
                muestraVuelos();
                menuAdmin();
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1: //Nuevo Vuelo
                        Vuelo newVuelo = new Vuelo();
                        System.out.print("Indica NumVuelo: ");
                        newVuelo.setNumVuelo(sc1.nextLine());
                        
                        if(Aero.existeVuelo(newVuelo.getNumVuelo()) != -1)
                            System.out.println("## ERROR: El Num. de Vuelo ya existe ##");
                        
                        else{
                            System.out.print("Indica la Compañia: ");
                            newVuelo.setCompania(sc1.nextLine());
                        
                            System.out.print("Indica el Destino: ");
                            newVuelo.setDestino(sc1.nextLine());
                        
                            System.out.print("Indica la Puerta de Embarque: ");
                            newVuelo.setPuertaEmbarque(sc1.nextInt());
                            
                            sc1.nextLine();
                            
                            newVuelo.setEstado("En hora");
                            
                            int hS = -1, hLl = -1;
                            do {                                
                                System.out.print("Indica la Hora de Salida del Vuelo: ");
                                hS = sc1.nextInt();
                                sc1.nextLine();
                                
                                System.out.print("Indica la Hora de Llegada del Vuelo: ");
                                hLl = sc1.nextInt();
                                sc1.nextLine();
                                
                                if(hS >= hLl)
                                    System.out.println("¡¡ La hora de Salida debe de ser más pequeña que la de Llegada !!");
                                else{
                                    newVuelo.setHoraSalida(hS);
                                    newVuelo.setHoraLlegada(hLl);
                                }
                            } while (hS >= hLl);
                            
                            Aero.nuevoVuelo(newVuelo);
                        }
                        break;
                        
                    case 2: //Modificar Vuelo
                        String NumV;
                        System.out.print("Indica el NumVuelo del Vuelo a modificar: ");
                        NumV = sc1.nextLine();
                        
                        int pos = Aero.existeVuelo(NumV);
                        if(pos == -1){
                            System.out.println("## ERROR: El Num. de Vuelo NO existe ##");
                        }
                        else{
                            Vuelo v = Vuelos.get(pos);
                            System.out.println("\n\n== Se ha encontrado el siguiete vuelo ==\n");
                            muestraUnVuelo(v);
                            
                            System.out.print("Indique el nuevo estado del Vuelo: ");
                            v.setEstado(sc1.nextLine());
                            
                            Aero.modificarVuelo(v, pos);
                        }
                        break;
                        
                    case 3: //Eliminar Vuelo
                        String nV;
                        System.out.print("Indica el NumVuelo del Vuelo que quieras eliminar: ");
                        nV = sc1.nextLine();
                        Aero.eliminarVuelo(nV);
                        break;
                        
                    case 4: //Salir
                        System.out.println("\nNos vemos en otro momento :)");
                        break;
                        
                    default:
                        System.out.println("!! Elige una opcion correcta ¡¡");;
                }
                
                while(continuar != 0) {
                    System.out.print("\nPulsa 0 para continuar: ");
                    continuar = sc.nextInt();
                }
                continuar = -1;
                
            } while (opcion != 4);
        }
    }
    
    
    //Muestra todos los vuelos disponibles
    public void muestraVuelos(){
        System.out.println("=========================================================================================");
        System.out.println(" Compañia | Destino | Num. Vuelo | Puerta Embarque | Estado | Hora Salida | Hora llegada");
        System.out.println("=========================================================================================");
        
        for (int i = 0; i < Vuelos.size(); i++) {
            System.out.println(" " + Vuelos.get(i).getCompania() + "   " + Vuelos.get(i).getDestino() + "     " +
                    Vuelos.get(i).getNumVuelo() + "          " + Vuelos.get(i).getPuertaEmbarque() + "         " + 
                    Vuelos.get(i).getEstado() + "      " + Vuelos.get(i).getHoraSalida() + "            " + Vuelos.get(i).getHoraLlegada());
        }
        System.out.println("=========================================================================================");
    }
    
    
    //Muestra el vuelo pasado por parametro
    public void muestraUnVuelo(Vuelo v){
        System.out.println("=========================================================================================");
        System.out.println(" Compañia | Destino | Num. Vuelo | Puerta Embarque | Estado | Hora Salida | Hora llegada");
        System.out.println("=========================================================================================");
          
        System.out.println(" " + v.getCompania() + "   " + v.getDestino() + "     " + v.getNumVuelo() + "          " + 
                v.getPuertaEmbarque() + "         " + v.getEstado() + "      " + v.getHoraSalida() + "            " + 
                v.getHoraLlegada());
        
        System.out.println("=========================================================================================");
    }
    
    public void menuPasajero(){
        System.out.println("\n");
        System.out.println("1) Visualizar Pantalla");
        System.out.println("2) Salir");
        System.out.println("-------------------------");
    }
    
    public void menuAdmin(){
        System.out.println("\n");
        System.out.println("1) Añadir Vuelo");
        System.out.println("2) Modificar Vuelo");
        System.out.println("3) Eliminar Vuelo");
        System.out.println("4) Salir");
        System.out.println("-------------------------");
    }
}
