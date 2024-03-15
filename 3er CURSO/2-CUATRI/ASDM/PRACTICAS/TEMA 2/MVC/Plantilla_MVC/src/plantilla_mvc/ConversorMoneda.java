
package plantilla_mvc;


public class ConversorMoneda {
    private double cambio;

    public ConversorMoneda(double valorCambio) {
        cambio = valorCambio;
    }
    
    public double eurosAmoneda(double cantidad){
        return cantidad/cambio;
    }
    
    public double monedaAeuros(double cantidad){
        return cantidad*cambio;
    }
}
