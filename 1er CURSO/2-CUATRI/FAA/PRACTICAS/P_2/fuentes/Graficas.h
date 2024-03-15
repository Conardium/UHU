/*
 * Clase Graficas, contiene m�todos para guardar las gr�ficas de los resultados, es decir, crea
 * los ficheros por lo lotes (comandos) para generar los ficheros gr�ficos que corresponda. 
 */
#ifndef _GRAFICA
#define _GRAFICA
#include <string>
#include <iostream>
using namespace std;

class Graficas
{
public:
/*
 * M�todo generarGraficaMEDIO, genera el fichero de comandos para GNUPlot y dibuja la gr�fica 
 * del caso medio de un m�todo de ordenaci�n y su ajuste a la funci�n correspondiente.
 * param metodo: metodo de ordenacion.
 * param orden: Orden del metodo de ordenacion.
 */
	void 	generarGraficaMEDIO(string metodo,int orden);
	 
/*
 * M�todo generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer m�todo de ordenaci�n 
 * param nombre2: es el nombre del fichero de datos del segundo m�todo de ordenaci�n 
 */
	void generarGraficaCMP(string metodo1,string metodo2);
}; 
#endif