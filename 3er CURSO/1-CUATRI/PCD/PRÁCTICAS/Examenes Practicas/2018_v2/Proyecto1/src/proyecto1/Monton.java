/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author ismae
 */
public class Monton {
    private int cantidad = 4;
    private int grandesEsperando = 0;
    
    public synchronized void cargaPoco() throws InterruptedException{
        while(cantidad == 0 || (grandesEsperando > 0 && cantidad == 2)){
            wait();
        }
        cantidad--;
        notifyAll();
    }
    
    public synchronized void cargaMucho() throws InterruptedException{
        grandesEsperando++;
        while(cantidad < 2){
            wait();
        }
        grandesEsperando--;
        cantidad-=2;
        notifyAll();
    }
    
    public synchronized void Rellena(int trozos){
        cantidad += trozos;
        System.out.println("\t--> Se han dejado " + trozos + " trozos, ahora hay " + cantidad);
        notifyAll();
    }
}
