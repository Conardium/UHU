/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_6_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author ismae
 */
public class CanvasAduana extends Canvas{
    
    private int nViajeros;
    private ArrayList<Float> vMaletas = new ArrayList<Float>();
    private ArrayList<Float> vMano = new ArrayList<Float>();
    
    int[] ocupacionRayosXmaleta = {-1, -1}; //Array de dos posiciones
    int ocupacionRayosXmano = -1;
    int[] ocupacionPerros = {-1, -1}; //Array de dos posiciones
    String perro_1_analizaA = "";
    String perro_2_analizaA = "";
    int posicionCuidador = -1; //Inicialmente el cuidador no está
    
    //Imagenes que se van a usar
    private Image viajeroMaletaimg;
    private Image viajeroManoimg;
    private Image cuidadorimg;
    private Image perroimg;
    private Image rayosXmaleta;
    
    public CanvasAduana(int ancho, int alto, int nv) {
        super();
        this.setSize(ancho, alto);
        this.setBackground(Color.white);
        nViajeros = nv;

        viajeroMaletaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/Vmaleta.png"));
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(viajeroMaletaimg, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}
        
        viajeroManoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/Vmano.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(viajeroManoimg, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}

        cuidadorimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/cuidador.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(cuidadorimg, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}
        
        perroimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/perro.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(perroimg, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}
        
        rayosXmaleta = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/rayosXmaletas.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(rayosXmaleta, 0);
        try {
            dibu.waitForID(0);
        } catch (InterruptedException ex) {}
    }
    
    public void ocupacionRayosXmaleta(int donde, int id){
        ocupacionRayosXmaleta[donde] = id;
        repaint();
    }
    
    public void ocupacionRayosXmano(int id){
        ocupacionRayosXmano = id;
        repaint();
    }
    
    public void ocupacionPerros(int donde, int id, String tipoViajero){
        ocupacionPerros[donde] = id;
        if(donde == 0){
            perro_1_analizaA = tipoViajero;
        }
        else{
            perro_2_analizaA = tipoViajero;
        }
        repaint();
    }
    
    public void avisarCuidador(int posicion){
        posicionCuidador = posicion;
        repaint();
    }
    
    public void estadoColaMaleta(ArrayList<Float> vMaletas){
        this.vMaletas = vMaletas;
        repaint();
    }
    
    public void estadoColaMano(ArrayList<Float> vMano){
        this.vMano = vMano;
        repaint();
    }
    
    @Override
    public void update(Graphics g){
       paint(g); 
    }
    
