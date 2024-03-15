#ifndef COMPLEJO_H
#define COMPLEJO_H

#include <iostream>

using namespace std;

class complejo {
  int real;
  int imaginario;
public:
/*
  complejo(int r, int i);
  complejo(int r);
*/
  complejo(int r, int i=0);

//complejo(const complejo &c); NO HACE FALTA HACERLO PORQUE LA CLASE NO TIENE PUNTEROS
  virtual ~complejo();
  int getr() const { return real; }
  int geti() const { return imaginario; }
  void set();
  void set(int r, int i) { real=r; imaginario=i; }
  void ver() const;

  complejo operator+(complejo c) const;
  //complejo operator+(int i) const;

  complejo operator-() const;

//LA SOBRECARGA DEL OPERATOR++ LA VAMOS A HACER CON FUNCIONES EN VEZ DE CON METODOS
  //complejo operator++();
  //complejo operator++(int nousar);

  bool operator==(complejo c);
  bool operator==(int i);

  operator int();

  friend ostream& operator<<(ostream& s, complejo c);
};

//complejo operator+(int i, complejo c);

ostream& operator<<(ostream& s, complejo c);

complejo operator++(complejo &c);
complejo operator++(complejo &c, int nousar);

#endif // COMPLEJO_H
