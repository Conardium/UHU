//3. Crear una clase llamada Estadistica que contenga los siguientes atributos:

#include <iostream>

using namespace std;

class Estadistica
{
    int susp, apr, notab, sob;
    int n; //numero de alumnos
    float nota;

public:
    void elaborarEstadistica();
    void resultado();
};

void Estadistica::elaborarEstadistica()
{
    susp=apr=notab=sob=0;
    cout << "\nIndique el numero de alumnos que se han examinado: ";
    cin >> n;

    for(int i=0; i<n; i++)
    {
        do
        {
            cout << "\nIndique nota del alumno "<< i+1 <<": ";
            cin >> nota;
            if(nota<0 || nota>10)
                cout << "\n\nNota incorrecta";
        }while(nota<0 || nota>10);

        if(nota>=0 && nota<5)
            susp++;

        if(nota>=5 && nota<7)
            apr++;

        if(nota>=7 && nota<9)
            notab++;

        if(nota>=9 && nota<=10)
            sob++;
    }
}

void Estadistica::resultado()
{
    cout<< "\n\n\tRESULTADOS";
    cout<< "\nSuspensos: "<< susp;
    cout<< "\nAprobados: "<<apr;
    cout<< "\nNotables: "<<notab;
    cout<< "\nSobresalientes: "<<sob;
}


int main()
{
    Estadistica Est;
    Est.elaborarEstadistica();
    Est.resultado();
    return 0;
}
