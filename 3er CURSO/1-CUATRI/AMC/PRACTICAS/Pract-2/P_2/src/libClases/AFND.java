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
 * Clase que se encarga de implementar los metodos el autómata finito no determinista (AFND)
 * @author Ismael Da Palma Fernández
 */
public class AFND implements Proceso, Cloneable
{
    private  String[] estadosFinales; //indica cuales son los estados Finales
    private static int numEstadosF;
    private String estadoInicial;
    private List<TransicionAFND> transiciones; //indica la lista de transiciones del AFND
    private List<Transicionλ> transicionesλ; //indica la lista de transiciones λ del AFND
 
    
    /**
     * Constructor de la clase, inicializa las listas de transiciones, el numero de estados finales a 0 y establece el estado inicial por defecto al "0"
     */
    public AFND()
    {
        numEstadosF = 0;
        estadosFinales = new String[numEstadosF];
        transiciones = new ArrayList<TransicionAFND>();
        transicionesλ = new ArrayList<Transicionλ>();
        
        estadoInicial = "0";
    }
   
    
    
    //----------------------------------------------------------------------
    //---                 OPERACIONES CON TRANSICIONES                   ---
    //----------------------------------------------------------------------
    
    /**
     * Metodo que agrega una transición a la lista de transiciones del AFND
     * @param e1
     * @param simbolo
     * @param e2 
     */
    public void agregarTransicion(String e1, char simbolo, String [] e2)
    {
        TransicionAFND transicion = new TransicionAFND(e1, simbolo, e2);
        transiciones.add(transicion);
    }
      
    /**
     * Metodo que agrega una transiciónλ a la lista de transicionesλ del AFND
     * @param e1
     * @param e2 
     */
    public void agregarTransicionλ(String e1, String[] e2)
    {
        Transicionλ transλ = new Transicionλ(e1, e2);
        transicionesλ.add(transλ);
    }
    
    
    /**
     * Metodo que dado un macroestado y un simbolo por parametro, devuelve el macroestado
     * al que ha avanzado el autómata
     * @param macroestado
     * @param simbolo
     * @return resultado
     */
    public String[] transicion(String[] macroestado, char simbolo)
    {
        //Inicializacion
        String[] resultado = new String[0]; //Guarda los resultados de todos los macroestados
        String[] aux = null; //Guarda los resultados del macro estado actual
        String[] aux2 = null; //Para añadir los estados de aux a resultado
        
        if(!macroestado[0].equals("")) //Si el macroestado contiene algo, continuamos
        {
            for(int i = 0; i < transiciones.size(); i++)
            {
                for(int j = 0; j < macroestado.length; j++)
                {
                    if(transiciones.get(i).getEs1().equals(macroestado[j]))
                    {
                        if(transiciones.get(i).getSimbolo() == simbolo)
                        {
                            aux = new String[transiciones.get(i).getEs2().length];
                            aux = transiciones.get(i).getEs2();
                            
                            aux2 = new String[resultado.length + aux.length];
                            for(int a1 = 0; a1 < resultado.length; a1++)
                                aux2[a1] = resultado[a1];
                            for(int a2 = 0; a2 < aux.length; a2++)
                                aux2[resultado.length + a2] = aux[a2];
                            
                            resultado = new String[aux2.length];
                            resultado = aux2;
                        }
                    }
                }
            }
        }
        return resultado;
    }
    
