/*4. Diseñe un programa que intercambie el valor de dos variables de tipo entero leídas desde
teclado, sacando su valor por pantalla.*/

#include <iostream>

using namespace std;

int main()
{
    int a, b;
    int aux; // SIRVE PARA INTERCAMBIAR LOS NUMEROS

    cout << "Introduce dos numeros enteros: ";
    cin >> a >> b;
    cout<< "\nINICIO: a = "<< a << "\tb = "<< b;

    //INTERCAMBIAMOS
    aux = a;
    a = b;
    b = aux;
    cout << "\n\n\nINTERCAMBIO: a = " << a << "\tb = "<< b << "\n\n";
    return 0;
}
