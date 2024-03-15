package proyecto1;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class Adulto implements Runnable {

    private Piscina piscina;

    public Adulto(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        //Mensaje de inicio con el identificador
        //Intentar acceder a la Piscina
        //Tiempo en la piscina 3-6s
        //Sale de la piscina

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            System.out.println("Llega el Adulto con id: " + Thread.currentThread().getName());
            piscina.EntraAdulto();
            Thread.sleep((rnd.nextInt(3) + 3) * 1000);
            piscina.SaleAdulto();       
        } catch (Exception e) {
        }
    }

}
