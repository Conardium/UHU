
package plantilla_decorador_v2;


public class Plantilla_Decorador_v2 {

    
    public static void main(String[] args) {
        Componente_Concreto basico = new Componente_Concreto();
        Decorador_Concreto_A añadido1 = new Decorador_Concreto_A(basico);
        Decorador_Concreto_B añadido2 = new Decorador_Concreto_B(añadido1);
        
        //Informacion que tiene guardada añadido1
        System.out.println(añadido1.getDescripcion());
        System.out.println("El precio es: " + añadido1.getPrecio());
        
        //Informacion que tiene guardada basico
        System.out.println("\n" + basico.getDescripcion());
        System.out.println("El precio es: " + basico.getPrecio());
        
        //Informacion que tiene guardada añadido2
        System.out.println("\n" + añadido2.getDescripcion());
        System.out.println("El precio es: " + añadido2.getPrecio());
    }
    
}
