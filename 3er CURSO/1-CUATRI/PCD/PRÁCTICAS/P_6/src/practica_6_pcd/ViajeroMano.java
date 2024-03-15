/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_6_pcd;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author ismae
 */
public class ViajeroMano implements Runnable{
    
    private float id;
    private CanvasAduana canvas;
    private Semaphore rayosXmano;
    private Semaphore perros;
    private Semaphore galleta;
    
    private ArrayList<Float> vManos; //Cola de espera de las maletas
    int[] ocupacionPerros; //Para conocer la ocupacion de los perros
    
    public ViajeroMano(int id, Semaphore rayosXmano, Semaphore perros, Semaphore galleta,  ArrayList<Float> vManos, int[] ocupacionPerros, CanvasAduana cv){
        System.out.println("* Llega el viajero mano-" + (int)id);
        this.id = id;
        canvas = cv;
        this.rayosXmano = rayosXmano;
        this.perros = perros;
        this.galleta = galleta;
        this.vManos = vManos;
        this.ocupacionPerros = ocupacionPerros;
    }

    @Override
    public void run() {
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        try {
            vManos.add(id);
            canvas.estadoColaMano(vManos);
            rayosXmano.acquire(); //wait
            vManos.remove(id);
            canvas.estadoColaMano(vManos);
            
            //Entra en la maquina de rayosX
            canvas.ocupacionRayosXmano((int)id);
            System.out.println("\t-->Viajero Mano-" + (int)id + " pasa por su maquina de rayos X");
            
            Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo de rayosX
            
            perros.acquire(); // wait
            rayosXmano.release(); //signal
            canvas.ocupacionRayosXmano(-1); //El viajero se va de la maquina
            
            if(ocupacionPerros[0] == -1){
                ocupacionPerros[0] = (int)id;
                System.out.println("\t\t----->Viajero Mano-" + (int) id + " va al perro 1");
                
                canvas.ocupacionPerros(0, (int)id, "Mano");
                
                Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo de perro
                System.out.println("\t\t\t----->El perro 1 acaba con el Viajero Mano-" + (int) id);

                canvas.avisarCuidador(0); //El cuidador va a darle la galleta al perro 1
                galleta.release(); //signal
                ocupacionPerros[0] = -1; //Acaba con el perro de arriba
                canvas.ocupacionPerros(0, -1, "Mano");
                System.out.println("\t\t\t\t<-----Viajero Mano-" + (int) id + " FINALIZA");
            }
            else{
                ocupacionPerros[1] = (int)id;
                System.out.println("\t\t----->Viajero Mano-" + (int) id + " va al perro 2");
                
                canvas.ocupacionPerros(1, (int)id, "Mano");
                
                Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo de perro
                System.out.println("\t\t\t----->El perro 2 acaba con el Viajero Mano-" + (int) id);

                canvas.avisarCuidador(1); //El cuidador va a darle la galleta al perro 1
                galleta.release(); //signal
                ocupacionPerros[1] = -1; //Acaba con el perro de arriba
                canvas.ocupacionPerros(1, -1, "Mano");
                System.out.println("\t\t\t\t<-----Viajero Mano-" + (int) id + " FINALIZA");
            }
        } catch (InterruptedException ex) {}
    }
}
