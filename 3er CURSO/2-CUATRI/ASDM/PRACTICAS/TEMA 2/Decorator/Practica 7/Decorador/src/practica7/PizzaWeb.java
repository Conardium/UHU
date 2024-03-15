
package practica7;

import java.util.Scanner;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class PizzaWeb {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Creador miFabrica;
        I_Pizza p;
        Champiñones champiñones;
        Cebolla cebolla;
        Pimiento pimiento;
        Pizzeria pizzeria = Pizzeria.getPizzeria();
        
        int opcion = 0, opPizza = 0;
        char opExtra = 'n';
        
        do {            
            opcion = menuPrincipal();
            switch(opcion){
                case 1: //Pedir una Pizza
                    opPizza = menuTipoPizza();
                    miFabrica = new Fabricador_Pizza(opPizza);
                    p = miFabrica.Factory_method();
                    
                    I_Pizza ultimoUsado = p;
                    
                    System.out.print("¿Quiere añadirle Champiñones a la pizza? (s/n): ");
                    opExtra = sc.nextLine().charAt(0);
                    if(opExtra == 's' || opExtra == 'S'){
                        champiñones = new Champiñones(ultimoUsado);
                        ultimoUsado = champiñones;
                    }
                    
                    System.out.print("¿Quiere añadirle Cebolla a la pizza? (s/n): ");
                    opExtra = sc.nextLine().charAt(0);
                    if(opExtra == 's' || opExtra == 'S'){
                        cebolla = new Cebolla(ultimoUsado);
                        ultimoUsado = cebolla;
                    }
                    
                    System.out.print("¿Quiere añadirle Pimiento a la pizza? (s/n): ");
                    opExtra = sc.nextLine().charAt(0);
                    if(opExtra == 's' || opExtra == 'S'){
                        pimiento = new Pimiento(ultimoUsado);
                        ultimoUsado = pimiento;
                    }
                    
                    pizzeria.nuevaPizza(ultimoUsado);
                    break;
                    
                case 2: //Listar Pizzas
                    if(pizzeria.getPizzas().isEmpty())
                        System.out.println("\n** Todavía no hay niguna pizza que mostrar\n");
                    else
                    {
                        System.out.println("\n<---- Listado de las Pizzas ---->");
                        for (int i = 0; i < pizzeria.getPizzas().size(); i++) {
                            System.out.println(i + ") Precio: " + pizzeria.getPizzas().get(i).getPrecio());
                            System.out.println(pizzeria.getPizzas().get(i).getIngredientes());
                            System.out.print("\n");
                        }
                    }
                    break;
                    
                case 3: //Salir
                    break;
                default:
                    System.out.println("\n**Error, indica una opcion valida.\n");
            }
        } while (opcion != 3);
    }
    
    public static int menuPrincipal(){
        Scanner sc = new Scanner(System.in);
        int valor;
        System.out.println("\n==== PIZZERÍA POMODORO ====");
        System.out.println("1.- Pedir una Pizza");
        System.out.println("2.- Listado de Pizzas");
        System.out.println("3.- Salir");
        System.out.println("-------------");
        System.out.print("Elige una opcion: ");
        valor = sc.nextInt();
        
        return valor;
    }
    
    public static int menuTipoPizza(){
        Scanner sc = new Scanner(System.in);
        int valor;
        System.out.println("\n### ¿Que tipo de pizza desea? ###");
        System.out.println("1.- Pizza Peperonni");
        System.out.println("2.- Pizza 4 Quesos");
        System.out.println("3.- Pizza Barbacoa");
        System.out.println("4.- Pizza Marinera");
        System.out.println("-------------");
        System.out.print("Elige una opcion: ");
        valor = sc.nextInt();
        
        return valor;
    }
}
