
package plantilla_decorator;


public class Plantilla_Decorator {


    public static void main(String[] args) {
        Componente_Concreto basico = new Componente_Concreto(); //Contiene la información basica
        Decorador_Concreto_1 añadido1 = new Decorador_Concreto_1(basico); //Contiene la informacion basica y la añadido1
        Decorador_Concreto_2 añadido2 = new Decorador_Concreto_2(añadido1); //Contiene la informacion basica, la añadido1 y añadido2
        
        añadido2.visualiza();
    }
    
}
