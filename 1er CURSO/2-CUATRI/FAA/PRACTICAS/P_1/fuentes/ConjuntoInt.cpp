#include <iostream>
using namespace std;
#include <ctime>  //para time
#include <cstdlib> // for srand, rand
#include "ConjuntoInt.h"

ConjuntoInt::ConjuntoInt(int max)
{
	tamano = max;
	datos = new int[max];
}
ConjuntoInt::~ConjuntoInt()
{
	delete[] datos;
}
void ConjuntoInt::vaciar()
{
	tamano = 0;
}
int* ConjuntoInt::getDatos()
{
	int* v = datos;
	for (int i = 0; i < tamano;i++) {
		v[i] = datos[i];
	}
	return v;
}

void ConjuntoInt::GeneraVector(int tam)
{
	tamano = tam;
	srand((unsigned)time(NULL)); //srand(time(0));
	for (int i = 0; i < tamano; i++) {
		datos[i] = rand() % 10000; //genera un número aleatorio entre 1 y 999
	}
}
int ConjuntoInt::generaKey()
{
	return datos[rand() % tamano];
	//return datos[tamano / 2];
	//return rand()%10000; //genera un número aleatorio entre 1 y 999
}
void ConjuntoInt::escribe()
{
	for (int i = 0; i < tamano; i++)
		cout << datos[i] << (i < tamano - 1 ? ", " : "\n");
}
int ConjuntoInt::getPrimero() {
	return datos[0];
}
int ConjuntoInt::busquedaSecuencial(int valor)
{
	int i = 0;
	while (datos[i] != valor && i < tamano)
	{
		i = i + 1;
	}
	if (datos[i] == valor)
		return i;	 // se encuentra el elemento en el array
	else
		return -1;  // no se encuentra el elemento en el array
}


//Esta parte es mía

int BusquedaSecuencial(int T[], int n, int valor) {
	int i = 0;
	while (i < n && T[i] != valor)
		i++;
	if (i < n && T[i] == valor)
		return i;
	return -1;
}