package proyecto1;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Ninyo extends Thread {

    private Piscina piscina;

    public Ninyo(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        //Mensaje de inicio con el identificador
        //Intentar acceder a la Piscina
        //Tiempo en la piscina 2-4s (si no hay adultos en la Piscina no espera)
        //Sale de la piscina

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            System.out.println("Llega el Niño con id: " + Thread.currentThread().getName());
            if (piscina.EntraNinyo()) {
                Thread.sleep((rnd.nextInt(2) + 2) * 1000);
                piscina.SaleNinyo();
            }
            else{
                System.out.println("!!! No hay adultos, el niño no nada ¡¡¡");
            }

        } catch (Exception e) {
        }
    }
}
