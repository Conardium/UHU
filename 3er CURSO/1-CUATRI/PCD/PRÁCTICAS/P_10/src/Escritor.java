
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author ismae
 */
public class Escritor implements Runnable {

    private int id;
    private MiCanvas cv;
    private ReentrantReadWriteLock L;

    public Escritor(int id, MiCanvas cv, ReentrantReadWriteLock L) {
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
                cv.setEscritor(id, 0);
                Thread.sleep((rnd.nextInt(3) + 1) * 1000);
                cv.setEscritor(id, 1);
                //Protocolo de entrada del escritor
                L.writeLock().lock();
                try {
                    cv.setEscritor(id, 2);
                    Thread.sleep((rnd.nextInt(3) + 2) * 1000);
                    //Protocolo de salida del escritor
                } finally {
                    L.writeLock().unlock();
                }
            } catch (Exception e) {
            }
        }
    }
}
