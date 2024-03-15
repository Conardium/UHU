
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pimiento extends Decorador{
    private String ingrediente = "extra Pimiento";
    private double precio = 2.5;
    
    public Pimiento(I_Pizza p){
        super(p);
    }
    
    public String getIngredientePimiento(){
        return ingrediente;
    }
    
    public double getPrecioPimiento(){
        return precio;
    }
    
    @Override
    public String getIngredientes() {
        return super.getIngredientes() + ", " + this.getIngredientePimiento();
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + this.getPrecioPimiento();
    }
}
