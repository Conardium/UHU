/*4. Diseñe la siguiente clase para mostrar una tabla de multiplicar:*/

#include <iostream>

using namespace std;

class TablaMultiplicar
{
    int Tabla;
public:
    void PedirNoTabla();
    void MostrarTabla();
};

void TablaMultiplicar::PedirNoTabla()
{
    do
    {
        cout<< "\nIngresa un numero para mostrar su tabla de multiplicar: ";
        cin >> Tabla;
        cout << "\n";
        if(Tabla<0 || Tabla>10)
            cout<< "\n\nERROR. Introduzca un numero entre el 1 y el 10."<<endl;
    }while(Tabla<0 || Tabla>10);
}

void TablaMultiplicar::MostrarTabla()
{
    for(int i=0; i<=10; i++)
    {
        cout << Tabla << " x " << i << " = " << Tabla*i << endl;
    }
}


int main()
{
    TablaMultiplicar TM;
    TM.PedirNoTabla();
    TM.MostrarTabla();
    return 0;
}
