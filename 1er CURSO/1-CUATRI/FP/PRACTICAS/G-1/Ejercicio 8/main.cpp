/*8. Dise�e un programa que pida dos n�meros enteros por teclado y a continuaci�n calcule su
suma, su diferencia, su producto y su cociente y los muestre por pantalla.*/

#include <iostream>

using namespace std;

int main()
{
    int a, b;
    cout << "\nIntroduzca dos numeros enteros: ";
    cin >> a >> b;

    cout<< "\n\ta = "<< a << "\tb = " << b << "\n\n\n";
    cout << "SUMA: "<< a+b;
    cout << "\nRESTA: "<< a-b << " y " << b-a;
    cout << "\nPRODUCTO: "<< a*b;
    cout << "\nDIVISION: "<< a/b << " y " << b/a << endl;
    return 0;
}
