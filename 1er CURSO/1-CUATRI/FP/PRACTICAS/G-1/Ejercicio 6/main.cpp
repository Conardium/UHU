/*6. Diseñe un programa en C++ de tal forma que pedirá la nota de teoría y la nota de prácticas de un
examen realizado (se suponen valoradas entre 0 y 10). Y mostrará por pantalla la nota final,
sabiendo que la nota de teoría vale el 70% y la nota de prácticas vale el 30%.*/

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
