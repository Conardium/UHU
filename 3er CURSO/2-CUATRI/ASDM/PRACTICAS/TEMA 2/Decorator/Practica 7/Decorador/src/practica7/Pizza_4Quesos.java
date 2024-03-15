
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pizza_4Quesos implements I_Pizza{
    private String ingredientes = "Pizza 4 Quesos con Tomate, Mozzarella, Orégano, Queso, Gorgonzola";
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
