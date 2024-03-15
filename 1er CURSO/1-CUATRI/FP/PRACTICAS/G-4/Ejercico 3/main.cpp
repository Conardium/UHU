/*3. Realizar un programa en c++ para controlar las cuentas corrientes de una persona. Para ello
definimos la siguiente clase que es capaz de almacenar y gestionar una sola cuenta.
*/

#include <iostream>
#include <stdlib.h>
#include <string.h>

#define MAX_CUENTAS 100 //Numero de cuentas maxima

using namespace std;
typedef char Cadena[50]; //Tipo de datos cadena

class Cuenta
{
    float Saldo; //Saldo de la cuenta
    int NoCuenta; //N de la cuenta
    bool Bloqueada; //true si esta bloqueada

public:
    Cuenta();
    Cuenta(int pNo, float pSal);
    bool ActualizarSaldo(float pSal);
    void ActualizarBloqueo(bool pBloq);
    float DameSaldo();
    int DameNoCuenta();
    bool EstaBloqueada();

};

Cuenta::Cuenta()
{
    Saldo=0;
    NoCuenta=0;
    Bloqueada=false;
}

Cuenta::Cuenta(int pNo, float pSal)
{
    Saldo=pSal;
    NoCuenta=pNo;
    Bloqueada=false;
}

bool Cuenta::ActualizarSaldo(float pSal)
{
    if(!Bloqueada)
    {
        Saldo=pSal;
        return true;
    }
    else
        return false;
}

void Cuenta::ActualizarBloqueo(bool pBloq)
{
    Bloqueada=pBloq;
}

float Cuenta::DameSaldo()
{
    return Saldo;
}

int Cuenta::DameNoCuenta()
{
    return NoCuenta;
}

bool Cuenta::EstaBloqueada()
{
    if(Bloqueada) //Si esta bloqueada
        return true;
    else // No bloqueada
        return false;
}

int BuscarCuenta(Cuenta Ctas[MAX_CUENTAS], int NCuentas, int NoCuenta); //LLAMADA A LA FUNCION

int MenuCuentas()
{
    int op;
    cout << "\n\n";
    cout << "\t\t       Menu Gestion de Cuentas\n"
         << "\t\t1. A"<<char(164)<< "adir una cuenta a un cliente\n"
         << "\t\t2. Mostrar las cuentas del cliente\n"
         << "\t\t3. Borrar una cuenta del cliente\n"
         << "\t\t4. Modificar Saldo de una cuenta\n"
         << "\t\t5. Modificar Estado de una cuenta\n"
         << "\t\t6. Salir\n"
         << "\t\t-------------\n"
         << "\t\tElige Opcion: ";
    cin >> op;
    return op;
}

