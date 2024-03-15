package plantilla_iterator;

public class Iterador_Concreto_2 implements I_Iterador{

    private int numeros[];
    private int posicion;

    public Iterador_Concreto_2(int num[]) {
        this.numeros = num;
        posicion = num.length-1;
    }
    
    @Override
    public Object Siguiente() {
        return numeros[posicion--];
    }

    @Override
    public Object Anterior() {
        if(posicion < numeros.length-1)
            return numeros[++posicion];  
        else
            return null;
    }

    @Override
    public boolean tieneSiguiente() {
        if(posicion >= 0)
            return true;
        else
            return false;
    }
    
}
