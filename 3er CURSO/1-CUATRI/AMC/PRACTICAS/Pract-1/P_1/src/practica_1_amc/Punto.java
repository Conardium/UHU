/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_amc;

import static java.lang.Math.*;


public class Punto //implements ComparableParametro
{
    private double x,y;
    
    public Punto(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX() { return x; }
    
    public double getY() { return y; }
    
    public double distancia(Punto p1){ return sqrt(pow((this.x-p1.x),2) + pow(this.y-p1.y,2)); } //Distancia entre un punto y otro
    
    public boolean comparar(Punto p)
    {
        boolean menor = false;
        if(this.x < p.x)
            menor = true;
        else if(this.x == p.x && this.y < p.y)
            menor = true;
        
        return menor;
    }
    
    public String toString()
    {
        String punto = "(" + x + ", " + y + ")";
        return punto;
    }
}
