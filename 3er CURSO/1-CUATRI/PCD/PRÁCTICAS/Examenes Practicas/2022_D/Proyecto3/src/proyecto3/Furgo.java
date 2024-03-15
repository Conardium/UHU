/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author ismae
 */
public class Furgo implements Callable<Integer>{
    private Tunel t;
    private int tiempo = 0;

    public Furgo(Tunel t) {
        this.t = t;
    }
    
    @Override
    public Integer call() throws Exception {
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        
        try {
            System.out.println("\t\t>> Llega la furgo-" + Thread.currentThread().getName());
            t.EntraCoche();
            System.out.println("\t\t\t---> La furgo-" + Thread.currentThread().getName() + " en TUNEL");
            tiempo = (rnd.nextInt(4)+1)*1000;
            Thread.sleep(tiempo);
            System.out.println("\t\t\t\t<--- La furgo-" + Thread.currentThread().getName() + " TERMINA");
            t.SaleCoche();
        } catch (Exception e) {
        }
        
        return tiempo;
    }
}
