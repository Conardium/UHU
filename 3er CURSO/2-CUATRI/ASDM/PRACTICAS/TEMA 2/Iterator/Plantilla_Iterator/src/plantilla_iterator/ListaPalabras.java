package plantilla_iterator;


public class ListaPalabras implements I_ListaPalabras{

    private String palabras[];
    int posicion;

    public ListaPalabras() {
        palabras = new String[10];
        posicion = 0;
    }
    
    @Override
    public void Agregar(String n) {
        palabras[posicion++] = n;
    }

    @Override
    public IteradorListaPalabras CrearIterador() {
        return new IteradorListaPalabras(palabras);
    }
    
}
