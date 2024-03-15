/*5. Cree una clase llamada Fecha con el siguiente atributo: */

#include <iostream>

using namespace std;

class Fecha
{
    int agno;
public:
    bool bisiesto();
    void leer();//Pedirá por teclado el año y lo almacenará en el atributo agno
};

void Fecha::leer()
{
    cout << "Introduzca un agno: ";
    cin >> agno;
}

bool Fecha::bisiesto()
{
    if(agno%400 == 0)
    {
        cout << "El agno es bisiesto";
        return true;
    }
    if(agno%4 == 0 && agno%100 != 0)
    {
        cout << "El agno es bisiesto";
        return true;
    }
    else
        cout << "El agno no es bisiesto";
        return false;
}

int main()
{
    Fecha Fe;
    Fe.leer();
    cout << "\n";
    Fe.bisiesto();
    cout << "\n\n";
    return 0;
}
