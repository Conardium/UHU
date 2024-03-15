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
public class Monton {

    private int cantidad = 4;
    private int grandesEsperando = 0;

    ReentrantLock mutex = new ReentrantLock();
    Condition colaPequenya = mutex.newCondition();
    Condition colaGrande = mutex.newCondition();

    public void cargaPoco() throws InterruptedException {
        mutex.lock();
        try {
            while (cantidad == 0) {
                colaPequenya.await();
            }
            cantidad--;
        } finally {
            mutex.unlock();
        }
    }

    public void cargaMucho() throws InterruptedException {
        mutex.lock();
        try {
            grandesEsperando++;
            while (cantidad < 2) {
                colaGrande.await();
            }
            grandesEsperando--;
            cantidad -= 2;
        } finally {
            mutex.unlock();

        }
    }

    public void Rellena(int trozos) {
        mutex.lock();
        try {
            cantidad += trozos;
            System.out.println("\t--> Se han dejado " + trozos + " trozos, ahora hay " + cantidad);
            colaGrande.signal();
            if (cantidad == 3) {
                colaPequenya.signal();
            } else if (cantidad > 3) {
                colaPequenya.signal();
                colaPequenya.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
