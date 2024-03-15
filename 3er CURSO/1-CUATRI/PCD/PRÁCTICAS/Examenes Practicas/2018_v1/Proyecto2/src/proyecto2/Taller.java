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
public class Taller {

    private int operariosLibres = 4;
    private int camionesEsperando = 0;

    ReentrantLock mutex = new ReentrantLock();
    Condition colaCoches = mutex.newCondition();
    Condition colaCamiones = mutex.newCondition();

    public void EntraCamion() throws InterruptedException {
        mutex.lock();
        try {
            camionesEsperando++;
            if (operariosLibres < 2) {
                colaCamiones.await();
            }
            camionesEsperando--;
            operariosLibres -= 2;
            System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
        } finally {
            mutex.unlock();
        }
    }

    public void SaleCamion() {
        mutex.lock();
        try {
            operariosLibres += 2;
            System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
            if (camionesEsperando > 0) {
                colaCamiones.signal();
            } else {
                colaCoches.signal();
                colaCoches.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void EntraCoche() throws InterruptedException {
        mutex.lock();
        try {
            if (operariosLibres == 0 || camionesEsperando > 0) {
                colaCoches.await();
            }
            operariosLibres--;
            System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
        } finally {
            mutex.unlock();
        }
    }

    public void SaleCoche() {
        mutex.lock();
        try {
            operariosLibres++;
            System.out.println("!! Operarios Libres: " + operariosLibres + " ¡¡");
            if (camionesEsperando > 0) {
                if(operariosLibres >= 2)
                    colaCamiones.signal();
            } else {
                colaCoches.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
