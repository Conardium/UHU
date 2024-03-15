package practica.Iterador;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public interface I_Lista {
    public void Agregar(Object v);
    public I_Iterador crearIteradorCola();
    public I_Iterador crearIteradorPila();
}
