/*4. Implemente los métodos de la clase siguiente y diseñe un main que compruebe el
funcionamiento de dichos métodos:*/

#include <iostream>
#include <string.h>
#define M 3
#define N 4

using namespace std;
typedef char cadena[30];

class cuatro
{
    cadena tabla[M][N];
public:
    void cargar();
    void encontrar();
};

void cuatro::cargar()
{
    for(int i=0; i<M; i++)
        for(int j=0; j<N; j++)
        {
            cout << "\nIngresa una palabra en la posicion " << i << ", " << j << ": ";
            cin >> tabla[i][j];
        }
}

void cuatro::encontrar()
{
    int i, j;
    i=j=0;
    cadena buscar;
    bool encontrado=false;
    cout << "\n\nIngrese una palabra para buscarla: ";
    cin >> buscar;
    while(i<M && !encontrado)
    {
        while(j<N && !encontrado)
        {
            if(strcmp(tabla[i][j], buscar)==0)
                encontrado=true;
            else
                j++;
        }
        if(!encontrado)
        {
           i++;
           j=0;
        }
    }

    if(encontrado)
        cout << "\n\nSe ha encontrado la palabra en la posicion "<< i << ", " << j << endl;
    else
        cout << "\n\nNo se ha encontrado la palabra" << endl;
}

int main()
{
    cuatro Palabra;
    Palabra.cargar();
    Palabra.encontrar();
    return 0;
}
