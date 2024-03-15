/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 * 4. Comparar todos los algoritmos de búsqueda implementados.
 */
#include "AlgoritmosOrdenacion.h"
#include "AlgoritmosBusqueda.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>

#include "TestBusqueda.h"
using namespace std;

//** ------------------------------------- ESCRIBIR PARA COMPLETAR LA PRACTICA ------------------------------------- **//
TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("SecuencialIt");  //LINEAL -> O(n)
	nombreAlgoritmo.push_back("BinarioIt");     //LOGARITMO de N
	nombreAlgoritmo.push_back("InterpolacionIt"); //LOGnLOGn
} 


TestBusqueda::~TestBusqueda()
{
}


double TestBusqueda::BuscarArrayDeInt(int v[], int size, int metodo, int &pos, int key)
{                                                                                 
	AlgoritmosBusqueda strategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	/* Invoca al método de busqueda elegido */
	switch (metodo) 
	{
	case SECUENCIAL: pos = strategia.busquedaSecuencialIt(v, size, key);
		break;
	case BINARIO: pos = strategia.busquedaBinariaIt(v, size, key);
		break;
	case INTERPOLACION: pos = strategia.busquedaInterpolacionIt(v, size, key);
		break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;
}


// COMPROBAR EL FUNCIONAMIENTO DE LOS METODOS DE BUSQUEDA
void TestBusqueda::comprobarMetodosBusqueda()
{
	TestOrdenacion Ordenacion;
	int talla, key, pos;
	cout << endl << endl << "Introduce la talla: ";
	cin >> talla;
	system("cls");
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		ConjuntoInt *v = new ConjuntoInt(talla);
		v->GeneraVector(talla);
		cout << endl << endl << "Vector para el metodo " << nombreAlgoritmo[metodo] << ":" << endl << endl;
		//Ordenamos el vector
		Ordenacion.ordenarArrayDeInt(v->getDatos(), talla, BURBUJA);
		v->escribe();
		cout << "\n\n	Introduce la clave a buscar: ";
		cin >> key;
		double secs = BuscarArrayDeInt(v->getDatos(), talla, metodo, pos, key);
		
		if (pos == -1)
			cout << "\n\nNo se ha encontrado el elemento en el vector\n\n";
		else
			cout << "	->  Posicion de " << key << " buscado con el metodo " << nombreAlgoritmo[metodo] << ": " << pos;
		cout << endl;
		v->vaciar();
		delete v;
		system("pause");
		system("cls");
	}
	system("cls");
}

// CASO MEDIO DE UN METODO DE BUSQUEDA
void TestBusqueda::casoMedio(int metodo)
{
	TestOrdenacion test;
	cout << "\tBusqueda " << nombreAlgoritmo[metodo] << ". Tiempos de ejecucion promedio\n\n\n";
	cout << "\t       Talla \t       TIEMPO (mseg)\n";
	ofstream fichero(nombreAlgoritmo[metodo] + ".dat");
	if (fichero.fail())
		cout << "\nError, no se pudo abrir el fichero";

	double tiempo;
	int pos;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt *v = new ConjuntoInt(talla);
		tiempo = 0;
		for (int i = 0; i < NUMREPETICIONES;i++)

		{
			v->GeneraVector(talla);
			tiempo += BuscarArrayDeInt(v->getDatos(), talla, metodo, pos, v->GeneraClave());
			v->vaciar();
		}
		tiempo /= NUMREPETICIONES;
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
		switch (metodo)
		{
		case SECUENCIAL: G.generarGraficaMEDIO(nombreAlgoritmo[metodo], N);
			break;

		case BINARIO: G.generarGraficaMEDIO(nombreAlgoritmo[metodo], logN);
			break;

		case INTERPOLACION: G.generarGraficaMEDIO(nombreAlgoritmo[metodo], loglogN);
			break;
		}
		cout << "\nGrafica guardada en el fichero " << nombreAlgoritmo[metodo] << ".pdf\n\n";
		system("start CmdMedio.gpl");
	}
	else
		cout << "No se muestra la grafica";
	system("pause");
}

// COMPARACION DE DOS METODOS DE BUSQUEDA
void TestBusqueda::comparar(int metodo1, int metodo2)
{
	cout << "\t\t\t *** Busqueda " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << " ***\n\n";
	cout << "\t             \t\t       \t      TIEMPO (ms)\n";
	cout << "\t       TALLA(n) \t\t" << nombreAlgoritmo[metodo1] << "\t" << nombreAlgoritmo[metodo2] << "\n";
	ofstream fichero(nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");
	if (fichero.fail())
		cout << "\nError, no se pudo abrir el fichero";

	double tiempo1, tiempo2;
	int pos;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt *v = new ConjuntoInt(talla);
		tiempo1 = tiempo2 = 0;
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			tiempo1 += BuscarArrayDeInt(v->getDatos(), talla, metodo1, pos, v->GeneraClave());
			v->GeneraVector(talla);
			tiempo2 += BuscarArrayDeInt(v->getDatos(), talla, metodo2, pos, v->GeneraClave());
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
		cout << "\n\n";
		system("start CmdComparar.gpl");
	}
	else
		cout << "No se muestra la grafica";
	system("pause");
}

void TestBusqueda::compararTodos()
{
	cout << "\n\n\t *** COMPARACION DE TODOS LOS METODOS DE BUSQUEDA ***\n\n\n";

	ofstream fichero("SecuencialBinarioInterpolacionIt.dat");
	if (fichero.fail())
		cout << "No se ha podido abrir/crear el archivo";

	double *tiempo = new double[nombreAlgoritmo.size()];
	int pos;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt *v = new ConjuntoInt(talla);
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
		{
			tiempo[algoritmo] = 0;
			for (int i = 0; i < NUMREPETICIONES; i++)
			{
				v->GeneraVector(talla);
				tiempo[algoritmo] += BuscarArrayDeInt(v->getDatos(), talla, algoritmo, pos, v->GeneraClave());
				v->vaciar();
			}
			tiempo[algoritmo] /= NUMREPETICIONES;
		}
		delete v;
	}
	fichero.close();

	//Generar Grafica de resultados
		Graficas G;
		G.generarGraficaTodos(nombreAlgoritmo);
		cout << "\nGrafica generada";
		cout << "\n\n";
		system("start CmdTodos.gpl");

	system("pause");
}



	
