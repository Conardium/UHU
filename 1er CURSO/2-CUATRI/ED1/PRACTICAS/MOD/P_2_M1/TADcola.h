#ifndef TADCOLA_H_INCLUDED
#define TADCOLA_H_INCLUDED

#include "TTipos.h"

class cola
{
 TPedido *elementos; //elementos de la cola
 int inicio, fin; //principio y fin de la cola
 int Tama; //Capacidad de la tabla
 int ne; //Nº de elementos

public:
 cola(); // constructor de la clase
 ~cola(); // destructor de la clase
 void encolar(TPedido p);
 void desencolar();
 bool esvacia();
 TPedido primero();
 int longitud();
};

#endif // TADCOLA_H_INCLUDED