    /**
     * Metodo que dado un estado, devuelve los estados a los que puede ir con el
     * simbolo lambda
     * @param estado
     * @return resultado
     */
    public String[] transicionλ(String estado)
    {
        String[] resultado = null;
        
        for(int i = 0; i < transicionesλ.size(); i++)
        {
            if(!transicionesλ.get(i).getEst2().equals("")) //Si la transacion lambda 0  no es nula, seguimos buscando
            {
                if(transicionesλ.get(i).getEst1().equals(estado)) //Vemos si corresponde el estado origen
                {
                    resultado = new String[transicionesλ.get(i).getEst2().length]; //Insertamos los estados destino de transicionλ i-esimo en el resultado
                    resultado = transicionesλ.get(i).getEst2();
                }
            }   
        }
        return resultado;
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------
    //--------------------------------------

    /**
     * Metodo que devuelve las transiciones del AFND
     * @return transiciones
     */
    public List<TransicionAFND> getTransiciones() {return transiciones;}

    /**
     * Metodo que devuelve las transicionesλ del AFND
     * @return transicionesλ
     */
    public List<Transicionλ> getTransicionesλ() {return transicionesλ;}

    /**
     * Metodo que devuelve el estado Inicial del AFND
     * @return estadoInicial
     */
    public String getEstadoInicial() {return estadoInicial;}

    /**
     * Metodo que devuelve los estados finales del AFND
     * @return estadosFinales
     */
    public String[] getEstadosFinales() {return estadosFinales;}
    
    /**
     * Metodo que establece el estado que se pasa por parametro como inicial, antes de esto, se comprobará si el estado que se quiere
     * establecer como inicial existe en alguna transición o no; Si existe se cambiará el estado inicial
     * @param estado 
     */
    public void setEstadoInicial(String estado)
    {
        //Primero buscamos si existe ese estado en alguna transicion
        boolean encontrado = buscarEstado(estado);
        
        if(!encontrado) //Si no existe no lo insertamos
            JOptionPane.showMessageDialog(null, "El estado no existe en ninguna transicion", "Error",JOptionPane.ERROR_MESSAGE);
        else //Si existe entonces actualizamos el estadoInicial del automata
            estadoInicial = estado;
    }
    
    /**
     * Metodo que establece lo estados finales del AFND, primero comprobará que los estados que se quieren hacer finales existen en alguna
     * transicion del AFND
     * @param estado 
     */
    public void setEstadosFinales(String estado)
    {
        //Primero vemos si el estado ya está en estados finales
        boolean existe = false;
        int i = 0;
        while(!existe && i < estadosFinales.length)
        {
            if(estadosFinales[i].equals(estado))
                existe = true;
            i++;
        }
        
        if(existe)
            JOptionPane.showMessageDialog(null, "El o algunos estados que intentas insertar ya son finales", "Error",JOptionPane.ERROR_MESSAGE);
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
     * Metodo que busca el estado pasado por parametro en las transiciones del autómata,
     * devolverá true si el estado existe en alguna transición
     * @param estado
     * @return encontrado
     */
    public boolean buscarEstado(String estado)
    {
        int i = 0;
        boolean encontrado = false;
        while(!encontrado && i < transiciones.size())
        {
            if(transiciones.get(i).getEs1().equals(estado))
                encontrado = true;
            i++;
        }
        
        i = 0;
        while(!encontrado && i < transiciones.size())
        {
            int j = 0;
            while(!encontrado && j < transiciones.get(i).getEs2().length)
            {
                if(transiciones.get(i).getEs2()[j].equals(estado))
                    encontrado = true;
                j++;
            }
            i++;
        }
        return encontrado;
    }
            
    /**
     * Metodo que comprueba si el estado pasado por parametro es final o no, true si el estado es final
     * @param estado
     * @return esFinal
     */
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
     * Metodo que dado un conjunto de estados, busca alguno que sea final, si encuentra uno o más devuelve true
     * @param macroestado
     * @return esFinal
     */
    public boolean esFinal(String[] macroestado)
    {
        boolean EsFinal = false;
        int i = 0;
        while(!EsFinal && i < estadosFinales.length)
        {
            int j = 0;
            while(!EsFinal && j < macroestado.length)
            {
                if(estadosFinales[i].equals(macroestado[j]))
                    EsFinal = true;
                j++;
            }
            i++;
        }
        return EsFinal;
    }
    
    /**
     * Metodo recursivo que realiza la lambda-clausura del macroestado pasado por parametro.
     * La lambda-clausura guarda el conjunto de estados a los que se puede avanzar con el simbolo lambda
     * incluyendo los estados que contiene el macroestado.
     * @param macroestado
     * @return resultado
     */           
    public String[] λ_clausura(String[] macroestado) 
    {
        String[] resultado = new String[0];
        String[] aux2 = null;
        String[] aux1 = null;
        String[] auxM = new String[0]; //String auxiliar para guardar todas las transiciones lambda de todos los macroestados
        
        if(!macroestado[0].equals("")) //Guardamos primero como resultado los macroestados que nos pasan
        {
               resultado = new String[macroestado.length];
               resultado = macroestado;
        }
        
        for(int i = 0; i < macroestado.length; i++) //Recogemos todos las transiciones lamda de los macroestados
        {
            String[] macroAux = transicionλ(macroestado[i]);
            if(!macroAux[0].equals("")) //Si transicionλ(macroAux) no es nulo, entramos
            {
                aux1 = new String[macroAux.length + auxM.length]; //Vamos a guardar en aux1 los valores que ya tenia auxM y los nuevos que se van a insertar de macroAux
            
                for(int a1 = 0; a1 < auxM.length; a1++)
                    aux1[a1] = auxM[a1];
                for(int a2 = 0; a2 < macroAux.length; a2++)
                    aux1[auxM.length + a2] = macroAux[a2];
            
                //Finalmente actualizamos auxM para que contenga lo mismo que aux1
                auxM = new String[aux1.length];
                auxM = aux1; 
            }        
        }
        //En auxM tenemos todas las transiciones lambda de todos los estados del macroestado
        
        if(auxM.length > 0)
        {
            for(int i = 0; i < auxM.length; i++)
            {
                boolean encontrado = false;
                int j = 0;
                //Buscamos en el array de resultados si existe el estado i-esimo de auxM
                while(!encontrado && j < resultado.length)
                {
                    if(resultado[j].equals(auxM[i]))
                        encontrado = true;
                    j++;
                }
                if(!encontrado) //Si el estado i-esimo de auxM no existe en el resultado, lo añadimos al resultado y llamamos al λ_clausura de auxM
                {
                    aux2 = new String[resultado.length + 1];
                    
                    for(int a1 = 0; a1 < resultado.length; a1++)
                        aux2[a1] = resultado[a1];                   
                    aux2[resultado.length] = auxM[i];
                    
                    resultado = new String[aux2.length];
                    resultado = aux2;
                    
                    String[] resultRecursivo = λ_clausura(auxM);
                }
            }
        }       
        return resultado;
    }
    
    /**
     * Metodo que reconoce la cadena pasada por parametro como aceptada o rechazada.
     * Para que la cadena sea aceptada, el macroestado debe de contener algun estado final.
     * @param cadena
     * @return esFinal(macroestado)
     */
    @Override
    public boolean reconocer(String cadena) 
    {
        char[] simbolo = cadena.toCharArray();
        String[] estado = { estadoInicial };  //Comenzamos el metodo con el estado inicial
        String[] macroestado = λ_clausura(estado);
        
        for(int i=0; i<simbolo.length; i++)
        {
            macroestado = transicion(macroestado, simbolo[i]);
        }
            
        
        return esFinal(macroestado);
    }

    /**
     * Metodo que devuelve una cadena que guarda los distintos datos del autómata
     * @return datos
     */
    @Override
    public String toString()
    {
        String datos = "";
        datos += "Estado Inicial: ";
        datos += estadoInicial;
        
        datos += "\n\n";
        datos += "Estados Finales:";
        for(int i = 0; i < estadosFinales.length-1; i++)
            datos += estadosFinales[i] + ", ";
        datos += estadosFinales[estadosFinales.length] + "\n\n";
        
        datos += "-- Transiciones --\n";
        for(int i = 0; i < transiciones.size(); i++)
        {
            datos += transiciones.get(i).getEs1() + " -> " + transiciones.get(i).getSimbolo() + " -> [";
            for(int j = 0; j < transiciones.get(i).getEs2().length-1; j++)
                datos += transiciones.get(i).getEs2()[j] + ", ";
            datos += transiciones.get(i).getEs2()[transiciones.get(i).getEs2().length] + "]\n";
        }
        
        datos += "\n-- Transicionesλ --\n";
        for(int i = 0; i < transicionesλ.size(); i++)
        {
            datos += transicionesλ.get(i).getEst1() + " -> [";
            for(int j = 0; j < transicionesλ.get(i).getEst2().length-1; j++)
                datos += transicionesλ.get(i).getEst2()[j] + ", ";
            datos += transicionesλ.get(i).getEst2()[transicionesλ.get(i).getEst2().length] + "]\n";
        }
        datos += "\n";
        
        return datos;
    }

    /**
     * Metodo que sirve para clonar un AFND
     * @return afnd
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Object clone() throws CloneNotSupportedException 
    {
        AFND afnd = null;
        try
        {
            afnd = (AFND)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            System.out.println("Error al clonar el automata AFND");
        }
        afnd.numEstadosF = this.numEstadosF;
        afnd.estadosFinales = new String[afnd.numEstadosF];
        afnd.estadosFinales = this.estadosFinales;
        afnd.estadoInicial = this.estadoInicial;
        afnd.transiciones = this.transiciones;
        afnd.transicionesλ = this.transicionesλ;
        
        return afnd;
    }
}
