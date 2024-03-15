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
public class Pequenya extends Thread {

    private Monton m;

    public Pequenya(Monton m) {
        this.m = m;
    }

    @Override
    public void run() {
        System.out.println("Llega la PEQUENYA - " + Thread.currentThread().getName());

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Pequenya esperando ...");
                m.cargaPoco();
                System.out.println("    Cargadora pequeña retira 1 en iteracion  "+  i);
                Thread.sleep((rnd.nextInt(2) + 1) * 1000);
            } catch (Exception e) {
            }
        }

    }
}
