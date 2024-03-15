/*5. Diseñe la siguiente clase para mostrar varias tablas a la vez de manera horizontal*/

#include <iostream>

using namespace std;

class TablasMultiplicar
{
    int TablaIni, TablaFin;
    int aux;
public:
    void PedirNoTablas();
    void MostrarTablas();
};

void TablasMultiplicar::PedirNoTablas()
{
    do
    {
        cout << "Introduzca el numero de tabla inicial y el final: ";
        cin >> TablaIni >> TablaFin;
        if(TablaIni>TablaFin)
        {
            aux=TablaIni;
            TablaIni=TablaFin;
            TablaFin=aux;
        }
    }while(TablaIni<0 || TablaIni>10 ||TablaFin<0 || TablaFin>10);
}

void TablasMultiplicar::MostrarTablas()
{
    for(int i=0; i<=10; i++)
    {
        for(int j=TablaIni; j<=TablaFin; j++)
        cout<< j << " x " << i << " = " << j*i << "\t";

        cout << "\n";
    }

}

int main()
{
    TablasMultiplicar TM;
    TM.PedirNoTablas();
    TM.MostrarTablas();
    return 0;
}
