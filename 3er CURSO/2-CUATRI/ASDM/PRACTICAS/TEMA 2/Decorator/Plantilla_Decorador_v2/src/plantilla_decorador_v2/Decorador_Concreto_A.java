
package plantilla_decorador_v2;


public class Decorador_Concreto_A extends Decorador{
    private String descripcion_decorador_A = "\nDescripcion Decorador A";
    private float precio = 200;

    public Decorador_Concreto_A(I_Componente c) {
        super(c);
    }
    
    public String getDescripcionDecoradorA(){
        return descripcion_decorador_A;
    }
    
    public float getPrecioDecoradorA(){
        return precio;
    }
    
    @Override
    public String getDescripcion(){
        return super.getDescripcion() + this.getDescripcionDecoradorA();
    }
    
    @Override
    public float getPrecio(){
        float suma;
        suma = super.getPrecio() + this.getPrecioDecoradorA();
        return suma;
    }
}
