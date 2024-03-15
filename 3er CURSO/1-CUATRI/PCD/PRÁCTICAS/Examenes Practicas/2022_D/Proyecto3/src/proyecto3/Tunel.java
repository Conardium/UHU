/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3;

/**
 *
 * @author ismae
 */
public class Tunel {

    private int tunelesLibres = 3;
    private int coches = 0, furgos = 0;
    private int cochesEsperando = 0, furgosEsperando = 0;
    
    public synchronized void EntraCoche() throws InterruptedException{
        cochesEsperando++;
        while(tunelesLibres == 0 || coches == 2 || (furgosEsperando > 0 && furgos < 2)){
            wait();
        }
        cochesEsperando--;
        coches++;
        tunelesLibres--;
        System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
    }
    
    public synchronized void SaleCoche(){
        tunelesLibres++;
        coches--;
        System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
        notifyAll();
    }
    
    public synchronized void EntraFurgo() throws InterruptedException{
        furgosEsperando++;
        while(tunelesLibres == 0 || (furgos == 2 && cochesEsperando > 0)) {
            wait();
        }
        furgosEsperando--;
        furgos++;
        tunelesLibres--;
        System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
    }
    
    public synchronized void SaleFurgo(){
        tunelesLibres++;
        furgos--;
        System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
        notifyAll();
    }
    
}
