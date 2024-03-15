/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

/**
 *Clase que implementa los métodos de las transiciones sin simbolo del AFND
 * @author Ismael Da Palma Fernández
 */
public class Transicionλ 
{
    private String est1;
    private String[] est2;

    /**
     * Constructor parametrizado de la clase
     * @param est1
     * @param est2 
     */
    public Transicionλ(String est1, String[] est2) 
    {
        this.est1 = est1;
        this.est2 = est2;
    }

    /**
     * Metodo que devuelve el estado origen de la transicion
     * @return est1 
     */
    public String getEst1() {return est1;}

    /**
     * Metodo que devuelve el/los estados destino de la transición
     * @return est2 
     */
    public String[] getEst2() {return est2;}
}
