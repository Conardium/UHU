/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author ismae
 */
public class Camisa extends Thread{
    private Linea linea;

    public Camisa(Linea linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        try {
            System.out.println("*Llega la CAMISA con id: " + Thread.currentThread().getName() + "*");
            linea.entraCorte("CAMISA");
            Thread.sleep(2 * 1000);
            linea.coserCamisa();
            System.out.println("\t\t----> La CAMISA " + Thread.currentThread().getName() + " se cose");
            Thread.sleep(2 * 1000);
            linea.saleCoser();
            System.out.println("\t\t\t<---- La CAMISA " + Thread.currentThread().getName() + " acaba .....");
        } catch (Exception e) {
        }
    } 
}
