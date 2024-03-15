
package plantilla_mvc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class InterfazTextual implements InterfazVista{

    private ControlConversor controlador;
    private int n_operacion;
    
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    private int leeOpcion(){
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (Exception e) {
            OperacionIncorrecta();
            return 0;
        }
    }
    
    private void OperacionIncorrecta(){
        System.out.println("Operacion Incorrecta");
        procesaNuevaOperacion();
    }
    
    private double leeCantidad(){
        String s = null;
        try {
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("Error en el formato, debe de ser 99.99 ");
            return leeCantidad();
        }
    }
    
    private void solicitaOperacion(){
        System.out.println("1.-Libras\n2.-Euros\n0.-Salir");
        System.out.print("Indique la operacion a realizar: ");
    }
    
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        
        switch(operacion){
            case 0:
                System.out.println("Adios");
                break;
                
            case 1:
                controlador.actionPerformed(new ActionEvent(this, n_operacion, aEUROS));
                break;
                
            case 2:
                controlador.actionPerformed(new ActionEvent(this, n_operacion, aLIBRAS));
                break;
                
            default:
                OperacionIncorrecta();
        }
    }
    
    @Override
    public void setControlador(ControlConversor c) {
        controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.print("Introduce cantidad a convertir: ");
        return leeCantidad();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        procesaNuevaOperacion();
    } 
}
