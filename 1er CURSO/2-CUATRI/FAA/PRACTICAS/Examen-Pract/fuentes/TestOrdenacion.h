/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los m�todos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un m�todo de ordenaci�n,
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente 
 * 3. Comparar el coste temporal de dos de los m�todos de ordenaci�n 
 *    Burbuja, Inserci�n, y Selecci�n, permitiendo guardar los datos e imprimir la 
 *    gr�fica correspondiente.
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