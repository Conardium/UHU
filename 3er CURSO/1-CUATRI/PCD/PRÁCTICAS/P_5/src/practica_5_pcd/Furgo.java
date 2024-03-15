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
public class Furgo implements Runnable{
    
    private char posicionDeLavado;
    private float id;
    private Tunel tunel;
    
    public Furgo(Tunel t, int id){
        System.out.println("* Llega la furgo-" + id);
        this.tunel = t;
        this.id = id;
    } 

    @Override
    public void run() {
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        try {
            Thread.sleep(10);
            posicionDeLavado = tunel.entraFurgo(id);
            System.out.println("\t-->La furgo-" + (int)id + " entra en el tunel " + posicionDeLavado);
            Thread.sleep((random.nextInt(3) + 6) * 1000); //Tiempo de lavado
            tunel.saleFurgo(posicionDeLavado);
            System.out.println("\t\t<----La furgo-" + (int)id + " sale del tunel " + posicionDeLavado);
        } catch (Exception e) {}
    }
}
