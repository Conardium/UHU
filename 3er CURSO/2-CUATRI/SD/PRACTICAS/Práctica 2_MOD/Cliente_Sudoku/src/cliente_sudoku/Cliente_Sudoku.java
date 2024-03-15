package cliente_sudoku;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria_sudoku.GestorJuegosIntf;
import libreria_sudoku.TipoDificultad;

/**
 *
 * @author Ismael Da Palma Fernandez
 */
public class Cliente_Sudoku {

    static Scanner sc_int = new Scanner(System.in);
    static Scanner sc_str = new Scanner(System.in);
    static ArrayList<ListaSudoku> Sudokus = new ArrayList(); //Inicialmente vacio
    static int aviso = 0;
    static String Ayuda = "";
    static int fila_aux, col_aux;

    public static int MenuPrincipal(){
        int valor;
        
        System.out.println("---- Servidor de Juegos ----");
        System.out.println("1.- Gestionar Juegos."); //Llama a MenuGestion
        System.out.println("2.- Jugar."); //Llama a MenuJuego (Si el sudoku esta vacio no deja jugar)
        System.out.println("0.- Salir.");
        System.out.print("\nElige Opción: ");
        valor = sc_int.nextInt();
        
        return valor;
    }
    
    public static int MenuGestion() {
        int valor;
        
        System.out.println("---- Gestión de Juegos ----");
        System.out.println("1.- Crear Juego."); //Llama a MenuDificultad
        System.out.println("2.- Borrar Juego.");
        System.out.println("3.- Seleccionar Juego."); //Llama a ListarSudokus
        System.out.println("0.- Salir."); 
        System.out.print("\nElige Opción: ");
        valor = sc_int.nextInt();

        return valor;
    }
    
    public static int MenuJuego() {
        int valor;
        
        System.out.println("---- Menú de Juegos ----");
        System.out.println("1.- Poner Valor.");
        System.out.println("2.- Borrar Valor.");
        System.out.println("3.- Ayuda.");
        System.out.println("0.- Salir."); 
        System.out.print("\nElige Opción: ");
        valor = sc_int.nextInt();

        return valor;
    }

    public static int ListarJuegos(boolean Borrar){
        int valor;
        System.out.println("\n\nLista de Juegos Almacenados\n");
        System.out.println("Posición        Código          Dificultad");
        System.out.println("------------------------------------------");
        int j = 1;
        for (int i = 0; i < Sudokus.size(); i++) {
            System.out.println(j++ + "\t\t" + Sudokus.get(i).Codigo + "\t\t" + Sudokus.get(i).Dificultad);
        }
        if(Borrar)
            System.out.print("\nIntroduce la posición del Juego a borrar: ");
        else
            System.out.print("\nIntroduce la posición del Juego a continuar: ");
        
        valor = sc_int.nextInt();
        return valor-1;
    }
    
    public static int MenuDificultad() {
        int valor;
        do {
            System.out.println("---- Eligen la Dificultad ----");
            System.out.println("1.- Muy Facil.");
            System.out.println("2.- Facil.");
            System.out.println("3.- Medio.");
            System.out.println("4.- Dificil.");
            System.out.println("5.- Muy Dificil.");
            System.out.print("\nElige Opción: ");
            valor = sc_int.nextInt();

            if (valor < 1 || valor > 5) {
                System.out.println("** Error, elija una dificultad correcta.");
            }
        } while (valor < 1 || valor > 5);

        return valor;
    }
    
    public static void Avisos(int cual){
        switch(cual){
            case 1: //Sudoku creado correctamente
                System.out.println("*** Nuevo Sudoku creado correctamente. ***\n");
                break;
            case 2: //Sudoku cargado correctamente
                System.out.println("*** Sudoku cargado correctamnete. ***\n");
                break;
            case 3: //Sudoku borrado correctamente
                System.out.println("*** Sudoku eliminado correctamente. ***\n");
                break;
            case 4: //Hueco ocupado
                System.out.println("*** La posicion seleccionada ya está ocupada. ***\n");
                break;
            case 5: //Hueco rellenado correctamente
                System.out.println("*** Valor ingresado correctamente. ***\n");
                break;
            case 6: //Valor no permitido 
                System.out.println("*** Ingresa un valor valido. ***\n");
                break;
            case 7: //Lista de Sudokus vacia 
                System.out.println("*** No hay ningún sudoku que mostrar. ***\n");
                break;
            case 8: //Valor borrado 
                System.out.println("*** Valor borrado con exito. ***\n");
                break;
            case 9: //Mostrar Ayuda
                System.out.println("*** Los posibles valores en la posicion F=" + fila_aux + " C=" + col_aux + " son: " + Ayuda + "\n");
                break;
            case 10: //Penalizacion ayuda
                System.out.println("*** Los posibles valores en la posicion F=" + fila_aux + " C=" + col_aux + " son: " + Ayuda);
                System.out.println("*** Como penalización se le ha borrado un número aleatorio del Sudoku\n");
                break;
        }
    }

