#ifndef TADLISTA_H_INCLUDED
#define TADLISTA_H_INCLUDED

#include <iostream>
#include "TTipos.h"

using namespace std;

class lista
{
  TPedido *elementos; // elementos de la lista
  int n; // nº de elementos que tiene la lista
  int Tama; // tamaño de la tabla en cada momento

public:
  lista(); // constructor de la clase
  ~lista(); // destructor de la clase
  lista(TPedido e);
  bool esvacia();
  int longitud();
  void anadirIzq(TPedido e);
  void anadirDch(TPedido e);
  void eliminarIzq();
  void eliminarDch();
  TPedido observarIzq();
  TPedido observarDch();
  void concatenar(lista l);
  bool pertenece(TPedido e);
  void insertar(int i, TPedido e);
  void eliminar(int i);
  void modificar(int i, TPedido e);
  TPedido observar(int i);
  int posicion(TPedido e);
};

#endif // TADLISTA_H_INCLUDED
