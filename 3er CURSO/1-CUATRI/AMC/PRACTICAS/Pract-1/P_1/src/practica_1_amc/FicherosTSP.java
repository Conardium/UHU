/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_amc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class FicherosTSP 
{
    public static Punto[] LeerFichero(String fichero) 
    {
        Punto[] puntos = null;
        int i = 0;
        try 
        {
            BufferedReader lectura = new BufferedReader(new FileReader(fichero));
            String line;
            boolean cordSection = false; //Estara a true si se encuentra en la parte del fichero
                                         //en las que esten las coordenadas.
            while ((line = lectura.readLine()) != null) 
            {
                if (!line.equals("EOF") && !line.equals("")) 
                {
                    if (cordSection) //Si estamos en la zona de las coordenadas, las guardamos en un array Punto
                    {
                        String[] parts = line.split(" ");
                        puntos[i++] = new Punto(Double.parseDouble(parts[1].trim()), Double.parseDouble(parts[2].trim()));
                    } 
                    else 
                    {
                        if (line.equals("NODE_COORD_SECTION"))//Despues de esta linea vienen las coordenadas
                            cordSection = true;
                        
                        else if (line.contains("DIMENSION"))//Si esta en la linea de dimension, indica cuantos puntos hay en el fichero
                        {
                            String[] parts = line.split(":");
                            puntos = new Punto[Integer.parseInt(parts[1].trim())];
                        }
                    }
                }
            }
            lectura.close();
            return puntos;
        } 
        catch (FileNotFoundException ex) 
        {
            ex.printStackTrace();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        return puntos;
    }
    
    public static void guardarTour(String archivo, Punto[] puntos, int solucion, String[] caminoMin, int[] costes) 
    {
        try 
        {
            Path path = Paths.get(archivo);
            BufferedWriter escribir = new BufferedWriter(new FileWriter(path.toFile()));
            escribir.write("NAME : " + path.getFileName() + "\n");
            escribir.write("TYPE : TOUR\n");
            escribir.write("DIMENSION : " + puntos.length + "\n");
            escribir.write("SOLUTION : " + solucion + "\n");
            escribir.write("TOUR_SECTION\n");
            
            for (int i =1; i<caminoMin.length; i++) //Escribimos el coste y el caminoMin de cada punto
                escribir.write(costes[i] + " - " + caminoMin[i] + "\n"); //Coloca en el fichero el coste del punto iesimo y su camino

            escribir.write("-1\nEOF\n");
            escribir.close();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
}
