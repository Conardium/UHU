/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    guardando los datos y permitiendo imprimir la gráfica correspondiente 
 * 3. Comparar el coste temporal de dos de los métodos de ordenación 
 *    burbuja, inserción, y selección, guardando los datos y permitiendo imprimir la gráfica correspondiente.
 */
#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;


TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Burbuja");
	nombreAlgoritmo.push_back("Insercion");
	nombreAlgoritmo.push_back("Seleccion");
} 
TestOrdenacion::~TestOrdenacion(){}

/*
 * Ordena un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de ordenacion a utilizar
 * return Tiempo empleado en la ordenación (en milisegundos)
 */
double TestOrdenacion::ordenarArrayDeInt(int v[],int size,int metodo) 
{
	AlgoritmosOrdenacion estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	/* Invoca al método de ordenación elegido */
	switch (metodo){
		case BURBUJA: estrategia.ordenaBurbuja(v, size);
			break;
		case INSERCION: estrategia.ordenaInsercion(v, size);
			break;
		case SELECCION: estrategia.ordenaSeleccion(v, size);
			break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;   
}

/*
 * Comprueba los metodos de ordenacion
 */
void TestOrdenacion::comprobarMetodosOrdenacion()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	system("cls"); 
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		ConjuntoInt *v= new ConjuntoInt(talla); 
		v->GeneraVector(talla);
		cout <<endl<<endl<< "vector inicial para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		double secs=ordenarArrayDeInt(v->getDatos(),talla,metodo);
		cout<<endl<<endl<<"Array ordenado con metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		cout<<endl;
		v->vaciar();
		delete v;
		system("pause");
		system("cls");
	} 
	system("cls");
}
   

/*
 * Calcula el caso medio de un metodo de ordenacion.
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de ordenacion a estudiar.
 */
void TestOrdenacion::casoMedio(int metodo)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	cout << "\t *** Ordenacion por " << nombreAlgoritmo[metodo] << " ***\n\n";
	cout << "\t Tiempos de ejecucion promedio\n\n\n";
	cout<< "\t       TALLA \t       TIEMPO (ms)\n";
	ofstream fichero(nombreAlgoritmo[metodo] + ".dat");
	if (fichero.fail())
		cout << "\nError, no se pudo abrir el fichero";
	
	double tiempo;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt *v = new ConjuntoInt(talla);
		tiempo = 0;
		for (int i = 0; i < NUMREPETICIONES;i++)
		{
			v->GeneraVector(talla);
			tiempo += ordenarArrayDeInt(v->getDatos(), talla, metodo);
			v->vaciar();
		}
		tiempo /= NUMREPETICIONES;
		//tiempo *= 1000.0;  //CALCULO DEL TIEMPO TEORICO
		delete v;

		//Muestra los datos por pantalla.
		cout << "\t\t" << talla << "\t     " << setw(10) << fixed << setprecision(4) << tiempo << "\n";
		fichero << talla << "\t" << tiempo << "\n";
	}
	fichero.close();
	cout << "\n\nDatos guardados en el fichero " << nombreAlgoritmo[metodo] + ".dat";

	//Genera la grafica.
	char opt;
	cout << "\n\nGenerar grafica de resultados? (s/n): ";
	cin >> opt;
	opt = toupper(opt);
	if (opt == 'S')
	{
		Graficas G;
		G.generarGraficaMEDIO(nombreAlgoritmo[metodo], CUADRADO);
		cout << "\nGrafica guardada en el fichero " << nombreAlgoritmo[metodo] << ".pdf\n\n";
		system("start CmdMedio.gpl");
	}
	else
		cout << "No se muestra la grafica";
	system("pause");
}



/*
 * Compara dos metodos de ordenacion. 
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2) 
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	cout << "\t\t\t *** Ordenacion por " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << " ***\n\n";
	cout << "\t\t\t\t Tiempos de ejecucion promedio\n\n";
	cout << "\t             \t\t       \t      TIEMPO (ms)\n";
	cout<< "\t       TALLA(n) \t\t" << nombreAlgoritmo[metodo1] << "\t\t" << nombreAlgoritmo[metodo2] << "\n";
	ofstream fichero(nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");
	if(fichero.fail())
		cout << "\nError, no se pudo abrir el fichero";

	double tiempo1, tiempo2;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt *v = new ConjuntoInt(talla);
		tiempo1 = tiempo2 = 0;
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			tiempo1 += ordenarArrayDeInt(v->getDatos(), talla, metodo1);
			v->GeneraVector(talla);
			tiempo2 += ordenarArrayDeInt(v->getDatos(), talla, metodo2);
			v->vaciar();
		}
		tiempo1 /= NUMREPETICIONES;
		tiempo2 /= NUMREPETICIONES;
		delete v;

		//Muestra los datos por pantalla.
		cout.precision(4);
		cout << "\t\t" << talla << "\t\t    " << setw(10) << fixed << setprecision(4) << tiempo1
			<< "\t     " << setw(10) << fixed << setprecision(4) << tiempo2 << "\n";
		fichero << talla << "\t" << tiempo1 << "\t" << tiempo2 << "\n";
	}
	fichero.close();
	cout << "\n\nDatos guardados en el fichero " << nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat";

	//Genera la grafica.
	char opt;
	cout << "\n\nGenerar grafica de resultados? (s/n): ";
	cin >> opt;
	opt = toupper(opt);
	if (opt == 'S')
	{
		Graficas G;
		G.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
		cout << "\nGrafica guardada en el fichero " << nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] << ".pdf";
		system("start CmdComparar.gpl");
	}
	else
		cout << "No se muestra la grafica";
	system("pause");
}	
