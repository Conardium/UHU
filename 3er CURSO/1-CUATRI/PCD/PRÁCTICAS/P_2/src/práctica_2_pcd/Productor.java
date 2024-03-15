/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_2_pcd;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Productor extends Thread{
    
    ColaLenta cola;
    
    public Productor(ColaLenta c){
        this.cola = c;
    }
    
    public void Producir() throws Exception{
        
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        
        for (int i = 0; i < 10; i++) {
            float aleatorio = r.nextFloat();
            cola.Acola(aleatorio);
        
            System.out.println("Id: " + getId() + " - numero insertado: " + aleatorio);
        }
    }
    
    @Override
    public void run(){
        try {
            Producir();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
