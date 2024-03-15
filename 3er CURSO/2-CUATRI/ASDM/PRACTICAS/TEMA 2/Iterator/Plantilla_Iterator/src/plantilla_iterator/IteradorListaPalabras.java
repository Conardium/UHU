
package plantilla_iterator;


public class IteradorListaPalabras implements I_Iterador{
    private String palabras[];
    private int posicion;

    public IteradorListaPalabras(String pal[]) {
        palabras = pal;
        posicion = 0;
    }

    @Override
    public Object Siguiente() {
        return palabras[posicion++];
    }

    @Override
    public Object Anterior() {
        if(posicion > 0)
            return palabras[--posicion];
        else
            return null;
    }

    @Override
    public boolean tieneSiguiente() {
        if(posicion < palabras.length)
            return true;
        else
            return false;
    }
    
    
}
