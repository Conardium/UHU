/*11. Diseñe la siguiente clase para mostrar una sucesión de Fibonacci
*/

#include <iostream>

using namespace std;

class Fibonacci
{
    int NoElementos;
public:
    void PedirNoElementos();
    float MostrarElementos();
};

void Fibonacci::PedirNoElementos()
{
    do
    {
        cout<< "\nEscoge el numero de elementos: ";
        cin >> NoElementos;
    }while(NoElementos<1);
}

float Fibonacci::MostrarElementos()
{
    //0,1,1,2,3,5,8,13,21,34
    cout << "\n\n";
    int primero=0;
    int siguiente=1;
    int aux;;
    for (int i=0; i<NoElementos;i++)
    {

        cout << primero << ", ";

        aux=primero;
        primero=primero+siguiente;
        siguiente=aux;
    }
    cout << "\n\n";
}

int main()
{
    Fibonacci naci;
    naci.PedirNoElementos();
    naci.MostrarElementos();
    return 0;
}
