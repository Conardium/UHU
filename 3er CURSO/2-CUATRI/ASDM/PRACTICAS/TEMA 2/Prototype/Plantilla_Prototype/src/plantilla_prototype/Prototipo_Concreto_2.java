package plantilla_prototype;

/**
 *
 * @author ismae
 */
public class Prototipo_Concreto_2 implements Prototipo{
    
    private String nombre;

    public Prototipo_Concreto_2() {
        nombre = "Prototipo concreto 2";
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
        Prototipo p = new Prototipo_Concreto_2(); //Creamos el clon
        p.setNombre(nombre); //Copiamos los atributos del original al clon
        return p; //Devolvemos el clon
    }
}
