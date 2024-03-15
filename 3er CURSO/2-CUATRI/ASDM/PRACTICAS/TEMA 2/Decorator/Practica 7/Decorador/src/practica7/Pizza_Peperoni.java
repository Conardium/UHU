
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pizza_Peperoni implements I_Pizza{

    private String ingredientes = "Pizza Peperonni con Tomate, Mozzarella, Orégano y Peperonni italiano";
    private double precio = 8;
    
    @Override
    public String getIngredientes() {
        return ingredientes;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
    
}
