
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Cebolla extends Decorador{
    private String ingrediente = "extra Cebolla";
    private double precio = 0.5;
    
    public Cebolla(I_Pizza p){
        super(p);
    }
    
    public String getIngredienteCebolla(){
        return ingrediente;
    }
    
    public double getPrecioCebolla(){
        return precio;
    }
    
    @Override
    public String getIngredientes() {
        return super.getIngredientes() + ", " + this.getIngredienteCebolla();
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + this.getPrecioCebolla();
    }
}
