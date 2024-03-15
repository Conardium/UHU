package practica_8_pcd;

import java.util.Random;
import java.util.concurrent.Callable;
/**
 *
 * @author ismae
 */
public class Cliente implements Callable<Integer> {

    private float id;
    private int tiempoAtendido;
    private Tienda tienda;
    private String entra;

    public Cliente(Tienda t, float id) {
        this.id = id;
        this.tienda = t;
        System.out.println("** Llega el Cliente-" + (int)id + " **");
    }

    @Override
    public Integer call() {
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        
        try {
            entra = tienda.entraComprar(id);
            if(entra.equals("Vendedor")){
                System.out.println("\t---> El cliente-" + (int)id + " entra en el VENDEDOR");
            }
            else{
                System.out.println("\t---> El cliente-" + (int)id + " entra en el MECANICO");
            }
            
            tiempoAtendido = (random.nextInt(3) + 5) * 1000;
            Thread.sleep(tiempoAtendido); //Tiempo de Compra
            tienda.saleComprar(entra);
            System.out.println("\t\t<--- El cliente-" + (int)id + " sale del " + entra + " y se va");
        } catch (Exception e) {
            System.out.println("============= Hilo Cliente-" + (int)id + " interrumpido =============");
        }

        return tiempoAtendido;
    }

}
