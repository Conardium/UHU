
package practica.Iterador;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Creador_Concreto extends Creador{
    
    private int tipo;

    public Creador_Concreto(int t) {
        tipo = t;
    }
    
    @Override
    public I_Lista Factory_method(){
        System.out.print("\nSe crea una nueva Lista ");
        if(tipo == 1){
            System.out.println("Numerica\n");
            return new ListaEnteros(10);
        }
        else{
            System.out.println("Alfabetica\n");
            return new ListaLetras(10);
        }
    }
}
