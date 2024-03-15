
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author ismae
 */
public class MiCanvas extends Canvas {

    private int lectores[] = {0, 0, 0, 0, 0, 0};
    private int escritores[] = {0, 0, 0};

    public MiCanvas(int ancho, int alto) {
        super();
        setSize(ancho, alto);
        setBackground(Color.lightGray);
    }

    public void setLector(int id, int estado){
        lectores[id] = estado;
        repaint();
    }
    
    public void setEscritor(int id, int estado){
        escritores[id] = estado;
        repaint();
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();

        og.drawString("Practica 10", 20, 20);
        og.setColor(Color.black);
        og.drawLine(0, 220, 599, 220);
        
        //Pintamos los estados 0
        og.setColor(Color.blue);
        for (int i = 0; i < 6; i++) {
            if(lectores[i] == 0)
                og.fillOval(50+50*i, 100, 20, 20);       
        }
        
        og.setColor(Color.red);
        for (int i = 0; i < 3; i++) {
            if(escritores[i] == 0)
                og.fillOval(400+50*i, 100, 20, 20);       
        }
        
        //Pintamos los estados 1
        og.setColor(Color.blue);
        for (int i = 0; i < 6; i++) {
            if(lectores[i] == 1)
                og.fillOval(50+50*i, 200, 20, 20);       
        }
        
        og.setColor(Color.red);
        for (int i = 0; i < 3; i++) {
            if(escritores[i] == 1)
                og.fillOval(400+50*i, 200, 20, 20);       
        }
        
        //Pintamos los estados 2
        og.setColor(Color.blue);
        for (int i = 0; i < 6; i++) {
            if(lectores[i] == 2)
                og.fillOval(50+50*i, 300, 20, 20);       
        }
        
        og.setColor(Color.red);
        for (int i = 0; i < 3; i++) {
            if(escritores[i] == 2)
                og.fillOval(400+50*i, 300, 20, 20);       
        }
        
        //Pintamos los estados 3
        og.setColor(Color.yellow);
        for (int i = 0; i < 6; i++) {
            if(lectores[i] == 2)
                og.fillOval(50+50*i, 300, 20, 20);       
        }
        
        //Pintamos los estados 4
        og.setColor(Color.red);
        for (int i = 0; i < 6; i++) {
            if(lectores[i] == 2)
                og.fillOval(50+50*i, 300, 20, 20);       
        }
         
        g.drawImage(img, 0, 0, null);
    }
}
