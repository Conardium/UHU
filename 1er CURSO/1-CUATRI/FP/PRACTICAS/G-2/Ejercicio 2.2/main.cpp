/*2. Cree una clase con los atributos máximo, minimo y media, y con los siguientes métodos
públicos:*/

#include <iostream>

using namespace std;

class Enteros
{
    int maximo, minimo;
    int n; //Numero de enteros
    int numero;
    float suma;
    float media;

public:
    void estadistica();
    int mostrarmax(); //Devuelve el valor maximo
    int mostrarmin();//Devuelve el valor minimo
    float mostrarmedia();//Devuelve el valor medio
};

void Enteros::estadistica()
{
    suma=0;
    cout << "\nCuantos numeros enteros va a poner: ";
    cin >> n;

    for(int i=0; i<n; i++)
    {
        cout << "\n\nIndica el valor del entero "<< i+1<< ": ";
        cin >> numero;
        if(i==0)
            maximo=minimo=numero;

        if(numero>maximo)
            maximo=numero;
        if(numero<minimo)
            minimo=numero;
        suma+=numero;
    }
    media=suma/n;
}

int Enteros::mostrarmax()
{
    return maximo;
}

float Enteros::mostrarmedia()
{
    return media;
}

int Enteros::mostrarmin()
{
    return minimo;
}

int main()
{
    Enteros E;
    E.estadistica();
    cout << "\n\n";
    cout << "El maximo es: "<< E.mostrarmax();
    cout << "\nEl minimo es: "<< E.mostrarmin();
    cout << "\nLa media es: "<<E.mostrarmedia();
    return 0;
}
