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
public class ViajeroMaleta extends Thread{
    
    private float id;
    private CanvasAduana canvas;
    private Semaphore rayosXmaleta;
    private Semaphore perros;
    private Semaphore galleta;
    
    private ArrayList<Float> vMaletas; //Cola de espera de las maletas
    int[] ocupacionRayosXmaleta; //Para conocer la ocupacion de los rayosX
    int[] ocupacionPerros; //Para conocer la ocupacion de los perros
    
    public ViajeroMaleta(int id, Semaphore rayosXmaleta, Semaphore perros, Semaphore galleta, ArrayList<Float> vMaletas, int[] ocupacionRayosXmaleta, int[] ocupacionPerros, CanvasAduana cv){
        System.out.println("* Llega el viajero maleta-" + (int)id);
        this.id = id;
        canvas = cv;
        this.rayosXmaleta = rayosXmaleta;
        this.perros = perros;
        this.galleta = galleta;
        this.vMaletas = vMaletas;
        this.ocupacionRayosXmaleta = ocupacionRayosXmaleta;
        this.ocupacionPerros = ocupacionPerros;
    }
    
    @Override
    public void run() {
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        try {
            vMaletas.add(id); //Añadimos al viajero a la cola de espera
            canvas.estadoColaMaleta(vMaletas);
            rayosXmaleta.acquire(); //wait
            vMaletas.remove(id); //El viajero sale de la cola
            canvas.estadoColaMaleta(vMaletas);
            
            //Busca en que maquina de rayosX meterse
            if(ocupacionRayosXmaleta[0] == -1){
                ocupacionRayosXmaleta[0] = (int)id; //Se coloca en el rayoX de arriba
                canvas.ocupacionRayosXmaleta(0, (int)id);
                System.out.println("\t-->Viajero Maleta-" + (int)id + " pasa a la maquina de rayos X de maletas 1");
                
                Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo de rayosX
                
                perros.acquire(); // wait
                rayosXmaleta.release(); //signal
                ocupacionRayosXmaleta[0] = -1; //Avisamos de que el rayoX 0 está libre
                canvas.ocupacionRayosXmaleta(0, -1); //Avisamos al canvas de que ya no hay nadie en el rayoX 0
                
                if(ocupacionPerros[0] == -1){
                    ocupacionPerros[0] = (int)id; //Se coloca en el perro de arriba
                    System.out.println("\t\t----->Viajero Maleta-" + (int) id + " va al perro 1");
                    
                    canvas.ocupacionPerros(0, (int)id, "Maleta");
                    
                    Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo en el perro
                    System.out.println("\t\t\t----->El perro 1 acaba con el Viajero Maleta-" + (int) id);

                    canvas.avisarCuidador(0); //El cuidador va a darle la galleta al perro 1
                    galleta.release(); //signal
                    ocupacionPerros[0] = -1; //Acaba con el perro de arriba
                    canvas.ocupacionPerros(0, -1, "Maleta");
                    System.out.println("\t\t\t\t<-----Viajero Maleta-" + (int) id + " FINALIZA");
                }
                else{
                    ocupacionPerros[1] = (int)id; //Se coloca en el perro de arriba
                    System.out.println("\t\t----->Viajero Maleta-" + (int) id + " va al perro 2");
                    
                    canvas.ocupacionPerros(1, (int)id, "Maleta");
                    
                    Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo en el perro
                    System.out.println("\t\t\t----->El perro 2 acaba con el Viajero Maleta-" + (int) id);

                    canvas.avisarCuidador(1); //El cuidador va a darle la galleta al perro 2
                    galleta.release(); //signal
                    ocupacionPerros[1] = -1; //Acaba con el perro de arriba
                    canvas.ocupacionPerros(1, -1, "Maleta");
                    System.out.println("\t\t\t\t<-----Viajero Maleta-" + (int) id + " FINALIZA");
                }
            }
            else{
                ocupacionRayosXmaleta[1] = (int)id; //Se coloca en el rayoX de abajo
                canvas.ocupacionRayosXmaleta(1, (int)id);
                System.out.println("\t-->Viajero Maleta-" + (int)id + " pasa a la maquina de rayos X de maletas 2");
                
                Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo de rayosX
                
                perros.acquire(); // wait
                rayosXmaleta.release(); //signal
                ocupacionRayosXmaleta[1] = -1; //Avisamos de que el rayoX 1 está libre
                canvas.ocupacionRayosXmaleta(1, -1); //Avisamos al canvas de que ya no hay nadie en el rayoX 1
                
                if(ocupacionPerros[0] == -1){
                    ocupacionPerros[0] = (int)id; //Se coloca en el perro de arriba
                    System.out.println("\t\t----->Viajero Maleta-" + (int) id + " va al perro 1");
                    
                    canvas.ocupacionPerros(0, (int)id, "Maleta");
                    
                    Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo en el perro
                    System.out.println("\t\t\t----->El perro 1 acaba con el Viajero Maleta-" + (int) id);

                    canvas.avisarCuidador(0);
                    galleta.release(); //signal
                    ocupacionPerros[0] = -1; //Acaba con el perro de arriba
                    canvas.ocupacionPerros(0, -1, "Maleta");
                    System.out.println("\t\t\t\t<-----Viajero Maleta-" + (int) id + " FINALIZA");
                }
                else{
                    ocupacionPerros[1] = (int)id; //Se coloca en el perro de arriba
                    System.out.println("\t\t----->Viajero Maleta-" + (int) id + " va al perro 2");
                    canvas.ocupacionPerros(1, (int)id, "Maleta");
                    Thread.sleep((random.nextInt(3) + 4) * 1000); //Tiempo en el perro
                    System.out.println("\t\t\t----->El perro 2 acaba con el Viajero Maleta-" + (int) id);

                    canvas.avisarCuidador(1);
                    galleta.release(); //signal
                    ocupacionPerros[1] = -1; //Acaba con el perro de arriba
                    canvas.ocupacionPerros(1, -1, "Maleta");
                    System.out.println("\t\t\t\t<-----Viajero Maleta-" + (int) id + " FINALIZA");
                }
            }                     
        } catch (InterruptedException ex) {}
    }
}
