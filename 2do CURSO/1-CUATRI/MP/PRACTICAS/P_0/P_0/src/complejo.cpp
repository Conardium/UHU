#include "complejo.h"
#include <iostream>

using namespace std;

complejo::complejo(int r, int i){
    real=r;
    imaginario=i;
}

void complejo::set(){
    cout << "Parte real: ";
    cin >> real;
    cout << "Parte imaginaria: ";
    cin >> imaginario;
}

void complejo::ver() const{
    if(imaginario >= 0)
        cout << "Z= " << real << "+" << imaginario << "i"<<endl;
    else
        cout << "Z= " << real << imaginario << "i"<<endl;
}

//-------------OPERADOR(+)---------------
complejo complejo::operator+(complejo c) const{ // C + C
    complejo suma(0,0);
    suma.real=real+c.real;
    suma.imaginario=imaginario+c.imaginario;
    return suma;

    //...OTRA FORMA DE IMPLEMENTARLO
    //return complejo(c.real+real, c.imaginario+imaginario);
}

complejo complejo::operator+(int i) const{ // C + I
    return complejo(real+i, imaginario);
}

complejo operator+(int i, complejo c){ // I + C
    return complejo (c.getr()+i, c.geti());
  //OTRA FORMA INGENIOSA... LLAMO A SUMA DE UN COMPLEJO CON UN ENTERO
  //return c+i;
}
//-----------------------------------------------

//-------------OPERADOR(-)---------------
complejo complejo::operator-() const{
    return complejo(-real, -imaginario);
}
//-----------------------------------------------

//-------------OPERADOR(<<)---------------
ostream& operator<<(ostream& s, complejo c){
    if(c.imaginario>=0)
        s << c.real <<"+"<<c.imaginario<<"i"<<endl;
    else
        s << c.real<<c.imaginario<<"i"<<endl;
    return s;
}

