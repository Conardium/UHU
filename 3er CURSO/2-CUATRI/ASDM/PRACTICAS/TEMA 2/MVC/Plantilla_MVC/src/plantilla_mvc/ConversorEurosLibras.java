
package plantilla_mvc;


public class ConversorEurosLibras extends ConversorMoneda{

    public ConversorEurosLibras() {
        super(1.18);
    }
    
    public double eurosAlibras(double cantidad){
        return eurosAmoneda(cantidad);
    }
    
    public double librasAeuros(double cantidad){
        return monedaAeuros(cantidad);
    }
    
}
