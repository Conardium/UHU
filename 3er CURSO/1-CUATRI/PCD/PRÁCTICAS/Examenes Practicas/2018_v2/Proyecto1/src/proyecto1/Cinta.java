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
public class Cinta extends Thread {

    private Monton m;

    public Cinta(Monton m) {
        this.m = m;
    }

    @Override
    public void run() {
        System.out.println("Llega la Cinta - " + Thread.currentThread().getName());

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        int cantidad = 0;
        try {
            while (true) {
                Thread.sleep((rnd.nextInt(4) + 1) * 1000);
                cantidad = rnd.nextInt(3) + 2;
                System.out.println(">> La cinta rellena " + cantidad);
                m.Rellena(cantidad);
            }
        } catch (Exception e) {
        }
    }
}
