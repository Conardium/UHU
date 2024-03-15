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
public class Coche implements Callable<Integer> {

    private Taller t;

    public Coche(Taller t) {
        this.t = t;
    }

    @Override
    public Integer call() throws Exception {

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int tiempoEnTaller = 0;

        try {
            System.out.println("\t\t\t>> COCHE: " + Thread.currentThread().getName() + " llega al taller");
            t.EntraCoche();
            System.out.println("\t\t\t\t-----> COCHE " + Thread.currentThread().getName() + " entra al taller");
            tiempoEnTaller = (rnd.nextInt(2) + 2) * 1000;
            Thread.sleep(tiempoEnTaller);
            t.SaleCoche();
            System.out.println("\t\t\t\t\t<----- COCHE " + Thread.currentThread().getName() + " sale al taller");
        } catch (Exception e) {
        }
        
        return tiempoEnTaller;
    }
}
