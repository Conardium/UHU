/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package proyecto1;

import java.util.Random;

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
    public static void main(String args[]) throws InterruptedException {
        
        /*Generador frame = new Generador();
        frame.setVisible(true);
        frame.setSize(800, 400);*/
        
        int nPersonas = 20;
        
        Piscina p = new Piscina();
        Thread personas[] = new Thread[nPersonas];
        
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        
        for (int i = 0; i < nPersonas; i++) {
            Thread.sleep((rnd.nextInt(2) + 1) * 1000);
            
            if(rnd.nextInt(10) < 6){
                personas[i] = new Thread(new Adulto(p));
            }
            else{
                personas[i] = new Ninyo(p);
            }
            
            personas[i].start();
        }
        
        for (int i = 0; i < nPersonas; i++) {
            personas[i].join();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
