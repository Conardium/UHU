
package plantilla_decorador_v2;


public class Decorador_Concreto_B extends Decorador{
    private String descripcion_decorador_B = "\nDescripcion Decorador B";
    private float precio = 300;

    public Decorador_Concreto_B(I_Componente c) {
        super(c);
    }
    
    public String getDescripcionDecoradorB(){
        return descripcion_decorador_B;
    }
    
    public float getPrecioDecoradorB(){
        return precio;
    }
    
    @Override
    public String getDescripcion(){
        return super.getDescripcion() + this.getDescripcionDecoradorB();
    }
    
    @Override
    public float getPrecio(){
        float suma;
        suma = super.getPrecio() + this.getPrecioDecoradorB();
        return suma;
    }
}
