/*2. Dise�e un programa que lea por teclado un n�mero entero y muestre por pantalla ese n�mero
incrementado en 5.*/

#include <iostream>

using namespace std;

int main()
{
    int num;
    cout << "Indica un numero entero: ";
    cin >> num;

    cout << "\n\nEl numero introducido es: "<< num;

    num +=5; // Es lo mismo que: num= num + 5

    cout << "\n\nEse numero incrementado en 5 es: "<< num << endl;

    return 0;
}
