/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author ismae
 */
public class Taller {
    
    private int operariosLibres = 4;
    private int camionesEsperando = 0;
    
    public synchronized void EntraCamion() throws InterruptedException {
        camionesEsperando++;
        while (operariosLibres < 2) {
            wait();
        }
        camionesEsperando--;
        operariosLibres -= 2;
        System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
    }
    
    public synchronized void SaleCamion() {
        operariosLibres += 2;
        System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
        notifyAll();
    }
    
    public synchronized void EntraCoche() throws InterruptedException {
        while (operariosLibres == 0 || camionesEsperando > 0) {
            wait();
        }
        operariosLibres--;
        System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
    }
    
    public synchronized void SaleCoche() {
        operariosLibres++;
        System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
        notifyAll();
    }
}
