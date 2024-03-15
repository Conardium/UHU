#ifndef CONTRATOMOVIL_H
#define CONTRATOMOVIL_H

#include <iostream>

#include "Contrato.h"
#include "Fecha.h"

using namespace std;

class ContratoMovil:public Contrato{
    float precioXminuto;
    int MinutosHabla;
    char* Nacionalidad;
public:
    ContratoMovil(long int dni, Fecha f, float pXm, int minutos, char* Nac);
    ~ContratoMovil(); //YA QUE LA CLASE TIENE UN PUNTERO
    ContratoMovil(const ContratoMovil& c);

    float getPrecioMinuto() const {return this->precioXminuto;}
    int getMinutosHablados() const {return this->MinutosHabla;}
    /*const*/ char* getNacionalidad() const {return this->Nacionalidad;}

    void setPrecioMinuto(float pXm) {this->precioXminuto=pXm;}
    void setMinutosHablados(int mH) {this->MinutosHabla=mH;}
    void setNacionalidad(const char* nac);

    void ver() const;
    float factura() const;
};
ostream& operator<<(ostream& s, const ContratoMovil &c);
#endif // CONTRATOMOVIL_H
