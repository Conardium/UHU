/*3. Implemente los métodos de la clase siguiente y diseñe un main que compruebe el
funcionamiento de dichos métodos:*/


#include <iostream>
#define M 10
#define N 15

using namespace std;

class tres
{
    int tabla[M][N];
public:
    void cargar();
    int encontrar();
};

void tres::cargar()
{
    for(int i=0; i<M; i++)
    {
        for(int j=0; j<N; j++)
        {
            tabla[i][j]=i*j;
            cout << "\t"<<tabla[i][j];
        }
        cout << endl;
    }
}

int tres::encontrar()
{
    int i=0;
    int j=0;
    int num;
    bool encontrado;
    cout << "\n\nIndica un numero para buscarlo: ";
    cin >> num;
    while(i<M && !encontrado)
    {
        while(j<N && !encontrado)
        {
          if(tabla[i][j]==num)
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
    {
        cout << "\n\nEl numero se ha encontrado en la posicion: " << i << ", "<< j;
        return 1;
    }
    else
    {
        cout << "\n\nNo se ha encontrado el numero";
        return 0;
    }

}

int main()
{
    tres DosDimensiones;
    DosDimensiones.cargar();
    DosDimensiones.encontrar();
    return 0;
}
