package practica_8_pcd;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ismae
 */
public class Tienda {

    CanvasTienda canvas;
    char vendedorLibre = 'l'; //Ambos libres inicialmente
    char mecanicoLibre = 'l'; //'R' = ciclista, 'C' = cliente
    int clientesEsperando = 0;
    int ciclistasEsperando = 0;

    private ArrayList<Float> clientes = new ArrayList<Float>();
    private ArrayList<Float> ciclistas = new ArrayList<Float>();

    final Lock mutex = new ReentrantLock(true);
    final Condition colaClientes = mutex.newCondition();
    final Condition colaCiclistas = mutex.newCondition();

    public Tienda(CanvasTienda cv) {
        System.out.println("============= La tienda ha abierto =============");
        this.canvas = cv;
    }

    public String entraComprar(float id) throws InterruptedException {
        mutex.lock();
        try {
            clientes.add(id);
            canvas.ActualizaColaClientes(clientes);

            while (vendedorLibre != 'l' && (clientesEsperando < 2 || mecanicoLibre != 'l')) {
                System.out.println("<<!>> No hay hueco, el cliente-" + (int) id + " se espera <<!>>");
                clientesEsperando++;
                colaClientes.await();
                clientesEsperando--;
            }

            clientes.remove(id);
            canvas.ActualizaColaClientes(clientes);

            if (vendedorLibre == 'l') {
                vendedorLibre = 'C';
                canvas.compra("Vendedor", (int)id);
                return "Vendedor";
            } else {
                mecanicoLibre = 'C';
                canvas.compra("Mecanico", (int)id);
                return "Mecánico";
            }
        } finally {
            mutex.unlock();
        }
    }

    public void saleComprar(String donde) {
        mutex.lock();
        try {
            if(donde.equals("Vendedor")){
                vendedorLibre = 'l';
                canvas.termina("Vendedor");
                colaClientes.signal();
            }
            else{
                mecanicoLibre = 'l';
                canvas.termina("Mecanico");
                
                if(clientesEsperando <= 2){
                    colaCiclistas.signal();
                }
                else{
                    colaClientes.signal();
                }
            }           
        } finally {mutex.unlock();}
    }

    public void entraReparar(float id) throws InterruptedException {
        mutex.lock();
        try {
            ciclistas.add(id);
            canvas.ActualizaColaCiclistas(ciclistas);

            while (mecanicoLibre != 'l') {
                System.out.println("<<!>> No hay hueco, el ciclista-" + (int) id + " se espera <<!>>");
                ciclistasEsperando++;
                colaCiclistas.await();
                ciclistasEsperando--;
            }

            ciclistas.remove(id);
            canvas.ActualizaColaCiclistas(ciclistas);

            mecanicoLibre = 'R';
            canvas.repara((int)id);
        } finally {mutex.unlock();}
    }

    public void saleReparar() {
        mutex.lock();
        try {
            mecanicoLibre = 'l';
            canvas.termina("Mecanico");
            
            if(clientesEsperando > 2){
                colaClientes.signal();
            }
            else{
                colaCiclistas.signal();
            }
        } finally {mutex.unlock();}
    }
}
