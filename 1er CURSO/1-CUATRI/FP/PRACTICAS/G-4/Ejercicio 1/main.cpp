/*1.Se va a realizar un programa en c++ para controlar los productos guardados en un almacén. De cada
producto tendremos su nombre, su precio y la cantidad que hay en el almacén. Para ello en
principio diseñaremos una clase tprod que servirá para tratar un producto, y a continuación
pasaremos a realizar otra clase en el mismo programa que maneje una tabla de productos que será
el almacén.*/

#include <iostream>
#include <string.h>
#include <stdlib.h>

#define MAX 5

using namespace std;
typedef char cad[20];

class tprod //ESTA CLASE SIRVE PARA TRATAR UN PRODUCTO
{
    cad nombre;
    float precio;
    int stock;
public:
    tprod(); //Constructor
    void cambiarnombre(cad nom);
    void cambiarprecio(float prec);
    void cambiarstock(int stoc);
    void leenombre (cad nom);
    float leeprecio();
    void leestock(int &st);
    void vender(int cantidad);
};

class almacen //ALMACENA TODOS LOS PRODUCTOS
{
    tprod productos[MAX];
    int nprod;
public:
    almacen();
    void vaciar();
    int existe(cad nom);
    void verprod (int pos, tprod &prod);
    int insertar(tprod P);
    void vertabla();
    void vender(int pos, int cant);
    char menu();
};

// CLASS TPROD ----------------------------------
tprod::tprod()
{
    strcpy(nombre, "NO HAY PRODUCTO");
    precio=0;
    stock=0;
}

void tprod::cambiarnombre(cad nom)
{
    strcpy(nombre, nom);
}

void tprod::cambiarprecio(float prec)
{
    precio=prec;
}

void tprod::cambiarstock(int stoc)
{
    stock=stoc;
}

void tprod::leenombre(cad nom)
{
    strcpy(nom, nombre);
}

float tprod::leeprecio()
{
    return precio;
}

void tprod::leestock(int &st)
{
    st=stock;
}

void tprod::vender(int cantidad)
{
    stock=stock-cantidad;
    cout << "\nEl precio a pagar es: "<< cantidad*precio << char(36);
    cout << "\nEn el stock queda: " << stock << endl;
}
// ---------------------------------------------------

//CLASS ALMACEN -------------------
almacen::almacen()
{
    vaciar();
}

void almacen::vaciar()
{
    nprod=0;
}

int almacen::existe(cad nom)
{
    int pos =-1;
    int i=0;
    cad original;
    while(i<nprod && pos==-1)
    {
        productos[i].leenombre(original);
        if(strcmp(nom, original)==0)
            pos=i;
        else
            i++;
    }
    return pos;
}

void almacen::verprod(int pos, tprod &prod)
{
    prod=productos[pos];
}

int almacen::insertar(tprod P)
{
    //LLENA(2)  //YA EXISTE(1) // INSERTADO(0)
    int resultado;

    if(nprod==MAX)
        resultado=2;

    else
    {
        cad nombre;
        P.leenombre(nombre);
        if(existe(nombre) != -1)
            resultado=1;
        else
        {
            productos[nprod++]= P;
            resultado=0;
        }
    }
    return resultado;
}

void almacen::vertabla()
{
    if(nprod==0)
        cout << "\nEl almacen esta vacio" << endl;
    else
    {
        cad nombre;
        int stock;
        for(int i=0; i<nprod; i++)
        {
            productos[i].leenombre(nombre);
            productos[i].leestock(stock);
            cout << "\nNombre: " << nombre <<endl;
            cout << "Precio: " << productos[i].leeprecio() <<char(36) << endl;
            cout << "Stock: " << stock << endl;
            cout << "----------" << endl;
        }
    }
}

void almacen::vender(int pos, int cant)
{
    int stock; //Cantidad del almacen
    productos[pos].leestock(stock);
    float precio=productos[pos].leeprecio();

    if(cant <= stock)
    {
        productos[pos].cambiarstock(stock-cant);
        cout << "\nTotal a pagar: " << cant*precio << endl;
    }
    else
        cout << "No hay suficiente cantidad en el stock"<<endl;
}

char almacen::menu()
{
    char op;
    cout << "\n*************** MENU ***************"
         << "\n*******A.- Visualizar la tabla.  ***"
         << "\n*******B.- Insertar un producto. ***"
         << "\n*******C.- Vender un producto.   ***"
         << "\n*******D.- Vaciar almacen.       ***"
         << "\n*******E.- Salir.                ***"
         << "\n************************************"
         << "\nPon la opcion que deseas: ";
         cin >> op;
         op=toupper(op);
    return op;
}


// MAIN --------------
int main()
{
    almacen ALM;
    tprod PROD;
    cad nombre;
    float precio;
    int stock, pos;
    char op;

    do
    {
        system("cls");
        op=ALM.menu();
        switch(op)
    {
    case'A': //Visualizar tabla
        system("cls");
        ALM.vertabla();
        system("pause");
        break;

    case 'B': //Insertar producto
        system("cls");
        cout << "Introduzca el nombre del producto: ";
        cin >> nombre;
        PROD.cambiarnombre(nombre);
        cout << "Introduce el precio: ";
        cin >>precio;
        PROD.cambiarprecio(precio);
        cout<< "Introduce el stock: ";
        cin >> stock;
        PROD.cambiarstock(stock);

        switch(ALM.insertar(PROD))
        {
        case 2:
            cout << "\nEl almacen esta lleno" << endl;
            break;

        case 1:
            cout << "\nEL producto ya existe" << endl;
            break;

        case 0:
            cout << "\nProducto creado con exito" << endl;
            break;
        }
        system("pause");
        break;

    case 'C': //Vender producto
        system("cls");
        cout << "\nIndique el nombre del producto a comprar: ";
        cin >> nombre;
        pos=ALM.existe(nombre);
        if(pos==-1)
            cout << "\nEl producto introducido no existe" << endl;
        else
        {
            cout << "\nIntroduzca la cantidad que desea: ";
            cin >> stock;
            ALM.vender(pos, stock);
        }
        system("pause");
        break;

    case 'D': // Vaciar almacen
        system("cls");
        ALM.vaciar();
        cout << "\nAlmacen vaciado con exito"<<endl;
        system("pause");
        break;

    case 'E': break; //SALIR

    default: cout << "\nError, elige una opcion correcta\n"; system("pause");
    }
    }while(op!='E');


    return 0;
}
