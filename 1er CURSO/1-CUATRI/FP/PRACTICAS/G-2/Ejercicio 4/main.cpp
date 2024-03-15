/*4. Diseñe un programa que pida dos números enteros por teclado y muestre por pantalla el
siguiente menú:*/

#include <iostream>
#include <stdlib.h>

using namespace std;

int main()
{
    int a, b;
    int op;
    cout << "\nIngrese dos numeros enteros: ";
    cin >> a >> b;

    do
    {
        system("cls");
        cout << "\n\n\n\t\tMENU\n\n"
             << "\t1. Sumar\n"
             << "\t2. Restar\n"
             << "\t3. Multiplicar\n"
             << "\t4. Dividir\n"
             << "\t5.Salir\n\n"
             << "Elije una opcion: ";
             cin >> op;

        switch(op)
        {
            case 1: cout << "\nLa suma es: "<< a+b << endl;
            system("pause");
                break;

            case 2: cout << "\nLa resta es: "<< a-b<< " y " << b-a << endl;
            system("pause");
                break;

            case 3: cout << "\nEl producto es: "<< a*b << endl;
            system("pause");
                break;

            case 4: cout << "\nLa division es: " << a/b << " y " << b/a << endl;
            system("pause");
                break;

            case 5: break;

            default: cout<< "\n\nOpcion no valida \n\n";
            system("pause");
            break;
        }
    }while(op != 5);

    return 0;
}
