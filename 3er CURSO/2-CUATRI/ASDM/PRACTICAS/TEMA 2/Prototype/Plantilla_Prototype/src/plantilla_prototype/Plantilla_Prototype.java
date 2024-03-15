package plantilla_prototype;

/**
 *
 * @author ismae
 */
public class Plantilla_Prototype {

    public static void main(String[] args) {
        
        Prototipo p1 = new Prototipo_Concreto_1();
        Prototipo p2 = new Prototipo_Concreto_2();
        Prototipo clon;
        
        clon = p2.clonar();
        
        System.out.println("Nombre p1: " + p1.getNombre());
        System.out.println("Nombre p2: " + p2.getNombre());
        System.out.println("Nombre clon: " + clon.getNombre());
        
        clon.setNombre("Soy un clon");
        System.out.println("------------------");
        System.out.println("Nombre clon: " + clon.getNombre());
    }
    
}
