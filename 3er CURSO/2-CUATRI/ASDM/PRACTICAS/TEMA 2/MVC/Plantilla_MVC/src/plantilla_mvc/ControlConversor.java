
package plantilla_mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlConversor implements ActionListener{
    private InterfazVista vista;
    private ConversorEurosLibras modelo;

    public ControlConversor(InterfazVista v, ConversorEurosLibras m) {
        vista = v;
        modelo = m;
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        double cantidad = vista.getCantidad();
        
        if(evento.getActionCommand().equals(InterfazVista.aEUROS))
            vista.escribeCambio(cantidad + " Libras a Euros son: " + modelo.librasAeuros(cantidad));
        else if(evento.getActionCommand().equals(InterfazVista.aLIBRAS))
            vista.escribeCambio(cantidad + " Euros a Libras son: " + modelo.eurosAlibras(cantidad));
        else
            vista.escribeCambio("Algo ha ido mal");
    }
}
