
package airport_huelva;

import java.util.ArrayList;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public final class Aeropuerto {
    
    private static final Aeropuerto Aero = new Aeropuerto(); //Lo que vamos a devolver (instancia)
    private static ArrayList<Vuelo> Vuelos; //Valor que opera el singleton
    
    private Aeropuerto(){
        Vuelos = new ArrayList();
        
        //Vuelo de ejemplo para probar la funcionalidad del Singleton
        Vuelo v = new Vuelo("Vueling", "Amsterdan", "MK1234", 158, "En hora", 11, 14);
        Vuelos.add(v);
    }
    
    public static Aeropuerto getAeropuerto(){ //Devolvemos la instancia que creamos
        return Aero;
    }
    
    
    //---- Sobre los datos del singleton realizamos los metodos que necesitemos ----
    public ArrayList<Vuelo> getVuelos(){
        return Vuelos;
    }
    
    //Metodo auxiliar que dado un Numvuelo devuelve la posicion de este en el array de Vuelos, -1 si no lo encuentra
    public int existeVuelo(String NumVuelo){
        
        int i = 0;
        int pos = -1;
        while (i < Vuelos.size() && pos == -1) {
            if(Vuelos.get(i).getNumVuelo().equals(NumVuelo))
                pos = i;
            else
                i++;
        }
        
        return pos;
    }
    
    public void nuevoVuelo(Vuelo v){
        Vuelos.add(v);
        System.out.println("Vuelo añadido correctamente, echa un vistazo al panel");
    }
    
    public void modificarVuelo(Vuelo v, int pos){
        Vuelos.set(pos, v);
        System.out.println("Vuelo modificado correctamente, echa un vistazo al panel");
    }
    
    public void eliminarVuelo(String NumVuelo){
        
        int pos = existeVuelo(NumVuelo);
        
        if(pos == -1)
            System.out.println("\n!! ERROR: No se ha encontrado ningún vuelo con numero: " + NumVuelo + "!!\n");
        else{
            Vuelos.remove(pos);
            System.out.println("Vuelo eliminado correctamente");
        }
    }
}
