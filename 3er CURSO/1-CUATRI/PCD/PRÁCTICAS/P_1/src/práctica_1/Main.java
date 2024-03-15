/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_1;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cola c = new Cola(4);
        
        //Hay que generar un numero aleatorio
        Random r = new Random();
        r.setSeed(System.currentTimeMillis()); //Establecemos la semilla del numero random para que nunca se repita el numero random

        for (int i = 0; i < 10; i++) {
            try {
                int v = r.nextInt(100);
                System.out.print(i + ") ");
                if (v % 2 == 0) {
                    System.out.print("Par, desacolamos el " + c.Primero() + " -> ");
                    c.Desacola();
                    System.out.println("OK");
                } else {
                    System.out.print("Impar, acolamos el " + v + " -> ");
                    c.Acola(v);
                    System.out.println("OK");
                }
                
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        
        System.out.println("\nLa cola acabó con: " + c.GetNum() + " elementos");
    }

}
