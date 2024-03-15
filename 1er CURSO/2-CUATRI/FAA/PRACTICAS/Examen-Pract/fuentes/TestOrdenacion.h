/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente 
 * 3. Comparar el coste temporal de dos de los métodos de ordenación 
 *    Burbuja, Inserción, y Selección, permitiendo guardar los datos e imprimir la 
 *    gráfica correspondiente.
 */
#ifndef _TEST_ORDENACION
#define _TEST_ORDENACION

#include <vector>
#include <string>
#include <iostream>
using namespace std;

class TestOrdenacion 
{
	vector<string> nombreAlgoritmo;
public:
	TestOrdenacion();
	~TestOrdenacion();
  
	static double ordenarArrayDeInt(int v[],int size,int metodo);
 
	void comprobarMetodosOrdenacion();
        
	void comparar(int metodo1, int metodo2);

	void casoMedio(int metodo);

};
#endif