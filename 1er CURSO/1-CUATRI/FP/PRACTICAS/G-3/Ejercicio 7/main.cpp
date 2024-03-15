/*7. Implemente una clase de tal forma que fusione dos tablas (ordenadas previamente) en otra
tercera, de tal forma que los elementos queden ordenados en ésta.*/

#include <iostream>
#include <stdlib.h>

using namespace std;

class merges
{
    int uno[15], dos[15], fus[30];
    int numuno;
    int numdos;
public:
    void cargar();
    void mezclar();
    void ver();
    void verfusion();
};

void merges::cargar()
{
    cout << "\nCual es el tamano de la tabla 1: ";
    cin >> numuno;
    for(int i=0; i<numuno; i++)
    {
        cout << "Ingrese valor para la pos_"<<i<<": ";
        cin >> uno[i];
    }

    cout << "\nCual es el tamano de la tabla 2: ";
    cin >> numdos;
    for(int i=0; i<numdos; i++)
    {
        cout << "Ingrese valor para la pos_"<<i<<": ";
        cin >> dos[i];
    }
}

void merges::ver()
{
    cout << "\n----- Las tablas uno y dos son -----\n";
    cout << "TABLA 1:\n";
    for(int i=0; i<numuno; i++)
        cout << uno[i]<<endl;

    cout << "\nTABLA 2:\n";
    for(int i=0; i<numdos; i++)
        cout << dos[i]<<endl;
}

void merges::mezclar()
{
    int i;
    for(i=0; i<numuno; i++)
        fus[i]=uno[i];
    for(i=0; i<numdos; i++)
        fus[i+numuno]=dos[i];
}

void merges::verfusion()
{
    int aux;
    cout << "\nEl vector fus ordenado seria: \n";
    for(int i=numuno+numdos; i>0; i--)
    {
        for(int j=1; j<i;j++)
        {
            if(fus[j]>fus[j+1])
            {
                aux=fus[j];
                fus[j]=fus[j+1];
                fus[j+1]=aux;
            }
        }
    }
    cout << "\n";
    for(int i=0; i<numuno+numdos; i++)
        cout << fus[i] << endl;
}

int main()
{
    merges M;
    M.cargar();
    M.ver();
    system("pause");
    M.mezclar();
    M.verfusion();
    return 0;
}
