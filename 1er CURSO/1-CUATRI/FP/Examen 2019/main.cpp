// NOMBRE COMPLETO DEL ALUMNO: ISMAEL DA PALMA FERNÁNDEZ
// MODELO A

#include <iostream>
#include <cstring>
#include <cstdlib>

#define MAX 50        // Cantidad máxima de Abonados para la Sala de cine

using namespace std;

typedef char Cadena[20];

//----------------------- CLASE TAbonado -------------------------------------
class TAbonado
{
    Cadena Nombre;             // Nombre del Abonado
    int Entradas;              // Nº de entradas que le quedan por gastar
public:
    TAbonado();
    void setNombre(Cadena N);
    void getNombre(Cadena N);
    void actualizaEntradas(int Entr, char op);
    int getEntradas();
};

TAbonado::TAbonado()
{
    strcpy(Nombre, "");
    Entradas=0;
}

void TAbonado::setNombre(Cadena N)
{
    strcpy(Nombre, N);
}

void TAbonado::getNombre(Cadena N)
{
    strcpy(N, Nombre);
}

void TAbonado::actualizaEntradas(int Entr, char op)
{
    if (op == '+')
      Entradas=Entradas+Entr;
    else if (op== '-')
      Entradas=Entradas-Entr;
}

int TAbonado::getEntradas()
{
    return Entradas;
}



//------------------------------ CLASE TCine ------------------------------
class TCine
{
    TAbonado Abonados[MAX];  // Contiene la información de hasta 50 abonados
    int NumAb;               // Almacena el número actual de abonados
public:
    TCine();
    int getNumAb();
    int buscarAbonado(Cadena Nombre);
    void verAbonado(int Pos);
    int nuevoAbonado(TAbonado A);
    TAbonado getAbonado(int Pos);
    bool comprarEntradas(Cadena Nombre, int cantidad);
    int gastarEntradas(Cadena Nombre, int Ninvitados);
};

TCine::TCine()
{
    NumAb=0;
}

int TCine::getNumAb()
{
    return NumAb;
}

int TCine::buscarAbonado(Cadena Nombre)   // --- A IMPLEMENTAR POR EL ALUMNO
{
    int i=0;
    bool encontrado=false;
    int pos;
    Cadena NombreAb;

    while(i<NumAb && !encontrado)
    {
        Abonados[i].getNombre(NombreAb);
        if(strcmp(NombreAb, Nombre)==0)
        {
            encontrado=true;
            pos=i;
        }
        else
            i++;
    }
    if(!encontrado)
    pos=-1;

    return pos;
}

void TCine::verAbonado(int Pos)
{
    cout << "Datos del Abonado nº " << Pos << ": " << endl;
    Cadena Nombre;
    Abonados[Pos].getNombre(Nombre);
    cout << "\t\tNombre: " << Nombre << endl;
    cout << "\t\tTikets por gastar: " << Abonados[Pos].getEntradas()<<endl;
    cout <<endl;
}

int TCine::nuevoAbonado(TAbonado A)  // --- A IMPLEMENTAR POR EL ALUMNO
{
    //0 si se ha podido insertar, 1 si existe, 2 si esta lleno
    int resultado;
    Cadena Nombre;

    if(NumAb==MAX) //LA TABLA ESTA LLENA
        resultado=2;
    else
    {
        A.getNombre(Nombre);
        if(buscarAbonado(Nombre)!=-1) //SI EXISTE EL ABONADO
            resultado=1;
        else //SE PUEDE AÑADIR
        {
            NumAb++;
            Abonados[NumAb-1]=A;
            resultado=0;
        }

    }
    return resultado;
}

TAbonado TCine::getAbonado(int Pos)
{
    return Abonados[Pos];
}

bool TCine::comprarEntradas(Cadena Nombre, int cantidad)
{
    int Pos=buscarAbonado(Nombre);
    bool Ok=false;
    if (Pos>=0)
    {
        Abonados[Pos].actualizaEntradas(cantidad, '+');
        Ok=true;
    }
    return Ok;
}

