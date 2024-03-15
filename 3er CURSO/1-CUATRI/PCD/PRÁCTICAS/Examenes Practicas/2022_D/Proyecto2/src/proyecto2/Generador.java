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
public class Generador {

    public static void main(String[] args) {

        Tunel tunel = new Tunel();
        Thread[] vehiculos = new Thread[10];

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            for (int i = 0; i < 10; i++) {
                if (rnd.nextInt(10) < 5) {
                    vehiculos[i] = new Coche(tunel);
                    vehiculos[i].start();
                } else {
                    vehiculos[i] = new Thread(new Furgo(tunel));
                    vehiculos[i].start();
                }
                Thread.sleep((rnd.nextInt(1)+2)*1000);
            }
            
            for (int i = 0; i < 10; i++) {
                vehiculos[i].join();
            }
        } catch (Exception e) {
        }
    }
}
