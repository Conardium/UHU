/*1. Implemente los métodos de la clase siguiente y diseñe un main que compruebe el
funcionamiento de dichos métodos.*/

#include <iostream>
#define M 10

using namespace std;

class uno
{
    int tabla[M];
public:
    void cargar();
    int maximo();
    int minimo();
};

void uno::cargar()
{
    for(int i=0; i<M; i++)
    {
        cout << "Selecciona un entero para la posicion " << i << ": ";
        cin >> tabla[i];
        cout << "\n";
    }
}

int uno::maximo()
{
    int maximo;
    maximo=tabla[0];
    for(int i=0; i<M; i++)
    {
        if(tabla[i]>maximo)
            maximo=tabla[i];
    }
    return maximo;
}

int uno::minimo()
{
    int minimo;
    minimo=tabla[0];
    for(int i=0; i<M; i++)
    {
        if(tabla[i]<minimo)
            minimo=tabla[i];
    }
    return minimo;
}

int main()
{
    uno Tab;
    Tab.cargar();
    cout << "\nEl valor maximo es: " << Tab.maximo();
    cout << "\nEl valor minimo es: " << Tab.minimo();
    cout << endl;
    return 0;
}
