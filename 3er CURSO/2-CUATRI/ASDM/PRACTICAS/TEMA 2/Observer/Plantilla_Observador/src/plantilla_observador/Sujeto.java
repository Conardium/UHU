package plantilla_observador;

import java.util.ArrayList;


public class Sujeto implements I_Sujeto{

    private ArrayList<Observador> observadores;
    private String accion;
    private String lugar;

    public Sujeto(String a, String l) {
        accion = a;
        lugar = l;
        observadores = new ArrayList();
    }
    
    @Override
    public void registrarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void retirarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void Notficar() {
        for(Observador o : observadores)
            o.actualizar(this.accion, this.lugar);
    }

    @Override
    public void ejecutarAccion(String a, String l) {
        this.accion = a;
        this.lugar = l;
        Notficar();
    }
    
}
