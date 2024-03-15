
#include <iostream>

#include "AlgoritmosBusqueda.h"
using namespace std;

/*
 * Implementación de los métodos de la clase AlgoritmosBusqueda
 */
AlgoritmosBusqueda::AlgoritmosBusqueda() { }
AlgoritmosBusqueda::~AlgoritmosBusqueda() { }


int AlgoritmosBusqueda::busquedaSecuencialIt(int v[], int size,int key)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int i = 0;

	while (v[i] != key && i <= size)
		i++;

	if (v[i] == key)
		return i;
	else
		return -1;
}


int AlgoritmosBusqueda::busquedaBinariaIt(int v[], int size,int key){
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	bool encontrado = false;
	int mitad, primero, ultimo;
	primero = 0;
	ultimo = size;

	while (primero <= ultimo && !encontrado){
		mitad = (primero + ultimo) / 2;
		if (key == v[mitad])
			encontrado = true;
		else{
			if (key < v[mitad])
				ultimo = mitad - 1;
			else{
				if (key > v[mitad])
					primero = mitad + 1;
			}
		}
	}
	if (encontrado)
		return mitad;
	else
		return -1;
}


int AlgoritmosBusqueda::busquedaInterpolacionIt(int v[], int size,int key){
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int p, primero, ultimo;
	primero = 0;
	ultimo = size - 1;

	while ((v[ultimo] >= key) && (v[primero] < key)){
		p = primero + ((ultimo - primero) * (key - v[primero]) / (v[ultimo] - v[primero]));
		if (key > v[p])
			primero = p + 1;
		else{
			if (key < v[p])
				ultimo = p - 1;
			else
				primero = p;
		}
	}
	if (v[primero] == key)
		return primero;
	else
		return -1;
}

