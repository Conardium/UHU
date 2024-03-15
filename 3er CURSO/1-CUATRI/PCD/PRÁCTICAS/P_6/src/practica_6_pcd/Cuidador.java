/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_6_pcd;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ismae
 */
public class Cuidador extends Thread{
    
    private Semaphore perros;
    private Semaphore galleta;
    private CanvasAduana canvas;
    
    public Cuidador(Semaphore perros, Semaphore galleta, CanvasAduana cv){
        System.out.println("<<El cuidador está listo para la jornada laboral>>");
        this.perros = perros;
        this.galleta = galleta;
        canvas = cv;
    }
    
    @Override
    public void run() {
        
        try {
            while(true){ //Bucle infinito
                galleta.acquire(); //wait
                System.out.println("\t<!>El cuidador le da una galleta al perro<!>");
                Thread.sleep(1500); //Tiempo que tarda el cuidador en dar la galleta al perro
                canvas.avisarCuidador(-1); //El cuidador termina con el perro

                perros.release(); //signal
            }
        } catch (Exception e) {}
    }
}