int main()
{
    int op;
    Cuenta DatosCuentas[MAX_CUENTAS];
    int nCuentas = 0;
    int NumeroCuenta;
    float Saldo;
    int ExisteLaCuenta;
    bool Activar=false;
    char bloquear;

    do
    {
        system("cls");
        op=MenuCuentas();

        switch(op)
        {
        case 1: //Añadir una cuenta
            if(nCuentas>=MAX_CUENTAS)
            {
                cout << "\n\n->Limite de cuentas superado\n\n";
                system("pause");
            }
            else//Si se puede crear la cuenta
            {
                cout << "\n\n->Indique el numero de la cuenta: ";
                cin >> NumeroCuenta;
                ExisteLaCuenta=BuscarCuenta(DatosCuentas, nCuentas, NumeroCuenta);
                if(ExisteLaCuenta==-1)
                {
                    cout << "\n->Introduce el Saldo de la cuenta: ";
                    cin >> Saldo;
                    DatosCuentas[nCuentas] = Cuenta(NumeroCuenta, Saldo);
                    nCuentas++;
                    cout << "\n\n** CUENTA CREADA CORRECTAMENTE **\n";
                    system("pause");
                }
                else
                {
                    cout << "\n\nYA EXISTE ESE NUMERO DE CUENTA\n";
                    system("pause");
                }

            }
            break;

        case 2: //Mostrar las cuentas
            if(nCuentas==0)
            {
                cout << "\n\n->No hay ninguna cuenta creada\n\n";
                system("pause");
            }
            else
            {
                cout << "\n\n## REGISTRO DE CUENTAS ##\n\n";
                for(int i=0; i<nCuentas; i++)
                {
                    cout << "NoCuenta: " << DatosCuentas[i].DameNoCuenta();
                    cout << "\nSaldo: " << DatosCuentas[i].DameSaldo();
                    if(DatosCuentas[i].EstaBloqueada()==true)
                        cout << "\nEstado: Bloqueada";
                    else
                        cout << "\nEstado: No Bloqueada";
                    cout << "\n---------------\n";
                }
                cout << "\n\n";
                system("pause");
            }
            break;

        case 3: //Borrar una cuenta
            cout << "\n\n->Seleccione el No de Cuenta a eliminar: ";
            cin >> NumeroCuenta;
            ExisteLaCuenta=BuscarCuenta(DatosCuentas, nCuentas, NumeroCuenta);
            if(ExisteLaCuenta==-1)
            {
                cout << "\nNO SE HA ENCONTRADO NINGUNA CUENTA CON ESE NUMERO\n";
                system("pause");
            }
            else
            {
                nCuentas--;
                for(int i=ExisteLaCuenta; i<nCuentas; i++)
                    DatosCuentas[i]=DatosCuentas[i+1];

                cout << "\n\n->Cuenta eliminada exitosamente\n\n";
                system("pause");
            }
            break;

        case 4: //Modificar saldo
            cout << "\n\n->Indique el No de la cuenta a actualizar: ";
            cin >> NumeroCuenta;
            ExisteLaCuenta=BuscarCuenta(DatosCuentas, nCuentas, NumeroCuenta);
            if(ExisteLaCuenta==-1)
            {
                cout << "\nNO SE HA ENCONTRADO NINGUNA CUENTA CON ESE NUMERO\n";
                system("pause");
            }
            else
            {
                if(DatosCuentas[ExisteLaCuenta].EstaBloqueada()==true)
                {
                    cout << "\n->La cuenta se encuentra Bloqueada\n";
                    system("pause");
                }
                else
                {
                    cout << "\n->Introduzca el nuevo saldo de la cuenta: ";
                    cin >> Saldo;
                    DatosCuentas[ExisteLaCuenta].ActualizarSaldo(Saldo);
                    cout << "\n\n* Cambios guardados en la cuenta *\n\n";
                    system("pause");
                }
            }
            break;

        case 5: //Modificar estado
            cout << "\n\n->Indique el No de la cuenta a actualizar: ";
            cin >> NumeroCuenta;
            ExisteLaCuenta=BuscarCuenta(DatosCuentas, nCuentas, NumeroCuenta);
            if(ExisteLaCuenta==-1)
            {
                cout << "\nNO SE HA ENCONTRADO NINGUNA CUENTA CON ESE NUMERO\n";
                system("pause");
            }
            else
            {
                cout << "\n->Bloquear cuenta (s/n): ";
                cin >> bloquear;
                bloquear=toupper(bloquear);
                if(bloquear=='S')
                {
                    Activar=true;
                    DatosCuentas[ExisteLaCuenta].ActualizarBloqueo(Activar);
                    cout << "\n\n** Cuenta bloqueada\n";
                    system("pause");
                }
                else
                {
                    Activar=false;
                    DatosCuentas[ExisteLaCuenta].ActualizarBloqueo(Activar);
                    cout << "\n\n** Cuenta desbloqueada\n";
                    system("pause");
                }
            }
            break;

        case 6: break; //SALIR
        }
    }while(op!=6);
    return 0;
}


int BuscarCuenta(Cuenta Ctas[MAX_CUENTAS], int NCuentas, int NoCuenta)
{
    int encontrar = -1;
    int i=0;
    while(i<NCuentas && encontrar == -1)
    {
        if(NoCuenta == Ctas[i].DameNoCuenta())
            encontrar=i;

        else i++;
    }
    return encontrar;
}
