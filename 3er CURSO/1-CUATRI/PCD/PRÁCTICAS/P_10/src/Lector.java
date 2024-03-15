
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author ismae
 */
public class Lector extends Thread {

    private int id;
    private MiCanvas cv;
    private ReentrantReadWriteLock L;

    public Lector(int id, MiCanvas cv, ReentrantReadWriteLock L) {
        this.id = id;
        this.cv = cv;
        this.L = L;
    }

    @Override
    public void run() {

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        while (true) {
            try {
                cv.setLector(id, 0);
                sleep((rnd.nextInt(3) + 1) * 1000);
                cv.setLector(id, 1);
                //Protocolo de entrada del lector
                L.readLock().lock();
                try {
                    cv.setLector(id, 2);
                    sleep(1000);
                    if (rnd.nextInt(4) == 0) { //Si quiere ser escritor
                        L.readLock().unlock();
                        cv.setLector(id, 3);
                        L.writeLock().lock();
                        cv.setLector(id, 4);
                        //Escribir
                        sleep(2000);
                        L.readLock().lock();
                        L.writeLock().unlock();
                        cv.setLector(id, 2);
                    } else { //Si no quiere
                        sleep(2000);
                    }
                    sleep(1000);
                    //Protocolo de salida del lector
                } finally {
                    L.readLock().unlock();
                }
            } catch (Exception e) {
            }
        }
    }
}
