#ifndef FECHA_H
#define FECHA_H

#include <iostream>

using namespace std;

class Fecha{
  int dia;
  int mes;
  int anio;
public:
    Fecha(const int &dia, const int &m, const int &anio);

    int getDia() const {return dia;}
    int getMes() const {return mes;}
    int getAnio() const {return anio;}

    void setFecha(const int &dia, const int &mes, const int &anio);
    void ver() const;
    bool bisiesto() const;
    Fecha operator++(); //++f;
    Fecha operator++(const int i); //f++
    Fecha operator+(const int &i) const; //FECHA + ENTERO

    friend Fecha operator+(const int &i, const Fecha &f);
};

Fecha operator+(const int &i, const Fecha &f); //const por seguridad y & por velocidad
ostream& operator<<(ostream& s, const Fecha &f);
#endif // FECHA_H
