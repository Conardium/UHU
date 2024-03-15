/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author ismae
 */
public class Pantalon implements Runnable {

    private Linea linea;

    public Pantalon(Linea linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        try {
            System.out.println("*Llega el PANTALON con id: " + Thread.currentThread().getName() + "*");
            linea.entraCorte("PANTALON");
            Thread.sleep(2 * 1000);
            linea.coserCamisa();
            System.out.println("\t\t\t\t----> el PANTALON " + Thread.currentThread().getName() + " se cose");
            Thread.sleep(2 * 1000);
            linea.saleCoser();
            System.out.println("\t\t\t\t\t<---- el PANTALON " + Thread.currentThread().getName() + " acaba .....");
        } catch (Exception e) {
        }
    }
}
