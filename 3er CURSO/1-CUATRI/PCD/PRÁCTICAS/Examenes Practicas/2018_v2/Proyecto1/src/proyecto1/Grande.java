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
public class Grande implements Runnable {

    private Monton m;

    public Grande(Monton m) {
        this.m = m;
    }

    @Override
    public void run() {
        System.out.println("Llega la GRANDE - " + Thread.currentThread().getName());

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Grande esperando ...");
                m.cargaMucho();
                System.out.println("        Cargadora grande retira 2 en iteracion  "+  i);
                Thread.sleep((rnd.nextInt(2) + 1) * 1000);
            } catch (Exception e) {
            }
        }

    }

}
