/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_7_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class CanvasTunel extends Canvas {

    private int ancho, alto;
    private char lateralIzqLibre;
    private char lateralDerchLibre;
    private char centroLibre;
    private int nVehiculos;
    private ArrayList<Float> coches = new ArrayList<Float>();
    private ArrayList<Float> furgos = new ArrayList<Float>();
    private Image cocheimg;
    private Image furgoimg;
    
    private int idLateralIzq;
    private int idCentro;
    private int idLateralDerch;

    public CanvasTunel(int ancho, int alto, int nv) {
        super();
        this.setSize(ancho, alto);
        this.setBackground(Color.white);
        nVehiculos = nv;

        cocheimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/coche.png"));
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(cocheimg, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}
        
        furgoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/furgo.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(furgoimg, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}

    }

    public void representa(char li, char ld, char centro, ArrayList<Float> coches, ArrayList<Float> furgos, int idLi, int idC, int idLd) {
        this.lateralIzqLibre = li;
        this.lateralDerchLibre = ld;
        this.centroLibre = centro;
        this.coches = coches;
        this.furgos = furgos;
        idLateralIzq = idLi;
        idCentro = idC;
        idLateralDerch = idLd;
        repaint();
    }
    
    @Override
    public void update(Graphics g) {
        paint(g); //El problema es que ahora los numeros se superponen y no se ven, hay que volver a cargar el fondo para solucionarlo
    }

    @Override
    public void paint(Graphics g) {

        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics(); //Obtenemos la capacidad de pintar en la imagen
        
        int baseXColaCoches = 255;
        int baseYColaCoches = 60;
        int baseXColaFurgo = 255;
        int baseYColaFurgo = 310;

        int baseXStringsCola = 250;
        int baseYStringColaCoche = 85;
        int baseYStringColaFurgo = 330;

        int baseXtunel = 65;
        int baseYtunel = 60;
        int baseXStringTunel = 65;
        int baseYStringTunel = 85;

        Font cabecera = new Font("Arial", Font.BOLD, 25);
        Font indicadores = new Font("Arial", Font.BOLD, 18);

        //Texto de las colas y el tunel
        og.setFont(cabecera);
        og.drawString("Coches:", 250, 40);
        og.drawString("Furgos:", 250, 290);
        og.drawString("Tuneles", 50, 40);
        og.setFont(indicadores);
        og.drawString("Izq", 15, 105);
        og.drawString("Cen", 10, 230);
        og.drawString("Der", 10, 360);
        og.setFont(cabecera);
        
        //Colas de espera para el autolavado
        og.setColor(Color.cyan); //Coches
        og.fillRect(235, 50, 550, 100);
        og.setColor(Color.pink); //Furgos
        og.fillRect(235, 300, 550, 100);

        //Tuneles de lavado
        og.setColor(Color.LIGHT_GRAY);
        og.fillRect(50, 50, 100, 100); //izq
        og.fillRect(50, 175, 100, 100); //centro
        og.fillRect(50, 300, 100, 100); //derch

        
        //################ Representacion de las colas de espera (ArrayList) ###################
        //------COCHES
        if (!coches.isEmpty()) {
            for (int i = 0; i < coches.size(); i++) {
                og.drawImage(cocheimg, baseXColaCoches + i * 100, baseYColaCoches, 75,75,null);                
                og.setColor(Color.black);
                float id = coches.get(i);
                og.drawString(" " + (int) id, baseXStringsCola + i * 100, baseYStringColaCoche);
            }
        }
        
        //------FURGOS
        if (!furgos.isEmpty()) {
            for (int i = 0; i < furgos.size(); i++) {
                og.drawImage(furgoimg, baseXColaFurgo + i * 100, baseYColaFurgo, 75,75,null);                
                og.setColor(Color.black);
                float id = furgos.get(i);
                og.drawString(" " + (int) id, baseXStringsCola + i * 100, baseYStringColaFurgo);
            }
        }
        //######################################################################################
        
        
        //============== Representacion del la ocupacion de los tuneles ====================
        //------COCHES
        if (lateralIzqLibre == 'c') {
            og.drawImage(cocheimg, baseXtunel, baseYtunel, 75, 75, null); 
            og.setColor(Color.black);
            og.drawString(" "+idLateralIzq, baseXStringTunel, baseYStringTunel);
        }
        if (centroLibre == 'c') {
            og.drawImage(cocheimg, baseXtunel, baseYtunel + 130, 75, 75, null); 
            og.setColor(Color.black);
            og.drawString(" "+idCentro, baseXStringTunel, baseYStringTunel + 130);
        }
        if (lateralDerchLibre == 'c') {
            og.drawImage(cocheimg, baseXtunel, baseYtunel + 255, 75, 75, null); 
            og.setColor(Color.black);
            og.drawString(" "+idLateralDerch, baseXStringTunel, baseYStringTunel + 255);
        }
        
        //------FURGOS
        if (lateralIzqLibre == 'f') {
            og.drawImage(furgoimg, baseXtunel, baseYtunel, 75, 75, null); 
            og.setColor(Color.black);
            og.drawString(" "+idLateralIzq, baseXStringTunel, baseYStringTunel);
        }
        if (centroLibre == 'f') {
            og.drawImage(furgoimg, baseXtunel, baseYtunel + 130, 75, 75, null); 
            og.setColor(Color.black);
            og.drawString(" "+idCentro, baseXStringTunel, baseYStringTunel + 130);
        }
        if (lateralDerchLibre == 'f') {
            og.drawImage(furgoimg, baseXtunel, baseYtunel + 255, 75, 75, null); 
            og.setColor(Color.black);
            og.drawString(" "+idLateralDerch, baseXStringTunel, baseYStringTunel + 255);
        }
        //=====================================================================================
        
        
        //Vaciar tunel libre
        if (lateralIzqLibre == 'l') {
            og.setColor(Color.lightGray);
            og.fillRect(50, 50, 100, 100);
        }
        if (centroLibre == 'l') {
            og.setColor(Color.lightGray);
            og.fillRect(50, 175, 100, 100);
        }
        if (lateralDerchLibre == 'l') {
            og.setColor(Color.lightGray);
            og.fillRect(50, 300, 100, 100);
        }
        
        g.drawImage(img, 0, 0, null);
        
        
        /*============== Ejemplos graficos (solo para saber las posiciones a la hora de la representacion) ===========
            //Coches y furgos en las colas
        g.setColor(Color.yellow); //Coches
        g.fillOval(baseXColaCoches, baseYColaCoches, 75, 75); //1a posicion
        g.fillOval(baseXColaCoches+100, baseYColaCoches, 75, 75);// +100 en el eje X se coloca otro vehículo
        g.setColor(Color.black);
        g.drawString("1", baseXStringsCola, baseYStringColaCoche); //Id del primer coche
        g.drawString("2", baseXStringsCola + 100, baseYStringColaCoche);

        g.setColor(Color.blue); //Furgos
        g.fillOval(baseXColaFurgo, baseYColaFurgo, 75, 75); //1a furgo
        g.setColor(Color.white);
        g.drawString("3", baseXStringsCola, baseYStringColaFurgo); //Id del la primera furgo

            //TUNELES
        g.setColor(Color.blue);
        g.fillOval(baseXtunel, baseYtunel, 75, 75); //Tunel lateral arriba (izq)
        g.fillOval(baseXtunel, baseYtunel+255, 75, 75); //Tunel lateral abajo (derch)
        g.setColor(Color.white);
        g.drawString("4", baseXStringTunel, baseYStringTunel); //Izquierda
        g.drawString("5", baseXStringTunel, baseYStringTunel + 255); //Derecha
        g.setColor(Color.yellow);
        g.fillOval(65, baseYtunel+130, 75, 75); //Tunel Central
        g.setColor(Color.black);
        g.drawString("6", baseXStringTunel, baseYStringTunel + 130); //Centro
         */
    }
}
