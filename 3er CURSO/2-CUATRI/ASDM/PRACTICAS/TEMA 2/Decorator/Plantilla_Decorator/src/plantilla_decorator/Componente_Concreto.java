
package plantilla_decorator;


public class Componente_Concreto implements I_Componente_Abstracto{

    @Override
    public void visualiza() {
        System.out.println("Visualización basica, sin decoracion");
    }
    
}
