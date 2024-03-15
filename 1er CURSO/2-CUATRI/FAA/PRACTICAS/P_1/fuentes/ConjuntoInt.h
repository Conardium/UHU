#ifndef _LIB_CONJUNTOINT
#define _LIB_CONJUNTOINT

#include <iostream>
using namespace std;

/////////// Declaraci�n de la clase conjuntoInt /////////////

class ConjuntoInt
{
private:
	int tamano;
	int *datos;
public:
	ConjuntoInt(int max = 0);
	~ConjuntoInt();
	void vaciar();
	void GeneraVector(int tam);
	int generaKey();
	int* getDatos();
	void escribe();
	int getPrimero();
	/*
	 * Funci�n busquedaSecuencial, implementa el m�todo de b�squeda secuencial Iterativo
	 * para el array de enteros "datos"
	 * tama�o del array "tamano"
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array, si no se encuentra return -1
	 */
	int busquedaSecuencial(int key);
};

#endif

