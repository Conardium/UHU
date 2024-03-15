/*1. Diseñe un programa que lea una nota con decimales por teclado y muestre por pantalla la
calificación que tiene. */

#include <iostream>

using namespace std;

int main()
{
    float nota;

    do
    {
        cout << "Indique la nota: ";
        cin >> nota;

        if(nota<0 || nota> 10)
            cout << "\n\nERROR, seleccione una nota correcta\n\n";

    }while(nota<0 || nota> 10);

    if(nota>0 && nota<5)
        cout<< "\nSUSPENSO"<< endl;
    else
        if(nota>=5 && nota<7)
        cout << "\nAprobado"<< endl;
    else
        if(nota>=7 && nota<9)
        cout << "\nNotable"<< endl;
    else
        cout << "\nSobresaliente"<< endl;
    return 0;
}
