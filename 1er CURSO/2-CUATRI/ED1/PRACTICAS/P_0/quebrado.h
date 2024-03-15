#ifndef QUEBRADO_H_INCLUDED
#define QUEBRADO_H_INCLUDED

class quebrado {
 int numerador, denominador;
public:
 quebrado() { numerador=0; denominador=1; }
 quebrado(int n, int d=1);
 int getnumerador() { return numerador; }
 int getdenominador() {return denominador; }
 void valor(int n, int d=1);
 quebrado operator+(quebrado q);
 quebrado operator-(quebrado q);
 quebrado operator*(quebrado q);
 quebrado operator/(quebrado q);
 quebrado operator-();
 int operator==(quebrado q);
 void mostrar();
};

#endif // QUEBRADO_H_INCLUDED
