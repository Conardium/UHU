/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 * 4. Comparar todos los algoritmos de búsqueda implementados.
 */
#ifndef _TEST_BUSQUEDA
#define _TEST_BUSQUEDA

#include <vector>
#include <string>
#include <iostream>
using namespace std;
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//

class TestBusqueda
{
	vector<string>nombreAlgoritmo;
public:
    
	TestBusqueda();
	~TestBusqueda();

	static double BuscarArrayDeInt(int v[], int size, int metodo, int &pos, int key);

	void comprobarMetodosBusqueda();

	void casoMedio(int metodo);

	void comparar(int metodo1, int metodo2);

	void compararTodos();
};

#endif