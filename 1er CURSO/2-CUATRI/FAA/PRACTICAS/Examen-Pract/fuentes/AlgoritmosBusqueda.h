﻿/*
 * Clase AlgoritmosBusqueda que implementa los Algoritmos de Búsqueda para buscar un elemento en un vector de enteros.
 * Define las implementaciones iterativas de los siguientes métodos de búsqueda en vectores de enteros en orden creciente:
 *	- Secuencial
 *	- Binaria o dicotómica
 *  - Interpolacion
 */
#ifndef _BUSQUEDA
#define _BUSQUEDA
/*
 * declaración de la clase AlgoritmosBusqueda
 */
class AlgoritmosBusqueda
{  

public:
    AlgoritmosBusqueda();
    ~AlgoritmosBusqueda();
	
  /*
	 * Función busquedaSecuencialIt, implementa el método de búsqueda secuencial Iterativo
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
   int busquedaSecuencialIt(int v[], int size,int key);

	/*
	 * Función busquedaBinariaIt, implementa el método de búsqueda binaria Iterativa
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
   int busquedaBinariaIt(int v[], int size,int key);

	/*
	 * Función busquedaInterpolacionIt, implementa el método de búsqueda binaria Recursivo
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
   int busquedaInterpolacionIt(int v[], int size,int key);
   
};
#endif