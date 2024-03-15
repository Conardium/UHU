/*11. Diseñe un programa que pida una cantidad en euros y devuelva su equivalente en dólares
americanos y en libras esterlinas. Nota: Utilice constantes para guardar el valor cambio en
euros del dólar americano y de las libras esterlinas.*/

#include <iostream>

using namespace std;
#define dolar 1.18
#define libra 0.9

int main()
{
    float euro;
    cout << "\nIndique la cantidad de euros: ";
    cin>> euro;

    cout << "\n\nEN DOLARES: "<< euro*dolar;
    cout << "\nEN LIBRAS: "<< euro*libra;
    cout << "\n\n";
    return 0;
}