int TCine::gastarEntradas(Cadena Nombre, int Ninvitados)  // --- A IMPLEMENTAR POR EL ALUMNO
{
    int resultado;
    int EntradasFinales;
    int Pos=buscarAbonado(Nombre);
    if(Pos==-1)//SI EL ABONADO NO EXISTE EN LA TABLA
        resultado=1;
    else
    {
        Abonados[Pos].actualizaEntradas(Ninvitados+1, '-');
        EntradasFinales=Abonados[Pos].getEntradas();
        if(EntradasFinales<0)//NO TIENE ENTRADAS SUFICIENTES
        {
            Abonados[Pos].actualizaEntradas(Ninvitados+1, '+');
            resultado=2;
        }

        else //ENTRADAS SUFICIENTES
            resultado=0;
    }

    return resultado;
}

int Menu()
{
    int Opcion;
    do
    {   system("cls");
        cout<<"Menú Principal\n"
            <<"**************\n\n"
            <<"1.- Añadir Abonado\n"
            <<"2.- Listar Abonados\n"
            <<"3.- Comprar Entradas\n"
            <<"4.- Asistir a una película\n"
            <<"0.- Salir\n"
            <<"Elige Opcion: ";
        cin >>Opcion;
    }while (Opcion<0 || Opcion>4);
    return Opcion;
}

int main()
{
    TCine SalaCine;
    TAbonado A;
    int Opcion;
    Cadena Nombre;
    int Nentradas;
    int NumAb;
    int ok;
    bool OK;
    int NAcompana;

    do
    {
        Opcion=Menu();
        switch(Opcion)
        {
            case 1: // --- A IMPLEMENTAR POR EL ALUMNO
                cout << "Indica el nombre del abonado: ";
                cin >> Nombre;
                cout << "\nIndica el numero de entradas: ";
                cin >> Nentradas;
                A.setNombre(Nombre);
                ok=SalaCine.nuevoAbonado(A);
                if(ok==1)
                    cout << "\n\nEL ABONADO YA EXISTE\n";
                else if(ok==2)
                    cout << "\n\nEL VECTOR ESTA LLENO\n";
                else
                {
                    cout << "\nAbonado insertado correctamente\n";
                    SalaCine.comprarEntradas(Nombre, Nentradas);
                }

                break;

            case 2: //----------- A IMPLEMENTAR POR EL ALUMNO
                NumAb=SalaCine.getNumAb();
                if(NumAb==0)
                    cout << "\nNo hay datos para mostrar, la tabla esta vacia\n";
                else
                {
                    int i;
                    for(i=0; i<NumAb; i++)
                        SalaCine.verAbonado(i);
                }

                break;
  //----------------------------------------------------------- YA REALIZADO
            case 3: cout<<"Introduce Nombre: ";
                    cin>>Nombre;
                    cout<<"Introduce el número de entradas a comprar: ";
                    cin>>Nentradas;
                    OK=SalaCine.comprarEntradas(Nombre,Nentradas);
                    if (!OK)
                        cout<<"No existe un abonado de la sala con el nombre "<<Nombre<<endl;
                    else
                        cout<<"Venta finalizada. \n";
                    break;
            case 4: cout<<"Introduce Nombre: ";
                    cin>>Nombre;
                    cout<<"Introduce el número de acompañantes vienen con el abonado: ";
                    cin>>NAcompana;
                    ok=SalaCine.gastarEntradas(Nombre,NAcompana);
                    if (ok == 0)
                        cout<<"El abonado y su/s acompañante/s puede/n pasar a la sala\n";
                    else if (ok == 1)
                        cout<<"El nombre del abonado no existe\n";
                    else
                        cout<<"El abonado "<<Nombre<<" no tiene entradas suficientes.\n";
                    break;
        }
        if (Opcion!=0)
        {
             cout << endl;
             system("pause");
        }
    } while (Opcion!=0);


    return 0;
}

