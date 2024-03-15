package practica.Iterador;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class ListaLetras implements I_Lista{
    
    private Object valores[];
    private int posicion;

    public ListaLetras(int tamaño) {
        valores = new Object[tamaño];
        posicion = 0;
    }
           
    @Override
    public void Agregar(Object v) {
        valores[posicion++] = (char)v;
    }

    @Override
    public I_Iterador crearIteradorCola() {
        return new IteradorCola(valores);
    }

    @Override
    public I_Iterador crearIteradorPila() {
        return new IteradorPila(valores);
    }
}
