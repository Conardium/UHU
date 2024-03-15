package plantilla_adaptador_2;

public class Adaptador implements Interfaz_Adaptador{
    Adaptado ad = new Adaptado();
    
    @Override
    public void metodo_Adaptado() {
        ad.metodo_A_adaptar();
    }
}
