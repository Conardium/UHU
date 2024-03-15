/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ismae
 */
public class Camisa extends Thread{
    
    Semaphore cortar, coser;

    public Camisa(Semaphore cortar, Semaphore coser) {
        this.cortar = cortar;
        this.coser = coser;
    }

    @Override
    public void run() {
        try {
            System.out.println("*Llega la CAMISA con id: " + Thread.currentThread().getName() + "*");
            cortar.acquire();
            System.out.println("\t----> la CAMISA " + Thread.currentThread().getName() + " se corta");
            Thread.sleep(2 * 1000);
            coser.acquire();
            cortar.release();
            System.out.println("\t\t----> La CAMISA " + Thread.currentThread().getName() + " se cose");
            Thread.sleep(2 * 1000);
            coser.release();
            System.out.println("\t\t\t<---- La CAMISA " + Thread.currentThread().getName() + " acaba .....");
        } catch (Exception e) {
        }
    } 
}
