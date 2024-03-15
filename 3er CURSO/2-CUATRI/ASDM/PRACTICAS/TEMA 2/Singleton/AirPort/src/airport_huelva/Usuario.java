
package airport_huelva;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Usuario {
    
    private Aeropuerto Aero; //Instancia Singleton
    
    int tipo = 0;  //0=Pasajero
                   //1 o más = Director, Director General, ...
    
    Usuario(int tipo){
        Aero = Aeropuerto.getAeropuerto();
        this.tipo = tipo;
    }
    
    public void visualizarInterfaz(int tipoUser){
        Interfaz_Ususario iu = new Interfaz_Ususario(tipoUser);
    }
}
