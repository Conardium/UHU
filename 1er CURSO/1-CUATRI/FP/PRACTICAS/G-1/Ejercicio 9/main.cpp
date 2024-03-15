/*10.Diseñe un programa que halle la longitud, la superficie y el volumen de la
circunferencia, círculo y esfera. El programa deberá pedir el radio de la circunferencia.
*/

#include <iostream>
#include <cmath>
#define pi 3.14159

using namespace std;

int main()
{
    float radio;
    cout << "\nIndique el radio de la circunferencia: ";
    cin >> radio;
    cout << "\n\nLONGITUD: "<< 2*pi*radio;
    cout << "\nSUPERFICIE: "<< pi*pow(radio,2);
    cout << "\nVOLUMEN: "<< 4/3*pi*pow(radio,3);
    return 0;
}
