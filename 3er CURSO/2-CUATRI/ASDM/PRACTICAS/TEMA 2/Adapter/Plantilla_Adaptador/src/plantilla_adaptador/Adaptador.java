package plantilla_adaptador;

public class Adaptador extends Adaptado implements iTarget{

    public Adaptador(String n){
        super(n);
    }
    
    @Override
    public void mostrarNombre(int f) {
        if(f == 1)
            mostrarNombre("MAYUS");
        else
            mostrarNombre("minus");
    }
    
}
