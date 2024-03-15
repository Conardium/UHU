/*
 * Clase Graficas, contiene métodos para guardar las gráficas de los resultados, es decir, crea
 * los ficheros por lo lotes (comandos) para generar los ficheros gráficos que corresponda.
 */
#include "Graficas.h"
#include "Constantes.h"
#include <fstream>
#include <iostream>
using namespace std;

 /*
  * Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot y dibuja la gráfica
  * del caso medio de un método de ordenación y su ajuste a la función correspondiente.
  * param metodo: metodo de ordenacion.
  * param orden: Orden del metodo de ordenacion.
  */
void Graficas::generarGraficaMEDIO(string nombre_metodo,int orden)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream fichero("CmdMedio.gpl");
	fichero << "#ESTE ES UN SCRIPT DE GNUPLOT PARA EL ESTUDIO DE 1 METODO\n";

	fichero << "set title \" " << nombre_metodo << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n"
		<< "Complejidad(x) = ";

	switch (orden) {
	case CUADRADO:	fichero << "a*x*x + b*x + c\n";	break;
	case NlogN:		fichero << "a*log(a)\n"; break;
	case N:         fichero << "a*x + b\n"; break;
	case logN:      fichero << "a+b*(log(x)/log(2))\n"; break;
	case loglogN:   fichero << "a*(log(log(x)))\n"; break;
	}
	fichero << "fit Complejidad(x) \"" << nombre_metodo << ".dat\" using 1:2 via ";
	switch (orden) {
	case CUADRADO:	fichero << "a,b,c\n"; break;
	case NlogN:		fichero << "a\n";	break;
	case N:         fichero << "a,b\n"; break;
	case logN:      fichero << "a,b\n"; break;
	case loglogN:   fichero << "a\n"; break;
	}

	fichero << "plot \"" << nombre_metodo << ".dat\" using 1:2 title \"" << nombre_metodo << "\", Complejidad(x) \n"
		<< "\n"
		<< "set terminal pdf\n"
		<< "set output \"" << nombre_metodo << ".pdf\"\n"
		<< "replot\n"
		<< "#pause -1 \"Pulsa Enter para continuar...\"\n";

	fichero.close();
}	

/*
 * Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación 
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación 
 */
void Graficas::generarGraficaCMP(string nombre1,string nombre2)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream fichero("CmdComparar.gpl");
	fichero << "#ESTE ES UN SCRIP DE GNUPLOT PARA LA COMPARACION DE 2 METODOS\n";

	fichero << "set title \"Comparacion tiempos " << nombre1 << " y " << nombre2 << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n";

	fichero << "plot \"" << nombre1<< ".dat\" using 1:2 with lines title \"" << nombre1 << "\", \"" << nombre2 << ".dat\" using 1:2 with lines title \"" << nombre2 << "\"\n"
		<< "\n"
		<< "set terminal pdf\n"
		<< "set output \"" << nombre1 << nombre2 << ".pdf\"\n"
		<< "replot\n"
		<< "#pause -1 \"Pulsa Enter para continuar...\"\n";

	fichero.close();
}

void Graficas::generarGraficaTodos(vector<string>nombres)
{
	ofstream fichero("CmdTodos.gpl");
	fichero << "#ESTE ES UN SCRIP DE GNUPLOT PARA LA COMPARACION ENTRE TODOS LOS METODOS (BUSQUEDA)\n";

	fichero << "set title \"Comparacion tiempos -SecuecialIt-BinariaIt-InterpolacionIt\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n";
	fichero << "plot ";
	for (int algoritmo = 0; algoritmo < nombres.size(); algoritmo++)
		fichero << "\"SecuencialBinarioInterpolacionIt.dat\" using 1:"<< (algoritmo + 2) << " with lines title \"" << nombres[algoritmo] << "\"" << (algoritmo == nombres.size() - 1 ? "\n" : ", ");
	fichero << "\n"
	    << "set terminal pdf\n"
		<< "set output \"ComparativaTodos.pdf\"\n"
		<< "replot\n"
		<< "#pause -1 \"Pulsa Enter para continuar...\"\n";

}