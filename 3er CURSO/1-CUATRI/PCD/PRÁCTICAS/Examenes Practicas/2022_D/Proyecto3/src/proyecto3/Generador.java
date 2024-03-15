/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author ismae
 */
public class Generador {

    public static void main(String[] args) {

        Tunel tunel = new Tunel();
        Thread[] vehiculos = new Thread[10];

        ExecutorService thpFurgos = Executors.newFixedThreadPool(3);
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        Future<Integer>[] ff = new Future[10];
        int coches = 0, furgos = 0;
        
        try {
            for (int i = 0; i < 10; i++) {
                if (rnd.nextInt(10) < 5) {
                    vehiculos[coches] = new Coche(tunel);
                    vehiculos[coches].start();
                    coches++;
                } else {
                    ff[furgos] = thpFurgos.submit(new Furgo(tunel));
                    furgos++;
                }
                Thread.sleep((rnd.nextInt(1)+2)*1000);
            }
            
            for (int i = 0; i < coches; i++) {
                vehiculos[i].join();
            }
            
            int tiempoInd = 0;
            int tiempoTotal = 0;
            System.out.println("\n\n");
            
            for (int i = 0; i < furgos; i++) {
                tiempoInd = ff[i].get();
                System.out.println("===> La furgo " + i + " fue atendida en " + tiempoInd/1000 + "s");
                tiempoTotal += tiempoInd;
            }
            System.out.println("El tiempo total es: " + tiempoTotal/1000 + "s");
            System.out.println("Total de furgos: " + furgos);
        } catch (Exception e) {
        }
    }
}
