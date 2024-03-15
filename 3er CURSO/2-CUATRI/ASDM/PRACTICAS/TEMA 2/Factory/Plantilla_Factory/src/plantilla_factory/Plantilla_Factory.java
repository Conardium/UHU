
package plantilla_factory;

import java.util.Scanner;

public class Plantilla_Factory {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String tipo;
        Producto p;
        Creador MiFabrica;
        System.out.println("¿Que tipo de producto quiere?");
        tipo = sc.nextLine();
        
        //FORMA 2
        MiFabrica = new Creador_concreto(tipo);
        p = MiFabrica.Factory_method();
        
        //FORMA 1
        /*if(tipo.equalsIgnoreCase("1"))
            p = new Producto_Concreto_1();
        else
            p = new Producto_Concreto_2();*/
        
        System.out.println("Has creado : " + p.Tipo_Producto());
    }
    
}
