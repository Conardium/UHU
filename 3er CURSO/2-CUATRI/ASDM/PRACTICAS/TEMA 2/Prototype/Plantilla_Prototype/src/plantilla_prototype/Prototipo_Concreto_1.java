package plantilla_prototype;

/**
 *
 * @author ismae
 */
public class Prototipo_Concreto_1 implements Prototipo{

    private String nombre;

    public Prototipo_Concreto_1() {
        nombre = "Prototipo concreto 1";
    }
    
    
    
    @Override
    public String getNombre() {
         return nombre;
    }

    @Override
    public void setNombre(String n) {
         nombre = n;
    }

    @Override
    public Prototipo clonar() {
        Prototipo p = new Prototipo_Concreto_1(); //Creamos el clon
        p.setNombre(nombre); //Copiamos los atributos del original al clon
        return p; //Devolvemos el clon
    }
    
}
