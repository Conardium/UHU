#include <iostream>
#include "complejo.h"

using namespace std;

int main()
{
    complejo a(2), b(-2), c(0,-2), d(1,3);
    a.ver(); // 2+0i
    b.ver(); // -2+0i
    c.ver(); // 0-2i
    d.ver(); // 1+3i

    cout << "----------Prueba del operador(+)----------"<<endl;
    cout << "Complejo + Complejo -> ";
    b=d+c; //1+1i
    b.ver();
    cout << "\nComplejo + Entero -> ";
    b=c+4; //4-2i
    b.ver();
    cout << "\nEntero + Complejo -> ";
    b=3+d; //4+3i
    b.ver();

    cout << "\n----------Prueba del operador(-)----------"<<endl;
    d=-d; // -1-3i
    d.ver();


    return 0;
}
