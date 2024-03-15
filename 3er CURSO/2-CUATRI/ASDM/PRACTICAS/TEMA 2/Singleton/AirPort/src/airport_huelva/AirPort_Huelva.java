
package airport_huelva;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class AirPort_Huelva {

    public static void main(String[] args) {
        
        Usuario u1 = new Usuario(0); //Pasajero
        Usuario u2 = new Usuario(1); //Director
        
        u2.visualizarInterfaz(1); //Primero lo abre un Administrador
        
        u1.visualizarInterfaz(0); //Luego un Pasajero
        
    }
    
}
