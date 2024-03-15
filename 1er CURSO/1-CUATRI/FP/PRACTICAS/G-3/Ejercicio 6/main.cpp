/*6. Determine si dos tablas tab1 y tab2 tienen el mismo contenido. Diseñe una clase que resuelva
este problema.*/

#include <iostream>

using namespace std;

class vector
{
    int tab1[4], tab2[4];
public:
    void cargar();
    int comparar();
};

void vector::cargar()
{
    for(int i=0; i<4; i++)
    {
        cout << "\nIngresa un valor para la tabla1: ";
        cin >> tab1[i];
    }
    cout << "\n-----------------------";

    for(int i=0; i<4; i++)
    {
        cout << "\nIngresa un valor para la tabla2: ";
        cin >> tab2[i];
    }
}

int vector::comparar()
{
    int i=0;
    bool iguales= true;
    cout << "\n\nVamos a comprobar si son iguales o no:\n";
    while(i<4 && iguales)
    {
        if(tab1[i]!=tab2[i])
            iguales=false;

        else i++;
    }
    if(iguales)
        return 0;
    else return 1;
}

int main()
{
    int veredicto;
    vector V;
    V.cargar();
    veredicto= V.comparar();
    if(veredicto==1)
        cout << "\nLas tablas son distintas\n";
    else
        cout << "\nLas tablas son iguales\n";
    return 0;
}
