/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author ismae
 */
public class Palabra {
    
    private ArrayList<String> palabrasCorrectas;
    private ArrayList<String> palabrasDesordenadas;
    
    public Palabra(){
        palabrasCorrectas = new ArrayList();
        palabrasCorrectas.add("PERSONA");
        palabrasCorrectas.add("CIUDAD");
        palabrasCorrectas.add("HELADO");
        palabrasCorrectas.add("MOCHILA");
        palabrasCorrectas.add("ORDENADOR");
        
        palabrasDesordenadas = new ArrayList();
        palabrasDesordenadas.add("APROSNE");
        palabrasDesordenadas.add("DIADUC");
        palabrasDesordenadas.add("LHAODE");
        palabrasDesordenadas.add("MHLIACO");
        palabrasDesordenadas.add("RODENORAD");
    }
    
    public boolean ComprobarPalabra(String intento){
        boolean estado = false;
        for (int i = 0; i < 5; i++) {
            if(palabrasCorrectas.get(i).equals(intento)){
                estado = true;
            }
        }
        return estado;
    }

    public String getPalabraCorrecta(int i) {
        return palabrasCorrectas.get(i);
    }
    
    public String getPalabraDesordenada(int i) {
        return palabrasDesordenadas.get(i);
    }  
}
