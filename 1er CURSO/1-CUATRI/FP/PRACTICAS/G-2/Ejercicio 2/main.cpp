/*2. Dise�e un programa que lea dos n�meros enteros por teclado y determine cu�l es el mayor y
cu�l es el menor. Tambi�n deber� considerar el caso en el que los dos n�meros sean iguales.
*/

#include <iostream>

using namespace std;

int main()
{
    int a, b;
    cout << "\nEscribe dos numeros enteros: ";
    cin >> a >> b;

    if (a < b)
    {
        cout << "\n\nEl mayor es: " << b;
        cout << "\nEl menor es: "<< a;
    }
    else
        if(a > b)
    {
        cout << "\n\nEl mayor es: " << a;
        cout << "\nEl menor es: "<< b;
    }
    else
        cout << "\n\nLos dos son iguales";
    return 0;
}
