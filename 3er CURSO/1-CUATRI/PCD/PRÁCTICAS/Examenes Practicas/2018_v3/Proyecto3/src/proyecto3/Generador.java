/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package proyecto3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class Generador extends java.awt.Frame {

    /**
     * Creates new form Generador
     */
    public Generador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        int nPrendas = 10;
        Thread[] prendas = new Thread[nPrendas];

        Semaphore cortar = new Semaphore(2);
        Semaphore coser = new Semaphore(1);
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            for (int i = 0; i < nPrendas; i++) {
                if (rnd.nextInt(10) < 7) {
                    prendas[i] = new Camisa(cortar, coser);
                } else {
                    prendas[i] = new Thread(new Pantalon(cortar, coser));
                }
                prendas[i].start();
                Thread.sleep((rnd.nextInt(2)+1) * 1000);
            }

            for (int i = 0; i < nPrendas; i++) {
                prendas[i].join();
            }
        } catch (InterruptedException ex) {}
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
