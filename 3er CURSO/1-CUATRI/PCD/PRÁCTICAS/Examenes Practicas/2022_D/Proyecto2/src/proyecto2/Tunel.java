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
public class Tunel {

    private int tunelesLibres = 3;
    private int coches = 0, furgos = 0;
    private int cochesEsperando = 0, furgosEsperando = 0;

    private ReentrantLock mutex = new ReentrantLock();
    private Condition colaCoches = mutex.newCondition();
    private Condition colaFurgos = mutex.newCondition();

    public void EntraCoche() throws InterruptedException {
        mutex.lock();
        try {
            cochesEsperando++;
            while (tunelesLibres == 0 || coches == 2) {
                colaCoches.await();
            }
            cochesEsperando--;
            coches++;
            tunelesLibres--;
            System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
        } finally {
            mutex.unlock();
        }
    }

    public void SaleCoche() {
        mutex.lock();
        try {
            tunelesLibres++;
            coches--;
            if (furgosEsperando > 0 && (furgos < 2 || cochesEsperando == 0)) {
                colaFurgos.signal();
            } else {
                colaCoches.signal();
            }
            System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
        } finally {
            mutex.unlock();
        }
    }

    public void EntraFurgo() throws InterruptedException {
        mutex.lock();
        try {
            furgosEsperando++;
            while (tunelesLibres == 0 || (furgos == 2 && cochesEsperando > 0)) {
                colaFurgos.await();
            }
            furgosEsperando--;
            furgos++;
            tunelesLibres--;
            System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
        } finally {
            mutex.unlock();
        }
    }

    public void SaleFurgo() {
        mutex.lock();
        try {
            tunelesLibres++;
            furgos--;
            if (furgosEsperando > 0 && (furgos < 2 || cochesEsperando == 0)) {
                colaFurgos.signal();
            } else {
                colaCoches.signal();
            }
            System.out.println("!!! Tuneles libres: " + tunelesLibres + " ¡¡¡");
        } finally {
            mutex.unlock();
        }
    }

}
