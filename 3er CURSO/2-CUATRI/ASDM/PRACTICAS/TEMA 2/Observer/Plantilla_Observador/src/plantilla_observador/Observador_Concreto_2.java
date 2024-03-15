package plantilla_observador;


public class Observador_Concreto_2 implements Observador{

    @Override
    public void actualizar(String accion, String lugar) {
        System.out.println("Soy el observador 2 realizando la accion " + accion + " en el lugar " + lugar);
    }
    
}
