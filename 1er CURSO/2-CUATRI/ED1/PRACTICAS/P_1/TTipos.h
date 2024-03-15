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
    Cadena CodProd;         ///Código de producto.
    int Cantidad;           ///Cantidad en el almacén.
    Cadena NombreProd;      ///Nombre del producto.
    float Precio;           ///Precio por unidad.
    Cadena Descripcion;     ///Descripción opcional del producto.
    TFecha Caducidad;       ///Caducidad del producto.
};

struct TEstante //Usado por la TIENDA
{
    int CodEstante;         ///Código del estante.
    int Posicion;           ///Posición del estante:    valores (1-centrado, 2- arriba, 3- abajo).
    int Capacidad;          ///Máxima capacidad que admite el estante.
    Cadena CodProd;         ///Código del producto que contiene el estante.
    int NoProductos;        ///Número de productos que hay en el estante.
};

#endif // TTIPOS_H_INCLUDED
