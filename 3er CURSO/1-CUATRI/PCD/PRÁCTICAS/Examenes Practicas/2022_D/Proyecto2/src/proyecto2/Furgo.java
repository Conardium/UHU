/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Furgo implements Runnable{
    private Tunel t;

    public Furgo(Tunel t) {
        this.t = t;
    }
    
    @Override
    public void run() {
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        
        try {
            System.out.println("\t\t>> Llega la furgo-" + Thread.currentThread().getName());
            t.EntraCoche();
            System.out.println("\t\t\t---> La furgo-" + Thread.currentThread().getName() + " en TUNEL");
            Thread.sleep((rnd.nextInt(2)+1)*3000);
            System.out.println("\t\t\t\t<--- La furgo-" + Thread.currentThread().getName() + " TERMINA");
            t.SaleCoche();
        } catch (Exception e) {
        }
    }
}
