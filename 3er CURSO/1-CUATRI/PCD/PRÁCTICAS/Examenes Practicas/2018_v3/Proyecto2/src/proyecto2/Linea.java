/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ismae
 */
public class Linea {

    private int cortarLibre = 2;
    private boolean coserLibre = true;
    private int esperaPantalon = 0;

    ReentrantLock mutex = new ReentrantLock();
    Condition colaCorte = mutex.newCondition();
    Condition colaCoserPantalon = mutex.newCondition();
    Condition colaCoserCamisa = mutex.newCondition();

    public void entraCorte(String quien) throws InterruptedException {
        mutex.lock();
        try {
            if (cortarLibre == 0) {
                colaCorte.await();
            }
            cortarLibre--;
            System.out.println("\t--->Entra a Cortar " + quien + " co id" + Thread.currentThread().getName() + "; Libre: " + cortarLibre);
        } finally {
            mutex.unlock();
        }
    }

    public void coserPantalon() throws InterruptedException {
        mutex.lock();
        try {
            esperaPantalon++;
            if (!coserLibre) {
                colaCoserPantalon.await();
                System.out.println("!!! PANTALON " + Thread.currentThread().getName() + ": no hay hueco en las maquina de coser ¡¡¡");
            }
            esperaPantalon--;
            cortarLibre++;
            coserLibre = false;

            colaCorte.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void coserCamisa() throws InterruptedException {
        mutex.lock();
        try {
            if (!coserLibre || esperaPantalon > 0) {
                colaCoserCamisa.await();
                System.out.println("!!! CAMISA " + Thread.currentThread().getName() + ": no hay hueco en la maquina de coser ¡¡¡");
            }
            cortarLibre++;
            coserLibre = false;
            colaCorte.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void saleCoser() {
        mutex.lock();
        try {
            coserLibre = true;
            if (esperaPantalon > 0) {
                colaCoserPantalon.signal();
            } else {
                colaCoserCamisa.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
