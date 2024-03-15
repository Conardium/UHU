/*2. Implemente los métodos de la clase siguiente y diseñe un main que compruebe el
funcionamiento de dichos métodos:*/

#include <iostream>
#define M 10

using namespace std;

class matrices
{
    int tabla[M];
public:
    void cargar();
    bool encontrar();
};

void matrices::cargar()
{
    for(int i=0; i<M; i++)
    {
        tabla[i]=i;
        cout << "\n\t"<<tabla[i];
    }
}

bool matrices::encontrar()
{
    int i, num;
    bool encontrado;
    i=0;
    cout << "\n\nSeleccione un entero para buscarlo en la tabla: ";
    cin >> num;
    while(i<M && !encontrado)
    {
        if(num==tabla[i])
            encontrado=true;
        else
            i++;
    }
    if(encontrado)
    {
        cout << "\n\nNUMERO ENCONTRADO, en la posicion: " << i+1;
            return true;
    }
    else
    {
        cout << "\n\nNo se ha encontrado el numero";
        return false;
    }
}

int main()
{
    matrices MT;
    MT.cargar();
    MT.encontrar();
    return 0;
}
