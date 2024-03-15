
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pizza_Marinera implements I_Pizza{
    private String ingredientes = "Pizza Marinera con Tomate, Mozzarella, Orégano, Atún, Cebolla y Aceitunas negras";
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
