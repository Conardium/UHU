/*
 * Clase AlgoritmosOrdenacion que implementa los Algoritmos de Ordenación para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes métodos de Ordenación en vectores: 
 *	- Burbuja
 *	- Inserción
 *	- Selección.
 */

#include "AlgoritmosOrdenacion.h"

AlgoritmosOrdenacion :: AlgoritmosOrdenacion() {}
AlgoritmosOrdenacion :: ~AlgoritmosOrdenacion(){}

/*
 * método ordenaBurbuja, implementa el método de ordenación Burbuja.
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */
void AlgoritmosOrdenacion :: ordenaBurbuja(int v[], int size)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int aux;
	for (int i = size - 2;i >= 0;i--)
	{
		for (int j = 0; j <= i;j++)
		{
			if (v[j] > v[j + 1])
			{
				aux = v[j];
				v[j] = v[j + 1];
				v[j + 1] = aux;
			}
		}
	}
		
}


/*
 * método ordenaInsercion, implementa el método de ordenación por Inserción-
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */
void AlgoritmosOrdenacion :: ordenaInsercion(int v[], int size)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int x, j;
	for (int i = 1; i < size;i++)
	{
		x = v[i];
		j = i - 1;
		while (j >= 0 && x < v[j])
		{
			v[j + 1] = v[j];
			j--;
		}
		v[j + 1] = x;
	}
}


/*
 * método ordenaSeleccion, implementa el método de ordenación por Selección.
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */
void AlgoritmosOrdenacion :: ordenaSeleccion(int v[], int size)
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//	
	int posMin, aux;
	for (int i = 0; i < size - 1; i++)
	{
		posMin = i;
		for (int j = i + 1;j < size;j++)
		{
			if (v[j] < v[posMin])
				posMin = j;
		}
		aux = v[posMin];
		v[posMin] = v[i];
		v[i] = aux;
	}
}

//------------------------------------------------------------------------------------------------


/***** EXA JUN20 *****/
void AlgoritmosOrdenacion::ordenaJunio2020(int v[], int size)
{
	Junio2020(v, 1, size);
}


void AlgoritmosOrdenacion::Junio2020(int a[], int primero, int ultimo)
{
	int x, k;
	for (int i = primero+1; i < ultimo; i++)
	{
		x = a[i];
		/*Busqueda binaria de la posicion insercion*/
		k = buscarPosicion(a, primero, i-1, x);
		/*desplazamos a la derecha los elementos ordenados para insertar el nuevo*/
		for (int j = i-1; j >= k; j--)
			a[j + 1] = a[j];

		/*Insertar el nuevo*/
		a[k] = x;
	}
}


int AlgoritmosOrdenacion::buscarPosicion(int v[], int primero, int ultimo, int key)
{
	while (primero <= ultimo)
	{
		int mitad = (primero + ultimo) / 2;
		if (key < v[mitad])
			ultimo = mitad - 1;
		else
			primero = mitad + 1;
	}
	return primero;
}



