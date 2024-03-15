/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Coche implements Runnable {

    private Taller t;

    public Coche(Taller t) {
        this.t = t;
    }

    @Override
    public void run() {

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            System.out.println("\t\t\t>> COCHE: " + Thread.currentThread().getName() + " llega al taller");
            t.EntraCoche();
            System.out.println("\t\t\t\t-----> COCHE " + Thread.currentThread().getName() + " entra al taller");
            Thread.sleep((rnd.nextInt(2) + 2) * 1000);
            t.SaleCoche();
            System.out.println("\t\t\t\t\t<----- COCHE " + Thread.currentThread().getName() + " sale al taller");
        } catch (Exception e) {
        }
    }
}
