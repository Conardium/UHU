#ifndef COMPLEJO_H
#define COMPLEJO_H

#include <iostream>

using namespace std;

class complejo {
  int real;
  int imaginario;
public:
  complejo(int r, int i);
  virtual ~complejo();
  int getr() const { return real; }
  int geti() const { return imaginario; }
  void set();
  void set(int r, int i) { real=r; imaginario=i; }
  void ver() const;

  complejo operator+(complejo c) const;
  complejo operator+(int i) const;

  complejo operator-() const;

  friend ostream& operator<<(ostream& s, complejo c);
};

complejo operator+(int i, complejo c);

ostream& operator<<(ostream& s, complejo c);

#endif // COMPLEJO_H
