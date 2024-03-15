
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pizza_Barbacoa implements I_Pizza{
    private String ingredientes = "Pizza Barbacoa con Tomate, Mozzarella, Orégano, Ternera, Bacon y salsa BBQ";
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
