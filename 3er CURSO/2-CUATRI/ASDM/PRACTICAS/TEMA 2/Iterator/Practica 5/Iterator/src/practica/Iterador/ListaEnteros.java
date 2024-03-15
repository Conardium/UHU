package practica.Iterador;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class ListaEnteros implements I_Lista{

    private Object valores[];
    private int posicion;

    public ListaEnteros(int tamaño) {
        valores = new Object[tamaño];
        posicion = 0;
    }
           
    @Override
    public void Agregar(Object v) {
        valores[posicion++] = (int)v;
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
