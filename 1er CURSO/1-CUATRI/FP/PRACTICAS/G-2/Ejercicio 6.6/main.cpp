/*6. Implemente la siguiente clase:*/

#include <iostream>

using namespace std;

class primo
{
    int n;
public:
    void cargar();
    //Pedirá por teclado un número entero, almacenándolo en n
    bool esPrimo();
    //Devolverá true si es primo el número n, false en caso contrario
};

void primo::cargar()
{
    cout << "\nDame un numero entero: ";
    cin>> n;
}

bool primo::esPrimo()
{

    for(int i=2;i<n;i++)
    {
        if(n%i==0)
        {
            cout << "\nEl numero NO es primo\n";
            return false;
        }
        else
        {
            cout << "\nEl numero es primo\n";
            return true;
        }
    }
}

int main()
{
    primo p;
    p.cargar();
    p.esPrimo();
    return 0;
}