    public static void main(String[] args) {
        try {
            int opcPrin = -1, opcDif = -1, opcGest = -1, opcJug = -1, codActual = 0, seleccion, puerto;
            int fil = -1, col = -1, valor = -1;
            TipoDificultad Dificultad = TipoDificultad.VACIO;
            
            System.out.print("Introduce el num. de puerto: "); //Usar el 5500
            puerto = sc_int.nextInt();
            
            Registry registro = LocateRegistry.getRegistry(puerto);
            GestorJuegosIntf srv = (GestorJuegosIntf)registro.lookup("GestorDeJuegos");
            
            do {                
                System.out.println("\nCódigo: " + codActual + "\t" + srv.GetSudoku(codActual));
                opcPrin = MenuPrincipal();
                switch (opcPrin){
                    case 1: //Al Menu de Gestión
                        do {                            
                            System.out.println("\n\nCódigo: " + codActual + "\t" + srv.GetSudoku(codActual));
                            Avisos(aviso);
                            opcGest = MenuGestion();
                            switch (opcGest) {
                                case 1: //Crear Juego
                                    opcDif = MenuDificultad();
                                    switch(opcDif){
                                        case 1:
                                            Dificultad = TipoDificultad.MUY_FACIL;
                                            break;
                                        case 2:
                                            Dificultad = TipoDificultad.FACIL;
                                            break;
                                        case 3:
                                            Dificultad = TipoDificultad.MEDIA;
                                            break;
                                        case 4:
                                            Dificultad = TipoDificultad.DIFICIL;
                                            break;
                                        case 5:
                                            Dificultad = TipoDificultad.MUY_DIFICIL;
                                            break;
                                    }
                                    codActual = srv.NuevoJuego(Dificultad);
                                    ListaSudoku ls = new ListaSudoku(codActual, Dificultad);
                                    Sudokus.add(ls);
                                    aviso = 1;
                                    break;

                                case 2: //Borrar Juego (llama a ListarJuegos)
                                    if(!Sudokus.isEmpty()){
                                        seleccion = ListarJuegos(true); //true = Borrar
                                        srv.BorrarJuego(Sudokus.get(seleccion).Codigo);
                                        Sudokus.remove(seleccion);
                                        aviso = 3;
                                    }
                                    else
                                        aviso = 7;
                                    break;

                                case 3: //Seleccionar Juego (llama a ListarJuegos)
                                    if(!Sudokus.isEmpty()){
                                        seleccion = ListarJuegos(false); //false = seleccionar
                                        codActual = Sudokus.get(seleccion).Codigo;
                                        aviso = 2;
                                    }
                                    else
                                        aviso = 7;
                                    break;

                                case 0: //Volver al menú principal
                                    aviso = 0;
                                    break;

                                default:
                                    System.out.println("\n** Error, indica una opcion válida **");
                            }
                        } while (opcGest != 0);
                        break; //------------------> FIN CASE 1
                        
                    case 2: //Al Menu de Juegos
                        do {                            
                            System.out.println("\n\nCódigo: " + codActual + "\t" + srv.GetSudoku(codActual));
                            if (srv.NumeroHuecos(codActual) == 0) {
                                System.out.print("*** SUDOKU COMPLETO ");
                                if (srv.Correcto(codActual) == false) {
                                    System.out.print("PERO IN");
                                }
                                System.out.println("CORRECTO ***\n");
                            }
                            Avisos(aviso);
                            opcJug = MenuJuego();
                            switch (opcJug) {
                                case 1: //Poner Valor
                                    System.out.print("Indica la fila: ");
                                    fil = sc_int.nextInt();
                                    System.out.print("Indica la columna: ");
                                    col = sc_int.nextInt();
                                    System.out.print("Indica el valor: ");
                                    valor = sc_int.nextInt();

                                    if (fil <= 0 || fil > 9 || col <= 0 || col > 9 || valor <= 0 || valor > 9)
                                        aviso = 6;
                                    else{
                                        if(srv.ComprobarValor(codActual, fil-1, col-1, Integer.toString(valor).charAt(0)))
                                            aviso = 4;
                                        else {
                                            srv.PonerValor(codActual, fil - 1, col - 1, Integer.toString(valor).charAt(0));
                                            aviso = 5;
                                        }
                                    }
                                    break;

                                case 2: //Borrar Valor
                                    System.out.print("Indica la fila: ");
                                    fil = sc_int.nextInt();
                                    System.out.print("Indica la columna: ");
                                    col = sc_int.nextInt();

                                    if (fil <= 0 || fil > 9 || col <= 0 || col > 9) 
                                        aviso = 6;
                                    else{
                                        srv.PonerValor(codActual, fil - 1, col - 1, ' ');
                                        aviso = 8;
                                    }
                                    break;

                                case 3: //Ayuda
                                    System.out.print("Indica la fila: ");
                                    fila_aux = sc_int.nextInt();
                                    System.out.print("Indica la columna: ");
                                    col_aux = sc_int.nextInt();
                                    Ayuda = srv.Ayuda(codActual, fila_aux-1, col_aux-1);
                                    if(srv.NumeroHuecos(codActual) != 81)
                                        aviso = 10;
                                    else
                                        aviso = 9;
                                    break;

                                case 0: //Volver al menú principal
                                    aviso = 0;
                                    break;
                            }
                        } while (opcJug != 0);
                        break; //------------------> FIN CASE 2
                        
                    case 0: //Salir de la aplicación
                        break;
                        
                        default:
                            System.out.println("\n** Error, indica una opcion válida **");
                }
            } while (opcPrin != 0);
            
        } catch (RemoteException ex) {
            System.out.println("\n** ERROR, excepcion en el Cliente_Sudoku: " + ex.getMessage());
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente_Sudoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//Fin del main

}
