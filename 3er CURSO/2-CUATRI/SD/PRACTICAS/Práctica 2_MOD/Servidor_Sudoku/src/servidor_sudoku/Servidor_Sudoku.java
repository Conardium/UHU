package servidor_sudoku;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria_sudoku.GestorJuegosIntf;

/**
 *
 * @author Ismael Da Palma Fernandez
 */
public class Servidor_Sudoku {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int puerto = 5500;
            //System.out.print("Introduce el num. de puerto: "); //Usar entre 5000 y 6000
            //puerto = sc.nextInt();

            Servicios services = new Servicios();
            GestorJuegosIntf stub = (GestorJuegosIntf) UnicastRemoteObject.exportObject(services, puerto);

            Registry registro = LocateRegistry.createRegistry(puerto);
            registro = LocateRegistry.getRegistry(puerto);
            registro.bind("GestorDeJuegos", stub);

            System.out.println("Servidor de Sudokus Funcionando en el Puerto 5500....");
            System.out.println("pulsa una tecla para finalizar");
            sc.nextInt();

            System.out.println("Saliendo del servidor ...");
            registro.unbind("GestorDeJuegos");
            System.exit(0);

        } catch (RemoteException | AlreadyBoundException | NotBoundException ex) {
            System.out.println("** ERROR, excepcion en el Servidor_Sudoku: " + ex.getMessage());
            System.exit(1);
        }
    }

}
