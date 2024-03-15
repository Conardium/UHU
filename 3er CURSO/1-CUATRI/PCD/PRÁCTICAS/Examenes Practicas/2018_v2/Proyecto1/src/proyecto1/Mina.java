/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author ismae
 */
public class Mina {
    public static void main(String[] args) throws InterruptedException {
        
        Monton monton = new Monton();
        
        Thread h1 = new Pequenya(monton);
        Thread h2 = new Pequenya(monton);
        Thread h3 = new Thread(new Grande(monton));
        Thread cinta = new Cinta(monton);
        
        cinta.start();
        h1.start();
        h2.start();
        h3.start();
        
        h1.join();
        h2.join();
        h3.join();
        cinta.interrupt();
    }
}
