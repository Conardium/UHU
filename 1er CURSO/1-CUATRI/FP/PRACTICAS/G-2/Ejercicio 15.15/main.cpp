/*15. Se quiere codificar un juego consistente en adivinar un número. Para ello se pide implementar
la clase Adivinar, que constará de:*/

#include <iostream>
#include <stdlib.h>

using namespace std;

class Adivinar
{
    int Nsecreto; //NUMERO SECRETO A ADIVINAR
    int Puntos; //PUNTOS DE PARTIDA PARA COMENZAR A JUGAR
public:
    void Inicio();
    void Jugar();
};

void Adivinar::Inicio()
{
    int semilla, a=0;
    cout << "\nIndica un numero: ";
    cin >> semilla;
    //Obtenemos el numero secreto a partir de la semilla
    Nsecreto=(semilla%90)*123;

    for(int i=1;i<=Nsecreto;i++)
    {
        if(Nsecreto%i==0)
            a=a+i;
    }
    if(a>Nsecreto) //ES ABUNDANTE
    {
        Nsecreto=((semilla%90)*123)%12459;
    }
    else //NO ES ABUNDANTE
        Nsecreto=((semilla%90)*123)%5397;

    cout << "\n\nElige la cantidad de puntos de partida: ";
    cin >>Puntos;
}

void Adivinar::Jugar()
{
    int num;
    do
    {
        system("cls");
      cout << "\nPuntos restantes: " << Puntos;
      cout << "\n\nEscribe un numero para adivinar el secreto: ";
      cin >> num;

    if(num<Nsecreto)
    {
        cout << "\nEl numero secreto es mas grande\n\n";
        Puntos--;
        system("pause");
    }

    if(num>Nsecreto)
    {
        cout << "\nEl numero secreto es mas pequeno\n\n";
        Puntos--;
        system("pause");
    }

    if(num==Nsecreto)
    {
        cout << "\n\n\n\nFELICITACIONES. Ha ganado, el nSecreto era: " << Nsecreto;
        cout << "\n\n";
    }

    if(Puntos==0)
    {
        cout << "\n\n\n\nLo sentimos. Ha perdido, el nSecreto era: "<< Nsecreto;
        cout << "\n\n";
    }


    }while(Puntos!=0 && num!=Nsecreto);
}

int main()
{
    Adivinar Numero;
    system("cls");
    Numero.Inicio();
    system("pause");
    system("cls");
    Numero.Jugar();
    return 0;
}
