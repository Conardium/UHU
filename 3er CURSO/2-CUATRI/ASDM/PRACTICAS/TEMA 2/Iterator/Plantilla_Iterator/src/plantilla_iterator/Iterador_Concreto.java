package plantilla_iterator;

public class Iterador_Concreto implements I_Iterador{
    private int numeros[];
    private int posicion;

    public Iterador_Concreto(int num[]) {
        this.numeros = num;
        posicion = 0;
    }

    @Override
    public Object Siguiente() {
        return numeros[posicion++];
    }

    @Override
    public Object Anterior() {
        if(posicion > 0)
            return numeros[--posicion];  
        else
            return null;
    }

    @Override
    public boolean tieneSiguiente() {
        if(posicion < numeros.length && numeros[posicion] != -1)
            return true;
        else
            return false;
    }
}
