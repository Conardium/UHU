/*
 * La clase Test contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para los casos de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente
 * 3. Comparar el coste temporal de los casos del método de búsqueda
 *    secuencial, permitiendo guardar los datos e imprimir la
 *    gráfica correspondiente.
 */
#ifndef _TEST_ALGORITMO
#define _TEST_TESTALGORITMO

#include <string>
#include <vector>
#include <iostream>
using namespace std;

class TestAlgoritmo
{
	vector<string> nombreAlgoritmoCaso;
public:

	TestAlgoritmo();
	~TestAlgoritmo();
	/* Comprueba que el algoritmo funciona */
	void comprobarAlgoritmo();



	/*
	* Calcula el coste TEORICO de los casos de un Algoritmo,
	* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
	* param metodo: caso del algoritmo a estudiar.
	*/
	void costeCasoTeorico(int numerocaso);
	/*
	* Compara los casos de un algoritmo.
	* Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
	* param metodo1: Primer algoritmo a comparar
	* param metodo2: Segundo algoritmo a comparar
		* param metodo3: Segundo algoritmo a comparar
	*/
	void compararTeorico(int metodo1, int metodo2, int metodo3);


	void costeCasoEmpirico(int numerocaso);
	void compararEmpirico(int metodo1, int metodo2, int metodo3);
};

void visualizarResultados(int caso[], int nCasos, bool medicion);

#endif
