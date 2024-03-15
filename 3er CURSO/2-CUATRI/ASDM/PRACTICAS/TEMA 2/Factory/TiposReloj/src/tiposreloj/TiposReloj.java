
package tiposreloj;

import java.util.Scanner;

public class TiposReloj {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String tipo_reloj;
        Reloj r;
        Creador miFabrica;
        
        System.out.print("Que tipo de reloj quieres (AM-PM ó 24): ");
        tipo_reloj = sc.nextLine();
        
        //Luego esto:
        miFabrica = new Creador_concreto(tipo_reloj);      
        r = miFabrica.Factory_method();
        
        //Primero esto:
        /*if(tipo_reloj.equalsIgnoreCase("24"))
            r = new Reloj24h();
        else
            r = new Reloj_AMPM();*/
        
        r.dame_hora();
    }
    
}
