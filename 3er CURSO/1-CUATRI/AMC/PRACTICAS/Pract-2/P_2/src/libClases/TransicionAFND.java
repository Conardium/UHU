/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

/**
 *Clase que implementa los metodos de las transiciones con simbolo del AFND
 * @author Ismael Da Palma Fernández
 */
public class TransicionAFND 
{
    private String est1;
    private String[] est2;
    private char simbolo;
    
    /**
     * Constructor parametrizado del autómata
     * @param e1
     * @param simb
     * @param e2 
     */
    public TransicionAFND(String e1, char simb, String[] e2)
    {
        est1 = e1;
        simbolo = simb;
        est2 = new String[e2.length];
        
        for(int i = 0; i<e2.length; i++)
            est2[i] = e2[i];
    }

    /**
     * Metodo que devuelve el estado origen de la transicion
     * @return est1 
     */
    public String getEs1() {return est1;}

    /**
     * Metodo que devuelve el/los estados destino de la transición
     * @return est2
     */
    public String[] getEs2() {return est2;}

    /**
     * Metodo que devuelve el simbolo de la transición
     * @return simbolo
     */
    public char getSimbolo() {return simbolo;}
}
