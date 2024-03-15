#include "TestOrdenacion.h"
#include "Constantes.h"
#include <iostream>

using namespace std;


int menuAlgoritmos() {
	char c;

	system("cls");
	do {
		cout << "\n\n"
			<< "           ***  Seleccione un metodo de ordenacion ***\n\n"
			<< "                 0: Burbuja\n\n"
			<< "                 1: Interseccion\n\n"
			<< "                 2: Seleccion\n\n"
			<< "                 ----------\n\n"
			<< "                 Elige opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '0' && c != '1' && c != '2');

	switch (c) {
	case '0':
		c = 1;
		break;
	case '1':
		c = 2;
		break;
	case '2':
		c = 3;
		break;
	}

	return c;
}
int MenuPrincipal() {
	char c;
	system("cls");

	do {
		cout << "\n\n";
		cout << "                                        *** FAA. Practica 2. Curso 19/20 *** \n\n"
			<< "                                                    Ismael Da Palma Fernandez \n\n\n"
			<< "                                *** MENU PRINCIPAL ***                         \n\n"
			<< "               *** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION ***        \n\n\n"
			<< "                         1.- Probar los metodos de ordenacion\n\n"
			<< "                         2.- Obtener el caso medio de un metodo de ordenacion\n\n"
			<< "                         3.- Comparar dos metodos\n\n"
			<< "                         0.- Salir\n\n"
			<< "                         ----------\n\n"
			<< "                         Elige opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '2' && c != '3' && c != '4' && c != '0');


	switch (c) {
	case '1':
		c = 1;
		break;
	case '2':
		c = menuAlgoritmos() + 1;
		break;
	case '3':
		c = (menuAlgoritmos() * 10) + menuAlgoritmos();
		break;
	case '4':
		c = 5;
		break;
	case '0':
		c = 0;
		break;
	}

	cout << "\n\n";
	return c;
}

int getConstante(int numero) {
	switch (numero) {
	case 1: return BURBUJA;
	case 2: return INSERCION;
	case 3: return SELECCION;
	}
}

/** Programa principal **/
int main()
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	TestOrdenacion test;

	int opt;

	do {
		opt = MenuPrincipal();
		if (opt > 10) {//Comparar dos
			test.comparar(getConstante(opt / 10), getConstante(opt % 10));
		}
		else {
			switch (opt) {
			case 1:	//Medir
				test.comprobarMetodosOrdenacion();
				break;
			case 2:
			case 3:
			case 4:	//Probar
				test.casoMedio(getConstante(opt - 1));
				break;
			}
		}
	} while (opt != 0);

	system("taskkill /F /T /IM wgnuplot.exe");
	return 0;
};