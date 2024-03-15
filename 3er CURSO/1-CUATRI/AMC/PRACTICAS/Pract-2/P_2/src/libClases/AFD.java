/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que se encarga de implementar los metodos el autómata finito determinista (AFD)
 * @author Ismael Da Palma Fernández
 */
public class AFD implements Proceso, Cloneable
{
    private String[] estadosFinales; //indica cuales son los estados Finales
    private String estadoInicial;
    private static int numEstadosF;
    private List<TransicionAFD> transiciones; //indica la lista de transiciones del AFD
    private List<String> estados; //Guardamos todos los estados que tiene del AFD
 
    /**
     * Constructor de la clase que se encarga de inicializar los atributos privados
     */
    public AFD()
    {
        numEstadosF = 0;
        estadosFinales = new String[numEstadosF];
        transiciones = new ArrayList<TransicionAFD>();
        estados = new ArrayList<>();
        
        estadoInicial = "0"; //Estado inicial por defecto
    }
    
    /**
     * Devuleve las transiciones del AFD
     * @return transiciones
     */
    public List<TransicionAFD> getTransiciones(){return transiciones;}
    
    /**
     * Metodo que devuelve los estados finales del AFD
     * @return estadosFinales
     */
    public String[] getEstadosFinales() {return estadosFinales;}
    
    /**
     * Metodo que establece el estado pasado por parametro como inicial
     * @param estado 
     */
    public void setEstadoInicial(String estado){estadoInicial = estado;}
    
    /**
     * Metodo que pasado un estado por parametro, lo añade a la lsita de estados del automata
     * @param estado 
     */
    public void addEstado(String estado)
    {
        estados.add(estado);
    }
    
    /**
     * Metodo que devuelve el estado inicial del AFD
     * @return estadoInicial
     */
    public String getEstadoInicial(){return estadoInicial;}
    
    /**
     * Metodo que establece el estado pasado por parametro como estado final.
     * Antes de eso, se comprobará que el estado no esté ya insertado en los estados finales
     * @param estado 
     */
    public void setEstadosFinales(String estado)
    {
        boolean existe = false;
        int i = 0;
        //Vemos si el estado final que intentamos insertar ya está o no
        while(!existe && i < estadosFinales.length)
        {
            if(estadosFinales[i].equals(estado))
                existe = true;
            else
                i++;
        }
        if(existe)
        {
            JOptionPane.showMessageDialog(null, "El estado que intentas insertar ya es final", "Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            numEstadosF++;
            String[] aux = new String[numEstadosF];
            for(int j = 0; j < estadosFinales.length; j++)
                aux[j] = estadosFinales[j];
        
            aux[numEstadosF-1] = estado;
            estadosFinales = aux;
        }
    }
    
    /**
     * Metodo que dado un estado, se encarga de buscar si este existe en los estados del AFD.
     * True si el estado existe
     * @param estado
     * @return existe
     */
    public boolean EstadosFinales(String estado) 
    {
        boolean existe = false;
        int i = 0;
        
        while(!existe && i < transiciones.size()) 
        {
            if(transiciones.get(i).getEs1().equals(estado))
                existe = true;
            else if(transiciones.get(i).getEs2().equals(estado))
                existe = true;
            i++;
        }
        return existe;
    }
    
    /**
     * Metodo que agrega una transición a la lista de transiciones del AFD
     * @param Eorigen
     * @param simbolo
     * @param Edestino 
     */
    public void agregarTransicion(String Eorigen, char simbolo, String Edestino)
    {
        boolean existe = false;
        int i = 0;
        
        //Primero buscamos si existe o no la transición
        while(!existe && i < transiciones.size()) 
        {
            if(transiciones.get(i).getEs1().equals(Eorigen) && transiciones.get(i).getSimbolo() == simbolo && transiciones.get(i).getEs2().equals(Edestino)
                    || transiciones.get(i).getEs1().equals(Eorigen) && transiciones.get(i).getSimbolo() == simbolo)
                existe = true;
            else
                i++;
        }
        
        //Si no existe esa transicion, la añadimos
        if(!existe)
        {
            TransicionAFD transicion = new TransicionAFD(Eorigen, simbolo, Edestino);
            transiciones.add(transicion);
        }
        else
            JOptionPane.showMessageDialog(null, "La transicion que intentas insertar ya existe.\nComprueba las transiciones del automata", "Error",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Metodo que devuelve el estado al que ha avanzado el automata cuando le pasas
     * un estado y un simbolo
     * @param estado
     * @param simbolo
     * @return resultado
     */
    public String transicion(String estado, char simbolo) //Devuelve el resultado de la transicion con el estado y simbolo pasado por parametro 
    {                                                     
        String resultado = ""; //Inicializacion
        
        for(int i = 0; i<transiciones.size(); i++) //Recorremos las transiciones del automata para ver si alguna concuerda
        {
            if(transiciones.get(i).getEs1().equals(estado))
                if(transiciones.get(i).getSimbolo() == simbolo)
                    resultado = transiciones.get(i).getEs2();
        }
        return resultado;
    }
    
    /**
     * Metodo que comprueba que el estado pasado por parametro es un estado final. True si lo es
     * @param estado
     * @return EsFinal 
     */
    @Override
    public boolean esFinal(String estado)
    {
        boolean EsFinal = false;
        int i = 0;
        
        while(!EsFinal && i<estadosFinales.length)
        {
            if(estado.equals(estadosFinales[i]))
                EsFinal = true;
            else
                i++;
        }
        return EsFinal;
    }
    
    /**
     * Metodo que se encarga de reconocer si la cadena se acepta o se rechaza.
     * La cadena se acepta si el estado en el que finaliza el autómata es final.
     * @param cadena
     * @return esFinal(estado)
     */
    @Override
    public boolean reconocer(String cadena)  //Reconoce si al finalizar las transiciones, el automata se encuentra en un estado final
    {
        char[] simbolo = cadena.toCharArray();
        String estado = estadoInicial ; //Inicializamos el primer estado con el inicial
        for(int i=0; i<simbolo.length; i++) 
            estado = transicion(estado,simbolo[i]);
        
        return esFinal(estado);
    }
    
    /**
     * Metodo que sirve para clonar un AFD
     * @return afd
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Object clone() throws CloneNotSupportedException 
    {
        AFD afd = null;
        try
        {
            afd = (AFD)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            System.out.println("Error al clonar el automata AFD");
        }
        afd.estadosFinales = this.estadosFinales;
        afd.transiciones = this.transiciones;
        
        return afd;
    }
    
    /**
     * Metodo que devuelve un String con toda la información del autómata
     * @return automata
     */
    @Override
    public String toString() 
    {
        String automata = "\n--- DATOS DEL AUTOMATA ---\nEstados finales:\n";
        //estadosFinales = new String[numEstadosF];
        
        for(int i = 0; i < estadosFinales.length; i++)
        {
            automata += estadosFinales[i];
            automata += "\n";
        }
        
        automata += "\nTransiciones:\n";
        
        for(int i = 0; i < transiciones.size(); i++)
        {
            automata += transiciones.get(i).getEs1();
            automata += " -> ";
            automata += transiciones.get(i).getSimbolo();
            automata += " -> ";
            automata += transiciones.get(i).getEs2();
            automata += "\n";
        }
        return automata;
    }   
}
