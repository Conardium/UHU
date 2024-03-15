/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_2_pcd;

import java.util.Random;

/**
 *
 * @author ismae
 */
public class UsaCola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        ColaLenta cola = new ColaLenta(10);
        
        Productor p = new Productor(cola);
        
        p.start();
        p.join();
        
        Consumidor c1 = new Consumidor(cola);
        Consumidor c2 = new Consumidor(cola);
        
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }

}
