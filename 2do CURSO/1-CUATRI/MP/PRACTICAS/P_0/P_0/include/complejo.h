#ifndef COMPLEJO_H
#define COMPLEJO_H

#include <iostream>

using namespace std;

class complejo{
    int real, imaginario;
public:
    complejo(int r, int i=0);
    int getr() const {return real;}
    int geti() const {return imaginario;}
    void set();
    void set(int r, int i) {real=r; imaginario=i;}
    void ver() const;
    //SOBRECARGA DEL OPERADOR +
    complejo operator+(complejo c) const; //COMPLEJO + COMPLEJO
    complejo operator+(int i) const; //COMPLEJO + ENTERO

    //SOBRECARGA DEL OPERADOR - (COMPLEJO INVERSO)
    complejo operator-() const;

    friend ostream& operator<<(ostream& s, complejo c);

};

complejo operator+(int i, complejo c); //ENTERO + COMPLEJO

//SOBRECARGA DEL OPERADOR <<
ostream& operator<<(ostream& s, complejo c);

#endif // COMPLEJO_H
