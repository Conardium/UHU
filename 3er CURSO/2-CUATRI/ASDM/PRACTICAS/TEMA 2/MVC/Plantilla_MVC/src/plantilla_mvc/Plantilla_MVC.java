
package plantilla_mvc;


public class Plantilla_MVC {

    public static void main(String[] args) {
        ConversorEurosLibras modelo = new ConversorEurosLibras();
        InterfazVista vista = new InterfazTextual();
        ControlConversor controlador = new ControlConversor(vista, modelo);
        vista.setControlador(controlador);
        vista.arranca();
    }
    
}
