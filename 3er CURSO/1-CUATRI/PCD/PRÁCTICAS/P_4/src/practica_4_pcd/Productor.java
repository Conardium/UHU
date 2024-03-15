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
public class Productor extends Thread{
    
    private ColaLenta cola;
    
    public Productor(ColaLenta c){
        System.out.println("Productor iniciado");
        this.cola = c;
    }
    
    @Override
    public void run(){
        
        Random random = new Random();
        random.setSeed(System.nanoTime());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
      
        try {
            for (int i = 0; i < 15; i++) {
                Thread.sleep((random.nextInt(3) + 1) * 1000); //Espera
                int insertado = random.nextInt(99);
                cola.Acola(insertado);
                System.out.println("Productor Id: " + getId() + " - numero insertado: " + insertado);
            }
        } catch (Exception e) {
            System.out.println("Productor id:" + Thread.currentThread().getId() + " -> " + e.getMessage());
        }
        
    }
}
