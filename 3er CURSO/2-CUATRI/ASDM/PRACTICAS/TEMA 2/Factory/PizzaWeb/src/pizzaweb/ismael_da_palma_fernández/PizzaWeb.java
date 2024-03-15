package pizzaweb.ismael_da_palma_fernández;

import java.util.Scanner;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class PizzaWeb {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        Interfaz_Usuario iu = new Interfaz_Usuario();
        int opcion = 0;
        int continuar = -1;
               
        do {
            iu.Menu_Principal();
            opcion = sc.nextInt();
            
            if(opcion == 1){
                iu.Listar_Carta();
                opcion = sc.nextInt();
                if(opcion != 5){
                    iu.Realizar_Pedido(opcion);
                    
                    while (continuar != 0) {                        
                        System.out.print("Pulsa 0 para continuar: ");
                        continuar=sc.nextInt();
                    }
                    continuar = -1;
                }
                opcion = 0;
            }
            
            if(opcion == 2){
                do {                    
                    iu.Listar_Carta();
                    opcion = sc.nextInt();
                    iu.Sub_Menu_Carta(opcion);
                    while (continuar != 0) {                        
                        System.out.print("Pulsa 0 para continuar: ");
                        continuar=sc.nextInt();
                    }
                    continuar = -1;
                } while (opcion != 5);
            }  
        } while (opcion != 3);
        
    }//Fin del main
    
}
