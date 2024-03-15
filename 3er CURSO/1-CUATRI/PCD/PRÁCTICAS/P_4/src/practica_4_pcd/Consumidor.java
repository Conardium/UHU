/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_4_pcd;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Consumidor implements Runnable{

    private ColaLenta cola;
    
    public Consumidor(ColaLenta c){
        System.out.println("Consumidor iniciado");
        this.cola = c;
    }

    @Override
    public void run() {
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis()+125);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
   
        try {
            for (int i = 0; i < 15; i++) {
                Thread.sleep((random.nextInt(3) + 1) * 1000); //Espera
                System.out.println("Consumidor Id: " + Thread.currentThread().getId() + " - numero extraido: " + cola.Desacola());
            }
        } catch (Exception e) {
            System.out.println("Consumidor id:" + Thread.currentThread().getId() + " -> " + e.getMessage());
        }
    }
    
    
    
}
