#ifndef _EXCEP_CONJUNTO_H_
#define _EXCEP_CONJUNTO_H_

#include "excepcion.h"

using namespace std;

class ConjuntoLlenoExcepcion: public Excepcion {
  public:
     ConjuntoLlenoExcepcion(): Excepcion("Se ha alcanzado la capacidad m�xima del conjunto. No se pueden a�adir mas elementos al conjunto.") {};
};

#endif

