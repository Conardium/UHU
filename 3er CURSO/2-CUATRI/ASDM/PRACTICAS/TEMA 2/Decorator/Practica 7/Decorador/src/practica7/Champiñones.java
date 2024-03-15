
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Champiñones extends Decorador{
    private String ingrediente = "extra Champiñones";
    private double precio = 1.5;
    
    public Champiñones(I_Pizza p){
        super(p);
    }
    
    public String getIngredienteChamp(){
        return ingrediente;
    }
    
    public double getPrecioChamp(){
        return precio;
    }
    
    @Override
    public String getIngredientes() {
        return super.getIngredientes() + ", " + this.getIngredienteChamp();
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + this.getPrecioChamp();
    }
}
