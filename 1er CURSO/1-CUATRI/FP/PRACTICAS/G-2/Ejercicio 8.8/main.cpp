/*8. Cree una clase con un atributo de tipo entero, y con los siguientes métodos públicos:*/

#include <iostream>

using namespace std;

class Factorizar
{
    int numero;
public:
    void informacion();
    long factorial();
};

void Factorizar::informacion()
{
    do
    {
        cout << "\nEscribe un numero entero (entre 0 y 20): ";
        cin >> numero;
        if(numero<0 && numero>=20)
            cout << "\n\nERROR, seleccione un numero valido."<<endl;
    }while(numero<0 && numero>=20);
}

long Factorizar::factorial()
{
    for(int i=1; i<numero; i++)
    {
        if(numero%i==0)
        {
            cout << "Factor de "<< numero << " es "<< i<<endl;
            return i;
        }
    }
}

int main()
{
    Factorizar F;
    F.informacion();
    F.factorial();
    return 0;
}
