
package plantilla_decorador_v2;


public class Componente_Concreto implements I_Componente{

    private String descripicion_basica = "Caracteristica Basica";
    private float precio = 100;
    
    @Override
    public String getDescripcion() {
        return descripicion_basica;
    }

    @Override
    public float getPrecio() {
        return precio;
    }
    
}
