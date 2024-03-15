#include "TestOrdenacion.h"
#include "Constantes.h"
#include "TestBusqueda.h"
#include <iostream>

using namespace std;


char MenuPrincipal() 
{
	char op;
	system("cls");
	cout << "\n\n";
	cout << "    *** FAA. Examen Junio2020. Curso 19/20 *** \n\n";
	cout << "                                                Ismael Da Palma Fernandez \n\n\n";
	cout << "                         *** MENU PRINCIPAL ***                           \n\n\n";
	cout << "                         1.- MENU ORDENACION (EX JUN2020)\n\n";
	cout << "                         2.- MENU BUSQUEDA\n\n";
	cout << "                         0.- Salir\n\n";
	cout << "                         ----------\n\n";
	cout << "                         Elige opcion: ";
	cin >> op;
	return op;
}

// ##################################     ORDENACION     ############################################
char MenuOrdenacion()																				 //
{																								     //
	char op1;																						 //
	system("cls");																					 //
	cout << "\n\n";																				     //
	cout << "	*** MENU ORDENACION ***\n\n\n";														 //
	cout << "	1.- Probar los metodos de ordenacion\n\n";								             //
	cout << "	2.- Obtener el caso medio de un metodo de ordenacion\n\n";							 //
	cout << "	3.- Comparar dos metodos\n\n";														 //
	cout << "	0.- Volver al menu principal\n\n";													 //
	cout << "	-----------\n\n";																	 //
	cout << "	Elige opcion: ";																     //
	cin >> op1;																						 //
	return op1;																						 //
}																									 //
																									 //
int ComparacionOrdenacion()																			 //
{																									 //
	int metodo;																						 //
	system("cls");																				     //
	cout << "\n\n";																					 //
	cout << "	*** Elige metodo a estudiar ***\n\n\n";							                     //
	cout << "	0: Burbuja\n\n";																	 //
	cout << "	1: Insercion\n\n";																	 //
	cout << "	2: Seleccion\n\n";	

	/***** EXA JUN20 *****/
	cout << "	3: Junio2020\n\n";                                                                   //
	cout << "	---------\n\n";																		 //
	cout << "	Elige opcion: ";																	 //
	cin >> metodo;																					 //
	return metodo;																					 //
}																									 //																									 
// ###################################################################################################




// ##################################     BUSQUEDA     ##############################################
char MenuBusqueda()																					 //
{																									 //
	char op2;																					     //
	system("cls");																					 //
	cout << "\n\n";																				     //
	cout << "	*** MENU BUSQUEDA ***\n\n\n";														 //
	cout << "	1.- Probar los metodos de busqueda\n\n";								             //
	cout << "	2.- Obtener el caso medio de un metodo de busqueda\n\n";							 //
	cout << "	3.- Comparar dos metodos\n\n";														 //
	cout << "	4.- Comparar todos\n\n";                                                             //
	cout << "	0.- Volver al menu principal\n\n";													 //
	cout << "	-----------\n\n";																	 //
	cout << "	Elige opcion: ";																     //
	cin >> op2;																						 //
	return op2;																						 //
}																									 //
																									 //
char ComparacionBusqueda()																			 //
{																									 //
	int metodo;																						 //
	system("cls");																				     //
	cout << "\n\n";																					 //
	cout << "	*** Elige metodo a estudiar ***\n\n\n";											     //
	cout << "	0: Busqueda Secuencial Iterativa\n\n";												 //
	cout << "	1: Busqueda Binaria Iterativa\n\n";												     //
	cout << "	2: Busqueda de Interpolacion Iterativa\n\n";										 //
	cout << "	--------------\n\n";												                 //
	cout << "	Elige opcion: ";																	 //
	cin >> metodo;																					 //
	return metodo;																					 //
}																									 //
// ###################################################################################################






/** Programa principal **/
int main()
{
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//

	TestOrdenacion testO;
	TestBusqueda testB;
	char op, op1, op2;
	int metodo, metodo1, metodo2;

	do
	{
		op = MenuPrincipal();
		switch (op)
		{
		case '1': // ORDENACION
			do
			{
				op1 = MenuOrdenacion();
				switch (op1)
				{
				case '1': //Probar metodos Ordenacion
					system("cls");
					testO.comprobarMetodosOrdenacion();
					break;

				case '2': //Caso medio
					metodo = ComparacionOrdenacion();
					system("cls");
					testO.casoMedio(metodo);
					break;

				case '3': //Comparar 2
					metodo1 = ComparacionOrdenacion();
					metodo2 = ComparacionOrdenacion();
					system("cls");
					testO.comparar(metodo1, metodo2);
					break;

				case '0': break; //Salir
				}
			} while (op1 != '0');
			break;
        //-------------------------------
       //---------------
		case '2': // BUSQUEDA
			do
			{
				op2 = MenuBusqueda();
				switch (op2)
				{
				case '1': //Probar metodos Busqueda
					system("cls");
					testB.comprobarMetodosBusqueda();
					break;

				case '2': //Caso medio
					metodo = ComparacionBusqueda();
					system("cls");
					testB.casoMedio(metodo);
					break;

				case '3': //Comparar 2 metodos
					metodo1 = ComparacionBusqueda();
					metodo2 = ComparacionBusqueda();
					system("cls");
					testB.comparar(metodo1, metodo2);
					break;

				case '4': //Comparar todos
					system("cls");
					testB.compararTodos();
					break;

				case '0': break; //Salir
				}
			} while (op2 !='0');
			break;
       //---------------
		case '0': break; //Finalizar el .exe
			
		}
	} while (op != '0');

	return 0;
};