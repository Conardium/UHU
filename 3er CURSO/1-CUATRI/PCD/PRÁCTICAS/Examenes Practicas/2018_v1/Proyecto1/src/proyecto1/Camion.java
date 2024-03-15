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
public class Camion extends Thread {

    private Taller t;

    public Camion(Taller t) {
        this.t = t;
    }

    @Override
    public void run() {
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        
        try {
            System.out.println(">> CAMION: " + Thread.currentThread().getName() + " llega al taller");
            t.EntraCamion();
            System.out.println("\t-----> CAMION " + Thread.currentThread().getName() + " entra al taller");
            Thread.sleep((rnd.nextInt(2)+3)*1000);
            t.SaleCamion();
            System.out.println("\t\t<----- CAMION " + Thread.currentThread().getName() + " sale al taller");
        } catch (Exception e) {
        }
    }
}
