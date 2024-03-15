
package practica.Iterador;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class IteradorPila implements I_Iterador{

    private Object[] valores;
    private int posicion_inicio;

    public IteradorPila(Object[] array) {
        valores = array;
        posicion_inicio = array.length-1;
    }
        
    @Override
    public Object Siguiente() {
        return valores[posicion_inicio--];
    }

    @Override
    public Object Anterior() {
        if(posicion_inicio < valores.length-1)
            return valores[++posicion_inicio];  
        else
            return null;
    }

    @Override
    public Object mostrarSiguiente() {
        return valores[posicion_inicio];
    }

    @Override
    public Object mostrarAnterior() {
        if(posicion_inicio < valores.length-1)
            return valores[posicion_inicio + 1];  
        else
            return null;
    }

    @Override
    public boolean haySiguiente() {
        if(posicion_inicio >= 0)
            return true;
        else
            return false;
    }
    
}
