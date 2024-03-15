
package practica7;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Fabricador_Pizza extends Creador{

    private int tipo; //1.- Peperoni, 2.- 4Quesos, 3.- Barbacoa, 4.- Marinera
    
    public Fabricador_Pizza(int t) {
        tipo = t;
    }
    
    @Override
    public I_Pizza Factory_method(){
        I_Pizza pizza = null;
        switch(tipo){
            case 1:
                System.out.println("\n-- Preparamos una Pizza Peperonni --");
                pizza = new Pizza_Peperoni();
                break;
            case 2:
                System.out.println("\n-- Preparamos una Pizza 4 Quesos --");
                pizza = new Pizza_4Quesos();
                break;
            case 3:
                System.out.println("\n-- Preparamos una Pizza Barbacoa --");
                pizza = new Pizza_Barbacoa();
                break;
            case 4:
                System.out.println("\n-- Preparamos una Pizza Marinera --");
                pizza = new Pizza_Marinera();
                break;
        }
        return pizza;
    }
}
