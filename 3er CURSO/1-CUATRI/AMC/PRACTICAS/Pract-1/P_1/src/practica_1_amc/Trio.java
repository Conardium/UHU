/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_amc;


public class Trio 
{
    private Punto p1, p2, p3, PuntoComun;
    private double dMin;
    
    public Trio(Punto p1, Punto p2, Punto p3)
    {
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        
        double d1, d2, d3; //Guarda las distancia de un punto con respecto a los otros dos
        
        d1=p1.distancia(p2) + p1.distancia(p3);
        d2=p2.distancia(p3) + p2.distancia(p1);
        d3=p3.distancia(p1) + p3.distancia(p2);
        
        //Buscamos cual de las tres puntos tiene una distancia menor con respecto a los otros dos
        //y ese será el punto comun a lo otros dos puntos
        if(d1<=d2 && d1<=d3)
        {
            dMin = d1;            
            PuntoComun = p1;
        }
        else if(d2<=d1 && d2<=d3)
        {
            dMin = d2;
            PuntoComun = p2;
        }
        else if(d3<=d1 && d3<=d2)
        {
            dMin = d3;
            PuntoComun = p3;
        }
    }
    
    public static Trio exhaustivo(Punto[] P, int i, int f) //fin = tamaño del array P
    {
        Trio sol = new Trio(P[0],P[1],P[2]), aux;
        for(int a1=i; a1 <= f-2; a1++)
        {
            for(int a2=a1+1; a2 <= f-1; a2++)
            {
                for(int a3=a2+1; a3 <= f; a3++)
                {
                    aux = new Trio(P[a1],P[a2],P[a3]);
                    if(aux.getDMin() < sol.getDMin()) //Compara si el aux es menor que sol, si es así sol=aux
                       sol = aux;
                }
            }
        }
        return sol;
    }
    
    public Punto getP1() { return p1;}
    
    public Punto getP2() { return p2;}
    
    public Punto getP3() { return p3;}
    
    public double getDMin() {return dMin;}
    
    public Punto getPuntoComun() {return PuntoComun;}
}
