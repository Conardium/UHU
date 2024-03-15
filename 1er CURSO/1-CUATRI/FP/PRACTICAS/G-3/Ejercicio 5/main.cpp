/*5. Implemente los métodos de la clase siguiente y diseñe un main que compruebe el
funcionamiento de dichos métodos:*/

#include <iostream>
#define M 2
#define N 4

using namespace std;
typedef char cadena[30];

struct persona
{
    int dni;//DNI de la persona sin letra
    cadena nombre;//Nombre de la persona
};

class matrices
{
    persona tabla[M][N];
public:
    void cargar();
    void encontrar();
};

void matrices::cargar()
{
    for(int i=0; i<M; i++)
        for(int j=0; j<N; j++)
    {
        cout << "\nIngresa un nombre: ";
        cin >> tabla[i][j].nombre;
        cout << "\nIngresa el DNI: ";
        cin >> tabla[i][j].dni;
        cout << "\n-------------------\n";
    }
}

void matrices::encontrar()
{
    int buscarDNI;
    bool encontrado = false;
    int i = 0;
    int j = 0;
    cout << "\n\n\nIndica un DNI para buscar: ";
    cin >> buscarDNI;

    while(i<M && !encontrado)
    {
        while(j<N && !encontrado)
        {
            if(buscarDNI==tabla[i][j].dni)
                encontrado = true;
            else j++;
        }
        if(!encontrado)
        {
            j = 0;
            i++;
        }
    }
    if(encontrado)
        cout << "\nSe ha encontrado el DNI, y pertenece a: "<< tabla[i][j].nombre << endl;
    else
        cout << "\nNo se ha encontrado el DNI"<< endl;
}


int main()
{
    matrices MT;
    MT.cargar();
    MT.encontrar();
    return 0;
}