    @Override
    public void paint(Graphics g){
        
        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics(); //Obtenemos la capacidad de pintar en la imagen
        
        Font ids = new Font("Consolas", Font.BOLD, 35);
        
        int baseXcolaMano = 700;
        int baseXcolaMaleta = 720;
        int baseYcolaMaleta = 490;
        int baseYcolaMano = 90;
        
        int baseXcolaManoString = 730;
        int baseYcolaManoString = 110;
        
        int baseXcolaMaletaString = 710;
        int baseYcolaMaletaString = 510;
           
        og.drawRect(700, 70, 1000, 250);
        og.drawRect(700, 470, 1000, 250);
        og.setFont(ids);
        
        og.drawImage(perroimg, 40, 190, 150, 150, null);
        og.drawImage(perroimg, 40, 510, 150, 150, null);
        
        og.drawImage(rayosXmaleta, 310, 60, 400, 300, null);
        og.drawImage(rayosXmaleta, 310, 340, 400, 300, null);
        og.drawImage(rayosXmaleta, 310, 570, 400, 300, null);
        
        og.drawString("EQUIPAJE DE MANO:", 700, 60);
        og.drawString("MALETAS:", 700, 460);
        
        //################ Representacion de las colas de espera (ArrayList) ###################
        //------MALETAS
        if (!vMaletas.isEmpty()) {
            for (int i = 0; i < vMaletas.size(); i++) {
                og.drawImage(viajeroMaletaimg, baseXcolaMaleta + i * 200, baseYcolaMaleta, 200, 200,null);                
                og.setColor(Color.blue);
                float id = vMaletas.get(i);
                og.drawString("" + (int) id, baseXcolaMaletaString + i * 200, baseYcolaMaletaString);
            }
        }
        
        //------MANOS
        if (!vMano.isEmpty()) {
            for (int i = 0; i < vMano.size(); i++) {
                og.drawImage(viajeroManoimg, baseXcolaMano + i * 200, baseYcolaMano, 200, 200,null);                
                og.setColor(Color.red);
                float id = vMano.get(i);
                og.drawString(" " + (int) id, baseXcolaManoString + i * 200, baseYcolaManoString);
            }
        }
        //######################################################################################
        
        
        //============== Representacion del la ocupacion de los rayosX ====================
        //MANO
        if(ocupacionRayosXmano != -1){
            og.drawImage(viajeroManoimg, 420, 90, 200, 200, null);
            og.setColor(Color.red);
            og.drawString(""+(int)ocupacionRayosXmano, 450, 110);
        }
        
        //MALETA
        if(ocupacionRayosXmaleta[0] != -1){
            og.drawImage(viajeroMaletaimg, 450, 400, 150, 150, null);
            og.setColor(Color.blue);
            og.drawString(""+ocupacionRayosXmaleta[0], 440, 415); 
             
        }
        if(ocupacionRayosXmaleta[1] != -1){
            og.drawImage(viajeroMaletaimg, 450, 630, 150, 150, null);
            og.setColor(Color.blue);
            og.drawString(""+ocupacionRayosXmaleta[1], 440, 645);
        }
        //=================================================================================
        
        
        //<<<<<<<<<<<<<<<< Representacion del la ocupacion de los perros >>>>>>>>>>>>>>>>>>>>>>>
        if(ocupacionPerros[0] != -1){
             if(perro_1_analizaA.equals("Maleta")){
                 og.drawImage(viajeroMaletaimg, 180, 150, 200, 200, null);
                 og.setColor(Color.blue);
                 og.drawString(""+ocupacionPerros[0], 180, 160);
             }
             else{
                og.drawImage(viajeroManoimg, 150, 140, 200, 200, null);
                og.setColor(Color.red);
                og.drawString(""+ocupacionPerros[0], 180, 160);
             }
        }
        if(ocupacionPerros[1] != -1){
            if(perro_2_analizaA.equals("Maleta")){
                 og.drawImage(viajeroMaletaimg, 180, 470, 200, 200, null);
                 og.setColor(Color.blue);
                 og.drawString(""+ocupacionPerros[1], 180, 480);
             }
             else{
                og.drawImage(viajeroManoimg, 150, 460, 200, 200, null); //En el perro de arriba MANO
                og.setColor(Color.red);
                og.drawString(""+ocupacionPerros[1], 180, 480);
             }
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        
        //Representacion del cuidador de los perros
        if(posicionCuidador == 0){
            og.drawImage(cuidadorimg, 0, 125, 140, 220, null);    
        }
        if(posicionCuidador == 1){
            og.drawImage(cuidadorimg, 0, 445, 140, 220, null);
        }
        
        g.drawImage(img, 0, 0, null);
        
        /*================================= Dibujo de ejemplo ====================================
        //ViajeroMano
        g.drawImage(viajeroManoimg, 700, 90, 200, 200, null); //1er viajeroMano de la cola
        g.drawImage(viajeroManoimg, 900, 90, 200, 200, null); //el siguiente está a +200 en el eje X
        g.drawImage(viajeroManoimg, 1100, 90, 200, 200, null); 
        g.drawImage(viajeroManoimg, 1300, 90, 200, 200, null); 
        g.drawImage(viajeroManoimg, 1500, 90, 200, 200, null);
        g.setColor(Color.red); //Color para los viajeroMano
        g.drawString("1", 730, 110); //1er id viajeroMano
        g.drawString("2", 930, 110);
        
        //ViajerosMaleta
        g.drawImage(viajeroMaletaimg, 700, 490, 200, 200, null); //1er viajeroMaleta de la cola
        g.drawImage(viajeroMaletaimg, 900, 490, 200, 200, null);
        g.drawImage(viajeroMaletaimg, 1100, 490, 200, 200, null);
        g.drawImage(viajeroMaletaimg, 1300, 490, 200, 200, null);
        g.drawImage(viajeroMaletaimg, 1500, 490, 200, 200, null);
        g.setColor(Color.blue); //Color para los viajeroMaleta
        g.drawString("3", 710, 510); //1er id viajeroMaleta
        g.drawString("4", 910, 510);
        
        //Cuidador
        g.drawImage(cuidadorimg, 0, 125, 140, 220, null); //En el perro de arriba
        g.drawImage(cuidadorimg, 0, 445, 140, 220, null); //En el perro de abajo
        
        //RayosX Vmano
        g.drawImage(rayosXmaleta, 310, 60, 400, 300, null);
        
        //RayosX vMaleta
        g.drawImage(rayosXmaleta, 310, 340, 400, 300, null);
        g.drawImage(rayosXmaleta, 310, 570, 400, 300, null);
        
        //Atender viajeros en rayosX
        g.drawImage(viajeroManoimg, 420, 90, 200, 200, null);
        g.setColor(Color.red);
        g.drawString("5", 450, 110);
        
        g.drawImage(viajeroMaletaimg, 450, 400, 150, 150, null); //EN sus rayosX de arriba (cambiar resolucion del viajero a 150x150)
        g.drawImage(viajeroMaletaimg, 450, 630, 150, 150, null); //EN sus rayosX de abajo (cambiar resolucion del viajero a 150x150)
        g.setColor(Color.blue);
        g.drawString("6", 440, 415); 
        g.drawString("7", 440, 645); 
        
        //Viajero con el perro
        //g.drawImage(viajeroManoimg, 150, 140, 200, 200, null); //En el perro de arriba MANO
        //g.drawString("8", 180, 160); //Texto MANO arriba
        g.drawImage(viajeroMaletaimg, 180, 150, 200, 200, null); //En el perro de arriba MALETA
        g.drawString("8", 180, 160); //Texto MALETA arriba
        
        g.drawImage(viajeroManoimg, 150, 460, 200, 200, null); //En el perro de arriba MANO
        g.drawString("9", 180, 480); //Texto MANO abajo
        //g.drawImage(viajeroMaletaimg, 180, 470, 200, 200, null); //En el perro de abajo MALETA
        //g.drawString("10", 180, 480); //Texto MALETA arriba
        */
    }
}
