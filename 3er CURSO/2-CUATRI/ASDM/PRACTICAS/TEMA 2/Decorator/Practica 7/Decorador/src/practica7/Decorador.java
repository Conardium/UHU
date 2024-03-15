
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public abstract class Decorador implements I_Pizza{
    private I_Pizza pizza;

    public Decorador(I_Pizza p) {
        pizza = p;
    }
    
    @Override
    public String getIngredientes() {
        return pizza.getIngredientes();
    }

    @Override
    public double getPrecio() {
        return pizza.getPrecio();
    }
}
