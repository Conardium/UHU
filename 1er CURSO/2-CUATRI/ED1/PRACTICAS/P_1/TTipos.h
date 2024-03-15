#ifndef TTIPOS_H_INCLUDED
#define TTIPOS_H_INCLUDED

#include <string.h>
#include <iostream>
#include <fstream>

typedef char Cadena[90];
#define Incremento 4

struct TFecha  //Almacena una fecha
{
    int Dia;
    int Mes;
    int Anyo;
};

struct TProducto //Usado por el ALMACEN
{
    Cadena CodProd;         ///C�digo de producto.
    int Cantidad;           ///Cantidad en el almac�n.
    Cadena NombreProd;      ///Nombre del producto.
    float Precio;           ///Precio por unidad.
    Cadena Descripcion;     ///Descripci�n opcional del producto.
    TFecha Caducidad;       ///Caducidad del producto.
};

struct TEstante //Usado por la TIENDA
{
    int CodEstante;         ///C�digo del estante.
    int Posicion;           ///Posici�n del estante:    valores (1-centrado, 2- arriba, 3- abajo).
    int Capacidad;          ///M�xima capacidad que admite el estante.
    Cadena CodProd;         ///C�digo del producto que contiene el estante.
    int NoProductos;        ///N�mero de productos que hay en el estante.
};

#endif // TTIPOS_H_INCLUDED
