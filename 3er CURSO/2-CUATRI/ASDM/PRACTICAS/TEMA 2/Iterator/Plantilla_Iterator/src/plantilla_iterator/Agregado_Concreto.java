package plantilla_iterator;


public class Agregado_Concreto implements I_Agregado{

    private int numeros[];
    private int posicion;

    public Agregado_Concreto() {
        numeros = new int[10];
        posicion = 0;
    }

    @Override
    public void Agregar(int a) {
        numeros[posicion++] = a;
    }

    @Override
    public I_Iterador crearIterador1() {
        return new Iterador_Concreto(numeros);
    }
    
    @Override
    public I_Iterador crearIterador2() {
        return new Iterador_Concreto_2(numeros);
    }
    
}
