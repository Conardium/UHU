/*2. Se quiere realizar el juego del Tres en Raya (tic tac toe) para lo cual se le solicita al alumno las
implementaciones que se detallan a continuación:*/

#include <iostream>
#include <stdlib.h>
#include <string.h>

using namespace std;

class TicTacToe
{
    char Tablero[3][3];

public:
    TicTacToe(); //Constructor
    void LimpiarTablero(); //Vacia la tabla
    void Pintar(); //Muestra el tablero
    bool PonerFicha (char ficha, int fila, int columna);
    bool ComprobarFila(char ficha, int fila);
    bool ComprobarColumna(char ficha, int columna);
    bool ComprobarDiagonal(char ficha, int fila, int columna);
    bool TableroCompleto();
    void PedirPosicion(char ficha, int &fila, int &columna);
};

TicTacToe::TicTacToe()
{
    LimpiarTablero();
}

void TicTacToe::LimpiarTablero()
{
    for(int i=0; i<3; i++)
        for(int j=0; j<3; j++)
    {
        Tablero[i][j]=' ';
    }
}

void TicTacToe::Pintar()
{
    for(int i=0; i<3; i++)
    {
        for(int j=0; j<3; j++)
        {
            cout << Tablero[i][j];
            if(j!=2)
            cout << "|";
        }
        if(i!=2)
        cout << "\n-+-+-\n";
    }
    cout << endl;
}

bool TicTacToe::PonerFicha(char ficha, int fila, int columna)
{
    if(Tablero[fila][columna] == ' ')
    {
        Tablero[fila][columna]=ficha;
        return true;
    }
    else
        return false; //No se puede poner la ficha
}

bool TicTacToe::ComprobarFila(char ficha, int fila)
{
    if((Tablero[fila][0]==Tablero[fila][1]) && (Tablero[fila][0]==Tablero[fila][2]))
        return true;
    else
        return false;
}

bool TicTacToe::ComprobarColumna(char ficha, int columna)
{
    if((Tablero[0][columna]==Tablero[1][columna]) && (Tablero[0][columna]==Tablero[2][columna]))
        return true;
    else
        return false;
}

bool TicTacToe::ComprobarDiagonal(char ficha, int fila, int columna)
{
    bool diagonal;
    if(fila==1 && columna==1)
        diagonal=((Tablero[0][0]==Tablero[1][1]) && (Tablero[0][0]==Tablero[2][2]));
    else if((fila==0 && columna ==0) || (fila==2 && columna ==2))
        diagonal=((Tablero[0][0]==Tablero[1][1]) && (Tablero[0][0]==Tablero[2][2]));
    else if((fila==2 && columna==0) || (fila==0 && columna==2))
        diagonal=((Tablero[0][0]==Tablero[1][1]) && (Tablero[0][0]==Tablero[2][2]));
    else
        diagonal=false;
    return diagonal;
}

bool TicTacToe::TableroCompleto()
{
    int i=0;
    int j=0;
    bool espacio=false;
    while(i<3 && !espacio)
    {
        while(j<3 && !espacio)
        {
            if(Tablero[i][j]==' ')
                espacio = true;
            else
                j++;
        }
        if(!espacio)
        {
            j=0;
            i++;
        }
    }
    if(espacio)
        return false;
    else
        return true;
}

void TicTacToe::PedirPosicion(char ficha, int &fila, int &columna)
{
    do
    {
        cout << "\n\nIndique fila: ";
        cin >> fila;
    }while(fila<0 || fila>2);

    do
    {
        cout << "\nIndique columna: ";
        cin >> columna;
    }while(columna<0 || columna>2);
}

int main()
{
    char continuar; //DESEA CONTINUAR??
    char ficha = 'X';
    int fila, columna;
    bool FichaColocada, TRfila, TRcolumna, TRdiagonal;
    bool Empate;
    TicTacToe T;

    do
    {
        do
        {
            cout << "\n";
            T.Pintar();
            cout << "\nTurno de " << ficha << endl;
            T.PedirPosicion(ficha, fila, columna);
            FichaColocada=T.PonerFicha(ficha, fila, columna);
            if(FichaColocada)
                {
                cout << "\nFicha colocada correctamente\n\n";
                system("pause");
                }
            else
                {
                cout << "\nLa casilla esta llena\n\n";
                system("pause");
                system("cls");
                }
        }while(!FichaColocada);

        //COMPROBAR SI HACE 3 EN RAYAS
        TRfila=T.ComprobarFila(ficha,fila);
        if(TRfila)
        {
            system("cls");
            cout << "\n";
            T.Pintar();
            cout << "EL GANADOR ES: " << ficha << endl << endl;
            cout << "\n\nDesea jugar de nuevo(S/N): ";
            cin >> continuar;
            continuar=toupper(continuar);
            if(continuar== 'S')
                T.LimpiarTablero();
            system("pause");
        }
        TRcolumna=T.ComprobarColumna(ficha,columna);
        if(TRcolumna)
        {
            system("cls");
            cout << "\n";
            T.Pintar();
            cout << "\nEL GANADOR ES: " << ficha << endl << endl;
            cout << "\n\nDesea jugar de nuevo(S/N): ";
            cin >> continuar;
            continuar=toupper(continuar);
            if(continuar== 'S')
                T.LimpiarTablero();
            system("pause");
        }
        TRdiagonal=T.ComprobarDiagonal(ficha, fila, columna);
        if(TRdiagonal)
        {
            system("cls");
            cout << "\n";
            T.Pintar();
            cout << "EL GANADOR ES: " << ficha << endl << endl;
            cout << "\n\nDesea jugar de nuevo(S/N): ";
            cin >> continuar;
            continuar=toupper(continuar);
            if(continuar== 'S')
                T.LimpiarTablero();
            system("pause");
        }

        Empate=T.TableroCompleto();
        if(Empate)
        {
            system("cls");
            cout << "\n";
            T.Pintar();
            cout << "\n\nEl tablero esta completo, hay un empate\n\n";
            system("pause");
        }
        if(ficha=='X')
            ficha = 'O';
        else
            ficha = 'X';
        system("cls");
    }while(continuar!='N');

    return 0;
}
