#ifndef CONTRATO_H
#define CONTRATO_H

#include <iostream>
#include "Fecha.h"

using namespace std;


class Contrato{ //CLASE BASE ABSTRACTA Y POLIMORFICA
    static int contador;
    const int idContrato;
    long int dniContrato;
    Fecha fechaContrato;
public:
    Contrato(long int dni, Fecha f);
    virtual ~Contrato();

    long int getDniContrato() const {return this->dniContrato;}
    Fecha getFechaContrato() const {return this->fechaContrato;}
    int getIdContrato() const {return this->idContrato;}

    void setFechaContrato(Fecha f) {this->fechaContrato=f;}
    void setDniContrato(long int dni) {this->dniContrato=dni;}

    virtual void ver() const; //PARA PERMITIR EL POLIMORFISMO
    virtual float factura() const = 0; //HACEMOS LA CLASE ABSTRACTA (quitar si se quiere usar Prueba2.cpp)
};
ostream& operator<<(ostream& s, const Contrato &c);

#endif // CONTRATO_H
