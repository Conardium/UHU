/*16. Diseñe una función que convierta una cantidad positiva de segundos leída desde teclado a su
equivalente en horas, minutos y segundos, expresando estos valores por pantalla. Si se lee,
por ejemplo, el valor de 3701 seg. debe mostrar por pantalla 1 hora, 1 minuto y 41 segundos.*/

#include <iostream>

using namespace std;

int main()
{
    int segundos, horas, minutos;
    cout << "\nIndique una unidad de tiempo (en segundos): ";
    cin >> segundos;

    minutos= segundos/60;
    horas=minutos/60;
    minutos = minutos%60;
    segundos = segundos%60;

    cout << "\n\nEl tiempo es: "<<horas<<" hora(s), "<<minutos<<" minuto(s), "<< segundos << " segundo(s)"<< endl;
    return 0;
}
