
package plantilla_decorador_v2;


public abstract class Decorador implements I_Componente{
    protected  I_Componente componente;
    
    public Decorador(I_Componente c){
        componente = c;
    }
    
    @Override
    public String getDescripcion(){
        return componente.getDescripcion();
    }
    
    @Override
    public float getPrecio(){
        return componente.getPrecio();
    }
}
