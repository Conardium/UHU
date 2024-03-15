/*
 * La clase Test contiene los metodos para:
 * 1. Comprobar que los m�todos de b�squeda funcionan adecuadamente.
 * 2. Calcular la eficiencia para los casos de un m�todo de b�squeda,
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente
 * 3. Comparar el coste temporal de los casos del m�todo de b�squeda
 *    secuencial, permitiendo guardar los datos e imprimir la
 *    gr�fica correspondiente.
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
	* Permite las opciones de crear el fichero de datos y la gr�fica correspondiente.
	* param metodo: caso del algoritmo a estudiar.
	*/
	void costeCasoTeorico(int numerocaso);
	/*
	* Compara los casos de un algoritmo.
	* Permite las opciones de crear el fichero de datos y la gr�fica correspondiente.
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
