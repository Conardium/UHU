#include <cmath>
#include <iostream>
#include "Fecha.h"

using namespace std;

Fecha::Fecha(const int &dia, const int &mes, const int &anio){
    setFecha(dia, mes, anio);
}

void Fecha::setFecha(const int &dia, const int &mes, const int &anio){
    this->dia = dia;
    this->mes = mes;
    this->anio = anio;

    if(mes < 1)
        this->mes=1;
    else if(mes > 12)
        this->mes=12;
    int maxDias[] = {0,31,bisiesto()?29:28,31,30,31,30,31,31,30,31,30,31};

    if(dia < 1)
        this->dia=1;
    else if(dia > maxDias[this->mes])
        this->dia=maxDias[this->mes];
}

bool Fecha::bisiesto() const {
    return ((this->anio%400==0) || (this->anio%4==0 && this->anio%100!=0));
}

void Fecha::ver() const {
    if(this->dia < 10)
        cout << "0";
    cout << this->dia << "/";
    if(this->mes < 10)
        cout << "0";
    cout << this->mes << "/" << this->anio;
}

Fecha Fecha::operator++(){ //++f
    Fecha sube = Fecha(*this)+1;

    this->dia=sube.dia;
    this->mes=sube.mes;
    this->anio=sube.anio;

    return *this;
}

Fecha Fecha::operator++(const int i){ //f++
    Fecha copia(this->dia, this->mes, this->anio); // copia(*this)
    ++(*this);
    return copia;
}

Fecha Fecha::operator+(const int &i) const {
    Fecha suma(*this);
    suma.dia+=i;
    int dmax, diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    if (this->bisiesto())
        diaMes[2]=29;
    dmax=diaMes[suma.mes];
    //Vamos a comprobar si dia ha pasado de mes
    if(suma.dia > dmax){
        suma.dia-= dmax;
        suma.mes++;

        //Hacemos lo mismo con el mes
        if(suma.mes > 12){
            suma.mes=1;
            suma.anio++;
        }
    }
    return suma;
}

Fecha operator+(const int &i, const Fecha &f){
    return f+i; //Llama al metodo operator+
}

ostream& operator<<(ostream& s, const Fecha &f){
    s << (f.getDia()<10?"0":"") << f.getDia() << " ";
    switch(f.getMes())
    {
        case 1: s << "ene"; break;
        case 2: s << "feb"; break;
        case 3: s << "mar"; break;
        case 4: s << "abr"; break;
        case 5: s << "may"; break;
        case 6: s << "jun"; break;
        case 7: s << "jul"; break;
        case 8: s << "ago"; break;
        case 9: s << "sep"; break;
        case 10: s << "oct"; break;
        case 11: s << "nov"; break;
        case 12: s << "dic"; break;
    }
    s << " " << f.getAnio();

    return s;
}





