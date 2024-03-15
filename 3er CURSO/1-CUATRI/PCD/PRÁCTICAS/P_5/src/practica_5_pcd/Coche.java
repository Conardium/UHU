/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_5_pcd;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Coche extends Thread{
    
    private char posicionDeLavado;
    private float id;
    private Tunel tunel;
    
    public Coche(Tunel t, int id){
        System.out.println("* Llega el coche-" + id);
        this.tunel = t;
        this.id = id;
    }
    
    @Override
    public void run() {

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        try {
            Thread.sleep(10);
            posicionDeLavado = tunel.entraCoche(id);
            System.out.println("\t-->El coche-" + (int)id + " entra en el tunel " + posicionDeLavado);
            Thread.sleep((random.nextInt(3) + 6) * 1000); //Tiempo de lavado
            tunel.saleCoche(posicionDeLavado);
            System.out.println("\t\t<----El coche-" + (int)id + " sale del tunel " + posicionDeLavado);
        } catch (Exception e) {}
    }
}