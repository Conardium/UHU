/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author ismae
 */
public class Linea {
    
    private int cortarLibre = 2;
    private boolean coserLibre = true;
    private int esperaPantalon = 0;
    
    public synchronized void entraCorte(String quien) throws InterruptedException{
        
        while(cortarLibre == 0){
            wait();
        }
        cortarLibre--;
        System.out.println("\t--->Entra a Cortar " + quien + " co id" + Thread.currentThread().getName() + "; Libre: " + cortarLibre);
    }
    
    public synchronized void coserPantalon() throws InterruptedException{
        esperaPantalon++;
        while(!coserLibre){
            wait();
            System.out.println("!!! PANTALON " + Thread.currentThread().getName() + ": no hay hueco en las maquina de coser ¡¡¡");
        }
        esperaPantalon--;
        cortarLibre++;
        coserLibre = false;
        notifyAll();
    }
    
    public synchronized void coserCamisa() throws InterruptedException{
        while(!coserLibre || esperaPantalon > 0){
            wait();
            System.out.println("!!! CAMISA " + Thread.currentThread().getName() + ": no hay hueco en la maquina de coser ¡¡¡");
        }
        cortarLibre++;
        coserLibre = false;
        notifyAll();
    }
    
    public synchronized void saleCoser(){
        coserLibre = true;
        notifyAll();
    }
}
