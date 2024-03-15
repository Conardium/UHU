package practica_8_pcd;

import java.util.Random;
import java.util.concurrent.Callable;
/**
 *
 * @author ismae
 */
public class Ciclista implements Callable<Integer> {

    private float id;
    private int tiempoAtendido;
    private Tienda tienda;

    public Ciclista(Tienda t, float id) {
        this.id = id;
        this.tienda = t;
        System.out.println("** Llega el Ciclista-" + (int)id + " **");
    }

    @Override
    public Integer call() {
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        
        try {
            tienda.entraReparar(id);
            System.out.println("\t---> El ciclista-" + (int)id + " entra en el MECANICO a reparar");
            
            tiempoAtendido = (random.nextInt(3) + 5) * 1000;
            Thread.sleep(tiempoAtendido); //Tiempo de reparación
            tienda.saleReparar();
            System.out.println("\t\t<--- El ciclista-" + (int)id + " sale del MECANICO y se va");
        } catch (Exception e) {
            System.out.println("============= Hilo Ciclista-" + (int)id + " interrumpido =============");
        }

        return tiempoAtendido;
    }

}
