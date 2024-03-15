
package plantilla_factory;

public class Creador_concreto extends Creador {
    
    private String tipo;

    public Creador_concreto(String t) {
        tipo = t;
    }
    
    @Override
    public Producto Factory_method(){
        System.out.println("Se crea un producto");
        if(tipo.equalsIgnoreCase("1"))
            return new Producto_Concreto_1();
        else
            return new Producto_Concreto_2();
    }
}
