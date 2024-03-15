/*10. Implementar la clase Tesoro, que nos permita jugar a buscar un tesoro rodeado de minas a
distinta profundidad. Para ello contará con:*/


#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

class Tesoro
{
    char Tablero[5][5]; //T-tesoro, B-bpmba, A-arena
    int Puntos;

public:
    void Iniciar();
    bool Jugar();
    void MostrarTablero();
};

//--METODOS--//
void Tesoro::Iniciar()
{
    srand(time(0));
    Puntos=15;
    int azar;
    int cofre=1;
    int bomba1=1, bomba2=2, bomba3=3;
    do
    {
        azar=rand()%4;

        if(azar==0 && cofre==1)
        {
            Tablero[rand()%5][rand()%5]='T';
            cofre--;
        }
        if(azar==1 && bomba1==1)
        {
            Tablero[rand()%5][rand()%5]='B(1)';
            bomba1--;
        }
        if(azar==2 && bomba2==2)
        {
            Tablero[rand()%5][rand()%5]=='B(2)';
            bomba2--;
        }
        if(azar==3 && bomba3==3)
        {
            Tablero[rand()%5][rand()%5]=='B(3)';
            bomba3--;
        }

    }while(cofre!=1 || bomba1!=1 || bomba2!=2 || bomba3!=3);
}


//--MAIN--//
int main()
{
    cout << "Hello world!" << endl;
    return 0;
}
