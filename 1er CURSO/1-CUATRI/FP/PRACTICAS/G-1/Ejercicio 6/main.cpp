/*6. Dise�e un programa en C++ de tal forma que pedir� la nota de teor�a y la nota de pr�cticas de un
examen realizado (se suponen valoradas entre 0 y 10). Y mostrar� por pantalla la nota final,
sabiendo que la nota de teor�a vale el 70% y la nota de pr�cticas vale el 30%.*/

#include <iostream>

using namespace std;

int main()
{
    float teoria, practica;

    do //NOTA TEORIA
    {
        cout << "\nIntroduzca la nota de teoria (0 a 10): ";
        cin >> teoria;
        if(teoria<0 || teoria>10)
            cout << "\nNota incorrecta, seleccione una nota valida\n\n\n";
    }while(teoria<0 || teoria>10);
//---------------------------------------------------------------
    do //NOTA PRACTICA
    {
        cout << "\nIntroduzca la nota de practicas (0 a 10): ";
        cin >> practica;
        if(practica<0 || practica>10)
            cout << "\nNota incorrecta, seleccione una nota valida\n\n\n";
    }while(practica<0 || practica>10);

    cout << "\n\nLA NOTA FINAL ES: "<<(0.7*teoria + 0.3*practica)<< endl;


    return 0;
}
