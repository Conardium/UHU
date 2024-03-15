
package practica_8_pcd;

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
public class CanvasTienda extends Canvas{
    
    private Image bicicletaimg;
    private Image ciclistaimg;
    private Image clienteimg;
    private Image mecanicoimg;
    private Image vendedorimg;
    
    private ArrayList<Float> clientes = new ArrayList<Float>();
    private ArrayList<Float> ciclistas = new ArrayList<Float>();   
    private boolean dibujaClienteEnVendedor = false;
    private boolean dibujaClienteEnMecanico = false;
    private boolean dibujaCiclistaReparando = false;
    private int idCliente = -1, idRepara = -1;
    
    public CanvasTienda(int ancho, int alto){
        super();
        this.setSize(ancho, alto);
        this.setBackground(Color.GRAY);
        
        bicicletaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/bicicleta.png"));
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(bicicletaimg, 0);
        try { dibu.waitForID(0);} catch (InterruptedException ex) {}
        
        ciclistaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/ciclista.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(ciclistaimg, 0);
        try { dibu.waitForID(0);} catch (InterruptedException ex) {}
        
        clienteimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/cliente.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(clienteimg, 0);
        try { dibu.waitForID(0);} catch (InterruptedException ex) {}
        
        mecanicoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/mecanico.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(mecanicoimg, 0);
        try { dibu.waitForID(0);} catch (InterruptedException ex) {}
        
        vendedorimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/vendedor.png"));
        dibu = new MediaTracker(this);
        dibu.addImage(vendedorimg, 0);
        try { dibu.waitForID(0);} catch (InterruptedException ex) {}
    }
    
    //============= Metodos para la obtención de datos para el paint() ============
    public void repara(int id){
        idRepara = id;
        dibujaCiclistaReparando = true;
        repaint();
    }
    
    public void compra(String donde, int id){ 
        if(donde.equals("Vendedor")){
            dibujaClienteEnVendedor = true;
            idCliente = id;
        }
        else{
            idRepara = id;
            dibujaClienteEnMecanico = true;
        }
        repaint();
    }
    
    public void termina(String donde){
        if(donde.equals("Vendedor")){
            dibujaClienteEnVendedor = false;
        }
        else{ //En el mecanico
            if(dibujaClienteEnMecanico == true){
                dibujaClienteEnMecanico = false;
            }
            else{
                dibujaCiclistaReparando = false;
            }
        }
        repaint();
    }
    
    public void ActualizaColaClientes(ArrayList<Float> clientes){
        this.clientes = clientes;
        repaint();
    }
    
    public void ActualizaColaCiclistas(ArrayList<Float> ciclistas){
        this.ciclistas = ciclistas;
        repaint();
    }
    //=============================================================================
    
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    @Override
    public void paint(Graphics g){
        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();
        
        Font textos = new Font("Arial", Font.BOLD, 30);
        Font ids = new Font("Consolas", Font.BOLD, 24);
        
        og.setColor(Color.WHITE);
        og.fillRect(20, 20, 1240, 180); //Cola clientes
        og.fillRect(20, 550, 1240, 180); //Cola ciclistas
        og.fillRect(20, 225, 550, 300); //Zona del Mecanico
        og.fillRect(610, 225, 650, 300); //Zona del Vendedor  
        og.drawImage(mecanicoimg, 360, 290, 200, 220, null);
        og.drawImage(vendedorimg, 910, 270, 250, 250, null);
        og.drawImage(bicicletaimg, 860, 390, 150, 150, null);
        og.setColor(Color.blue.darker());
        og.setFont(textos);
        og.drawString("MECÁNICO", 380, 270);
        og.setColor(Color.green.darker());
        og.drawString("VENDEDOR", 950, 270);
        
        
        //************** Representación de las imagenes en la cola ***************
        if(!clientes.isEmpty()){
            og.setFont(ids);
            for (int i = 0; i < clientes.size(); i++) {
                og.drawImage(clienteimg, 50 + i * 120, 30, 70, 170, null);                
                og.setColor(Color.red);
                float id = clientes.get(i);
                og.drawString("" + (int)id, 28 + i * 120, 60);
            }
        }
        
        if(!ciclistas.isEmpty()){
            og.setFont(ids);
            for (int i = 0; i < ciclistas.size(); i++) {
                og.drawImage(ciclistaimg, 30 + i * 120, 560, 120, 150, null);                
                og.setColor(Color.blue);
                float id = ciclistas.get(i);
                og.drawString("" + (int)id, 40 + i * 120, 590);
            }
        }
        //************************************************************************
        
        
        //######### Representación de las imagenes en el MECANICO y en el VENDEDOR #########
        if(dibujaClienteEnVendedor){
            og.setFont(ids);
            og.drawImage(clienteimg, 750, 280, 100, 230, null);                
            og.setColor(Color.red);
            og.drawString("" + idCliente, 728, 310);
        }
        
        if(dibujaClienteEnMecanico){
            og.setFont(ids);
            og.drawImage(clienteimg, 150, 280, 100, 230, null);                
            og.setColor(Color.red);
            og.drawString("" + idRepara, 128, 310);
        }
        
        if(dibujaCiclistaReparando){
            og.setFont(ids);
            og.drawImage(ciclistaimg, 110, 280, 200, 220, null);                 
            og.setColor(Color.blue);
            og.drawString("" + idRepara, 138, 310);
        }
        //##################################################################################
        
        
        //Textos aclarativos de las colas
        og.setColor(Color.black);
        og.fillRect(20, 160, 140, 40);
        og.fillRect(20, 690, 140, 40);
        og.setFont(textos);
        og.setColor(Color.yellow);
        og.drawString("Clientes", 30, 190);
        og.drawString("Ciclistas", 30, 720);
        g.drawImage(img, 0, 0, null);
    }
}
