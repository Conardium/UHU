/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

/**
 *Clase que implementa los métodos de una transición para el AFD
 * @author Ismael Da Palma Fernández
 */
public class TransicionAFD 
{
    private String es1;
    private String es2;
    private char simbolo;

    /**
     * Constructor de la clase sin parametros
     */
    public TransicionAFD() 
    {
        es1 = null;
        es2 = null;
        simbolo = ' ';
    }
    
    /**
     * Constructor parametrizado de la clase
     * @param e1
     * @param simb
     * @param e2 
     */
    public TransicionAFD(String e1, char simb, String e2)
    {
        es1 = e1;
        es2 = e2;
        simbolo = simb;
    }

    /**
     * Metodo que devuelve el estado origen de la transición
     * @return es1
     */
    public String getEs1() {return es1;}

    /**
     * Metodo que devuelve el estado destino de la transición
     * @return es2
     */
    public String getEs2() {return es2;}

    /**
     * Metodo que devuelve el símbolo de la transición
     * @return simbolo
     */
    public char getSimbolo() {return simbolo;}
}
