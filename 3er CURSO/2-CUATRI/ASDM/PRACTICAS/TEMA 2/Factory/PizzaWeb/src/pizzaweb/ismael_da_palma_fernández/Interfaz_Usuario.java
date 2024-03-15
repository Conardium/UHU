package pizzaweb.ismael_da_palma_fernández;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Interfaz_Usuario {

    private Creador miFabrica;
    
    public Interfaz_Usuario() {
    }
    
    
    //Muestra el menu principal de la Pizzeria
    public void Menu_Principal(){
        System.out.println("\n\n");
        System.out.println("====== Bienvenido a Pizzerias Don Alejandro ======");
        System.out.println("          --- ¿Que desea hacer? ---");
        System.out.println("          1) Realizar un Pedido");
        System.out.println("          2) Mirar la carta");
        System.out.println("          3) Salir");
        System.out.println("          -------------------------");
        System.out.print("          Elige una opción: ");
    }
    
    
    //Lista los diferentes tipos de pizza de las que dispone el restaurante
    public void Listar_Carta(){ 
        System.out.println("\n\n");
        System.out.println("          ======= CARTA =======");
        System.out.println("      --- ¿Que pizza quiere seleccionar? ---");
        System.out.println("          1) Peperoni");
        System.out.println("          2) 4 Quesos");
        System.out.println("          3) Barbacoa");
        System.out.println("          4) Marinera");
        System.out.println("          5) Volver");
        System.out.println("          -------------------------");
        System.out.print("          Elige una opción: ");
    }
    
    
    /*Según la opcion elegida en el metodo Listar_Carta() se mostrará
    la información de la pizza creando una instancia de esta con Factory_method().*/
    public void Sub_Menu_Carta(int opcion){ 
        Pizza p;

        switch(opcion){
            case 1: //Peperoni
                miFabrica = new Creador_concreto(1);      
                p = miFabrica.Factory_method();
                
                p.Tipo_Pizza();
                System.out.println("*Ingredientes Base: " + p.getIngredientesBase());
                System.out.println("*Ingredientes Propios: " + p.getIngredientesPropios());
                break;
            case 2: //4 Quesos
                miFabrica = new Creador_concreto(2);      
                p = miFabrica.Factory_method();
                
                p.Tipo_Pizza();
                System.out.println("*Ingredientes Base: " + p.getIngredientesBase());
                System.out.println("*Ingredientes Propios: " + p.getIngredientesPropios());
                break;
            case 3: //Barbacoa
                miFabrica = new Creador_concreto(3);      
                p = miFabrica.Factory_method();
                
                p.Tipo_Pizza();
                System.out.println("*Ingredientes Base: " + p.getIngredientesBase());
                System.out.println("*Ingredientes Propios: " + p.getIngredientesPropios());
                break;
            case 4: //Marinera
                miFabrica = new Creador_concreto(4);      
                p = miFabrica.Factory_method();
                
                p.Tipo_Pizza();
                System.out.println("*Ingredientes Base: " + p.getIngredientesBase());
                System.out.println("*Ingredientes Propios: " + p.getIngredientesPropios());
                break;
            case 5: //Volver
                break;
            default:
                System.out.println("Elija una opción válida del menú\n");
                break;
        }
        System.out.println("\n");
    }
    
    
    /*Una vez seleccionada la pizza con Listar_Carta se le pregunta al usuario
    /si quiere añadir ingredientes extras, en caso afirmativo llamará al método
    Listar_Ingredientes() que recogerá los ingredientes extra seleccionados y los
    añadira a la pizza seleccionada.*/
    public void Realizar_Pedido(int tipo_pizza){ 
        Pizza p;
        ArrayList<String> ing_extras = new ArrayList();
        
        miFabrica = new Creador_concreto(tipo_pizza);      
        p = miFabrica.Factory_method();
        
        Scanner sc = new Scanner(System.in);
        char sn;
        System.out.print("¿Quiere agregar ingredientes extras? (s/n): ");
        sn = sc.nextLine().charAt(0);
        if(sn == 's' || sn == 'S'){
            for (int i = 0; i < 3; i++) {
                ing_extras.add(Listar_Ingredientes());
            }
            p.añadir_Ingredientes_extras(ing_extras.get(0), ing_extras.get(1), ing_extras.get(2));
        }
        
        System.out.print("\n######## Resultado del Pedido ########");
        p.Tipo_Pizza();
        System.out.println("*Ingredientes Base: " + p.getIngredientesBase());
        System.out.println("*Ingredientes Propios: " + p.getIngredientesPropios());
        System.out.print("*Ingredientes Extras: ");
        p.mostrarIngredientesExtras();
        System.out.println("\n");
    }
    
    
    /*Se le muestra al usuario un menu con los diferentes ingredientes a elegir(pudiendo elegir el mismo varias veces)
    una vez se seleccione el ingrediente se retornará este. En caso de no elegir ningun ingrediente dentro de la lista, 
    el metodo devolverá una cadena vacia*/
    public String Listar_Ingredientes(){
        String ing_extra;
        int opcion;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n\n");
        System.out.println("          ======= LISTA DE INGREDIENTES =======");
        System.out.println("      --- ¿Que ingredientes quiere agregar? ---");
        System.out.println("          1) Jamon York");
        System.out.println("          2) Champiñones");
        System.out.println("          3) Cebolla");
        System.out.println("          4) Peperoni");
        System.out.println("          5) Cheddar");
        System.out.println("          6) Salsa Carbonara");
        System.out.println("          7) Salsa BBQ");
        System.out.println("          8) Bacon");
        System.out.println("          -------------------------");
        System.out.print("          Elige una opción: ");
        opcion = sc.nextInt();
        
        switch (opcion) {
            case 1:
                ing_extra = "Jamon York";
                break;   
            case 2:
                ing_extra = "Champiñones";
                break;
            case 3:
                ing_extra = "Cebolla";
                break; 
            case 4:
                ing_extra = "Peperoni";
                break;
            case 5:
                ing_extra = "Cheddar";
                break;
            case 6:
                ing_extra = "Salsa Carbonara";
                break;
            case 7:
                ing_extra = "Salsa BBQ";
                break;
            case 8:
                ing_extra = "Bacon";
                break;
            default:
                ing_extra = " ";
        }
        return ing_extra;
    }
}
