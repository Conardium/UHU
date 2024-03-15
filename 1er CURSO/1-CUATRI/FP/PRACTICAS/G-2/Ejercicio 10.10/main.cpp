//10. Diseñe la siguiente clase:

#include <iostream>

using namespace std;

class Numero
{
    int a, b;
public:
    void informacion();
    int mcd();
};

void Numero::informacion()
{
    cout << "\nEscribe dos numeros enteros: ";
    cin >> a >> b;
}

int Numero::mcd()
{
    while(a!=b)
    {
        if(a>b)
            a=a-b;
        else
            b=b-a;
    }
    cout<< "\n\nEl mcd es: "<< a;
    return a;
}

int main()
{
    Numero num;
    num.informacion();
    num.mcd();
    return 0;
}
