
package plantilla_mvc;

/**
 *
 * @author ismae
 */
public interface InterfazVista {
    static final String aEUROS = "Libras a Euros ";
    static final String aLIBRAS = "Euros a Libras ";
    
    void setControlador(ControlConversor c);
    void arranca();
    double getCantidad();
    void escribeCambio(String s);
}
