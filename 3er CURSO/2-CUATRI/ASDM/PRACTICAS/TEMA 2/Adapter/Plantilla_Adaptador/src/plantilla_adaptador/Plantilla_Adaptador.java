package plantilla_adaptador;

import java.util.Scanner;

public class Plantilla_Adaptador {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int forma;
        iTarget objetoAdaptador = new Adaptador("Segundo");
        /*Adaptado ap = new Adaptado("Inicial");
        String forma;
        System.out.print("¿Cómo quieres sacar el nombre? (MAYUS/minus): ");
        forma = sc.nextLine();
        ap.mostrarNombre(forma);*/
        
        System.out.print("¿Cómo quieres sacar el nombre? (1.-MAYUS / 2.-minus): ");
        forma = sc.nextInt();
        objetoAdaptador.mostrarNombre(forma);
    }
    
}
