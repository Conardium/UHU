package plantilla_observador;


public interface I_Sujeto {
    //Metodos que manejan los observadores
    public void registrarObservador(Observador o);
    public void retirarObservador(Observador o);
    public void Notficar();
    
    //Metodo que cambia el estado del sujeto/entidad
    public void ejecutarAccion(String a, String l);
}
