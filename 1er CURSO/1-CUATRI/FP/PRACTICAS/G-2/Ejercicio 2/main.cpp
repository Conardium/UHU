/*2. Diseñe un programa que lea dos números enteros por teclado y determine cuál es el mayor y
cuál es el menor. También deberá considerar el caso en el que los dos números sean iguales.
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
