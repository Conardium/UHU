
package practica7;

import java.util.ArrayList;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pizzeria {
    private static final Pizzeria pizeria = new Pizzeria();
    private static ArrayList<I_Pizza> pizzas = new ArrayList();
    
    public static Pizzeria getPizzeria(){
        return pizeria;
    }
    
    public ArrayList<I_Pizza> getPizzas(){
        return pizzas;
    }
    
    public void nuevaPizza(I_Pizza p){
        pizzas.add(p);
        System.out.println("\nSe ha agregado una nueva pizza a los pedidos, ECHE UN VISTAZO AL LISTADO");
    }
}
