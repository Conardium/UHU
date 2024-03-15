package practica.Iterador;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class IteradorCola implements I_Iterador{

    private Object valores[];
    private int posicion_inicio;

    public IteradorCola(Object[] array) {
        valores = array;
        posicion_inicio = 0;
    }
    
    @Override
    public Object Siguiente() {
        return valores[posicion_inicio++];
    }
    
    @Override
    public Object mostrarSiguiente(){
        return valores[posicion_inicio];
    }

    @Override
    public Object Anterior() {
        if(posicion_inicio == 0)
            return null;
        else
            return valores[--posicion_inicio];
    }
    
    @Override
    public Object mostrarAnterior(){
        if(posicion_inicio == 0)
            return null;
        else
            return valores[posicion_inicio-1];
    }

    @Override
    public boolean haySiguiente() {
        if(posicion_inicio < valores.length)
            return true;
        else
            return false;
    }
    
}
