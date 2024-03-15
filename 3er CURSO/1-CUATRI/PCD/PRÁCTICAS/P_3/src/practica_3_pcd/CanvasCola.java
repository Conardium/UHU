/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_3_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author ismae
 */
public class CanvasCola extends Canvas {
    
    private Object[] cola;
    private String vaciaMsg = "";
    private String llenaMsg = "";
    int head, tail, capacidad;
    int numElementos;
    
    public CanvasCola(int ancho, int alto, int capacidad) {
        super();
        this.setSize(ancho, alto);
        this.setBackground(Color.LIGHT_GRAY);
        this.capacidad=capacidad;
    }
    
    public void avisa(String msg){
        if(msg.equals("Vacia"))
        {
            vaciaMsg="COLA VACIA";
            repaint();         
        }
        else if(msg.equals("Llena"))
        {
            llenaMsg="COLA LLENA";
            repaint();  
        }
        else
        {
            llenaMsg = "";
            vaciaMsg = "";
            repaint();
        }
    }
    
    public void representa(Object[] cola, int tail, int head, int num){
        this.cola = cola; //Pasamos la cola que estamos tratando
        this.tail=tail;
        this.head=head;
        this.numElementos=num;
        repaint();
    }    
    
    /*
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    */
    
    @Override
    public void paint(Graphics g){
        
        // FONTS
        Font f1 = new Font("Cooper Black", Font.BOLD, 25);
        Font f2 = new Font("Arial", Font.BOLD, 25);
        Font f3 = new Font("Arial Black", Font.BOLD, 25);
        Font f4 = new Font("Arial", Font.BOLD, 15);
        
        //Título del canvas
        g.setColor(Color.BLACK);
        g.setFont(f1);
        g.drawString("Uso de Cola", 210, 40);
        
        //Forma de la cola
        g.fillRect(90, 150, 380, 76);
        g.setColor(Color.WHITE);
        g.fillRect(93, 153, 90, 70);
        g.fillRect(187, 153, 90, 70);
        g.fillRect(281, 153, 90, 70);
        g.fillRect(375, 153, 90, 70);
        
        //Textos de las excepciones
        g.setFont(f2);
        g.setColor(Color.BLACK);
        g.drawString(vaciaMsg, 50, 350);
        g.drawString(llenaMsg, 375, 350);
        
        
        //Posiciones de los numeros de la cola
        g.setFont(f3);
        int base = 120;
        
        if ((head == tail) && (numElementos == capacidad)) //Dibujar la cola llena
            for (int i = 0; i < capacidad; i++)
                g.drawString(" " + cola[i], base + i * 95, 196);
        
        if((head == tail) && (numElementos == 0)) //Dibujar la cola vacia
            for(int i = 0; i < capacidad; i++)
                g.drawString(" ", base + i * 95, 196);
        
        if(head < tail) {  //head antes del tail
            for (int i = 0; i < head; i++) //Pone vacio los elementos antes del head
                g.drawString(" ", base + i * 95, 196);
            
            for (int i = head; i < tail; i++) //Rellena los valores entre head y tail
                g.drawString(" " + cola[i], base + i * 95, 196);
            
            for (int i = tail; i < capacidad; i++) //Pone vacio los elementos despues del tail
                g.drawString(" ", base + i * 95, 196);
        }
        
        if(head > tail) {  //tail antes del head
            for (int i = 0; i < tail; i++)
                g.drawString(" "+ cola[i], base + i * 95, 196);
            
            for (int i = tail; i < head; i++) 
                g.drawString(" ", base + i * 95, 196);
            
            for (int i = head; i < capacidad; i++) 
                g.drawString(" "+ cola[i], base + i * 95, 196);
        }
        
        //Header y tail
        g.setFont(f4);
        g.drawString("Header", 115+(head*95), 100);
        g.drawString("Tail", 125+(tail*95), 285);
        g.setColor(Color.red); //Header
        g.fillOval(124+(head*95), 105, 30, 30);
        g.setColor(Color.blue); //Tail
        g.fillOval(124+(tail*95), 240, 30, 30);
        
        // Numeros de ejemplo
        // g.drawString("1", 120, 196); //1º casilla
        // g.drawString("46", 215, 196); //2º casilla
        // g.drawString("10", 310, 196); //3º casilla
        // g.drawString("100", 405, 196); //4º casilla
    } 
}
