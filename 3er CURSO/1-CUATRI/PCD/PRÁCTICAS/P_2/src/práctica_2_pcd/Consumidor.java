/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_2_pcd;

/**
 *
 * @author ismae
 */
public class Consumidor implements Runnable{

    ColaLenta cola;
    
    public Consumidor(ColaLenta c){
        this.cola = c;
    }
    
    public void Consumir() throws Exception{
        for (int i = 0; i < 10; i++) {
            Object consumido = cola.Desacola();
            
            System.out.println("Id: " + Thread.currentThread().getId() + " - numero extraido: " + consumido);
        }
    }
    
    @Override
    public void run() {
        try {
            Consumir();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
}
