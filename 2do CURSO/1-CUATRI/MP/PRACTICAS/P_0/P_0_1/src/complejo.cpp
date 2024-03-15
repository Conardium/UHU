#include <iostream>
#include "complejo.h"
#include "complejo.h"

using namespace std;

complejo::complejo(int r, int i) {
  real=r;
  imaginario=i;
}

complejo::~complejo() {
    //dtor
}

void complejo::set() {
  cout << "parte real: ";
  cin >> real;
  cout << "parte imaginaria: ";
  cin >> imaginario;
}

void complejo::ver() const {
  if (imaginario>=0) {
    cout << real << "+" << imaginario << "i" << endl;
  }
  else {
    cout << real << imaginario << "i" << endl;
  }
}

complejo complejo::operator+(complejo c) const {
  complejo suma(0,0);
  suma.real=real+c.real;
  suma.imaginario=imaginario+c.imaginario;
  return suma;
/*
//OTRA FORMA DE HACERLO
  complejo suma(real,imaginario);
  suma.real+=c.real;
  suma.imaginario+=c.imaginario;
  return suma;
//...Y OTRA
  complejo suma(real+c.real, imaginario+c.imaginario);
  return suma;
//...Y OTRA MAS
  return complejo(real+c.real, imaginario+c.imaginario);
*/
 }

complejo complejo::operator+(int i) const {
  complejo suma(0,0);
  suma.real=real+i;
  suma.imaginario=imaginario;
  return suma;
}

complejo operator+(int i, complejo c) {
  complejo suma(0,0);
//suma.real=i+c.getr(); ERROR real es privado
//suma.imaginario=c.geti(); ERROR imaginario es privado
  suma.set( i+c.getr(), c.geti() );
  return suma;
/*
//OTRA FORMA DE HACERLO
  complejo suma(0,0);
  int rr=i+c.getr();
  int ii=c.geti();
  suma.set(rr, ii);
  return suma;
//OTRA FORMA DE HACERLO
  return complejo( i+c.getr(), c.geti() );
//OTRA FORMA INGENIOSA... LLAMO A SUMA DE UN COMPLEJO CON UN ENTERO
  return c+i;
*/
}

complejo complejo::operator-() const {
  complejo cambiosigno(-real, -imaginario);
  return cambiosigno;

}

ostream& operator<<(ostream& s, complejo c) {
  if (c.imaginario>=0) {
    s << c.real << "+" << c.imaginario << "i";
  }
  else {
    s << c.real << c.imaginario << "i";
  }
  return s;
}
