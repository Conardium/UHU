/*8. Implementar la clase PalabraOculta, que nos permita jugar a adivinar una palabra. Dicha clase
tendrá las siguientes características:*/

#include <iostream>
#include <conio.h>
#include <cstring> //convierte una cadena de texto a MAYUS
#include <string.h>
#include <cctype>
#include <stdlib.h>

using namespace std;
typedef char cadena[30];


class PalabraOculta
{
    cadena palabraSecreta; //Contiene la palabra a adivinar
    cadena Asterisco;
    int Puntos; //Almacena los puntos

public:
    void Iniciar();
    int Jugar();
    void MostrarSecreta();
};
// -- METODOS -- //
void PalabraOculta::MostrarSecreta()
{
    cout << "\n\nLa palabra secreta era: "<< palabraSecreta << endl << endl;
}

void PalabraOculta::Iniciar()
{
    Puntos=9; //PUNTOS INICIALES
    cout << "\nIndica la palabra secreta a adivinar: ";
    cin >> palabraSecreta;
    strupr(palabraSecreta);
    strcpy(Asterisco, palabraSecreta);
    system("cls");

    for(int i=0; Asterisco[i]!='\0'; i++)
    {
        Asterisco[i]='-';
    }
}

int PalabraOculta::Jugar()
{
    char letra;
    bool encontrada=false;
    bool ganar=false;
    do
    {
        cout << "\n\nLa palabra oculta es: " << Asterisco << endl;
        cout << "Puntos: "<<Puntos <<endl;

        //NOS PIDE UNA LETRA
        cout << "\nIndica una letra: ";
        cin >> letra;
        letra=toupper(letra);
        system("cls");
        //ESTÁ LA LETRA EN LA PALABRA???
        for(int i=0; palabraSecreta[i]!='\0'; i++)
        {
            if(letra==palabraSecreta[i])
            {
                Asterisco[i]=toupper(letra);
                encontrada=true;
            }
        }
        if(!encontrada)
        Puntos--;
        encontrada=false;

        //Vemos si "Asterisco" es igual a la palabra secreta, para terminar el juego.
        if(strcmp(Asterisco, palabraSecreta)==0)
        {
            ganar = true;
            return Puntos;
        }
    }while(Puntos!=0 && !ganar);

    return Puntos;
}

// -- MAIN -- //
int main()
{
    int puntuaje;
    PalabraOculta PO;
    PO.Iniciar();
    puntuaje = PO.Jugar();
    if(puntuaje!=0)
        cout << "\n\nENHORABUENA, has ganado"<< "\n" << "-Puntos totales: "<<puntuaje<< "\n\n";
    else
        cout << "\n\nLo Sentimos, has perdido"<<endl << endl;
    PO.MostrarSecreta();
    system("pause");
    return 0;
}
