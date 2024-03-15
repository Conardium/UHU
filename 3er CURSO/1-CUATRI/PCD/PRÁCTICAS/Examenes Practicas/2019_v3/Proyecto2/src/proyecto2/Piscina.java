package proyecto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ismae
 */
public class Piscina {

    private int huecosLibres = 5;
    private ReentrantLock mutex = new ReentrantLock();
    private Condition colaAdulto = mutex.newCondition();
    private Condition colaNinyo = mutex.newCondition();

    private int adultosEsperando = 0;
    private int adultosEnLaPiscina = 0;

    //EntrAdulto
    public void EntraAdulto() throws InterruptedException {
        mutex.lock();
        try {
            adultosEsperando++;
            while (huecosLibres == 0) {
                System.out.println("\tLa piscina está llena, el ADULTO se espera");
                colaAdulto.await();
            }
            adultosEsperando--;
            adultosEnLaPiscina++;
            huecosLibres--;

            System.out.println("\t\t-----> El Adulto " + Thread.currentThread().getName() + " entra en la piscina; Libres= " + huecosLibres);
        } finally {
            mutex.unlock();
        }
    }

    //SaleAdulto
    public void SaleAdulto() {
        mutex.lock();
        try {
            adultosEnLaPiscina--;
            huecosLibres++;
            System.out.println("\t\t\t<------El Adulto " + Thread.currentThread().getName() + " sale de la piscina; Libres= " + huecosLibres);
            if (adultosEsperando > 0) {
                colaAdulto.signal();
            } else if (huecosLibres >= 2) {
                colaNinyo.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    //EntraNinyo
    public boolean EntraNinyo() throws InterruptedException {
        mutex.lock();
        try {
            if (adultosEnLaPiscina != 0) {
                while (huecosLibres < 2) {
                    colaNinyo.await();
                    System.out.println("\tLa piscina está llena o hay Adultos esperando, el NIÑO se espera");
                }
                huecosLibres -= 2;
                System.out.println("\t\t-----> El Niño " + Thread.currentThread().getName() + " entra en la piscina; Libres= " + huecosLibres);
                return true;
            } else {
                return false;
            }
        } finally {
            mutex.unlock();
        }
    }

    //SaleNinyo
    public void SaleNinyo() {
        mutex.lock();
        try {
            huecosLibres += 2;
            System.out.println("\t\t\t<------El Niño " + Thread.currentThread().getName() + " sale de la piscina; Libres= " + huecosLibres);
            
            if(adultosEsperando > 0){
                colaAdulto.signal();
                colaAdulto.signal();
            }
            else{
                colaNinyo.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
