#ifndef TTIENDA_H_INCLUDED
#define TTIENDA_H_INCLUDED

#include "TTipos.h"

using namespace std;

class TTienda
{
    Cadena Nombre;          ///Nombre de la tienda.
    Cadena Direccion;       ///Dirección de la tienda.
    Cadena NomFiche;        ///Nombre del fichero que contiene los datos de la tienda.
    TEstante *Estantes;     ///Vector dinámico de estantes.
    int NEstan;             ///Número de estantes ocupados en el vector dinámico.
    int Tamano;             ///Tamaño de los estantes del vector.

    //Método privado para ordenar los estantes del vector. La ordenación se realizará por el código de
    //producto en orden ascendente. En caso de tener el mismo código se ordenará por la posición del
    //producto en el estante en orden ascendente.
    void OrdenarProductos();

public:
    TTienda(); /*Constructor que debe inicializar los atributos de la clase.*/
    ~TTienda(); /*Destructor que cerrará la tienda en caso de que el usuario no lo haya hecho.*/

    //Devuelve los atributos nombre y dirección por parámetro.
    void DatosTienda(Cadena pNombTienda, Cadena pDirTienda);

    //Crea un fichero binario vacío con el nombre pasado por parámetro e inicializa los atributos nombre y
    //dirección mediante los parámetros y a continuación lo cerrará. Devolverá true si ha podido crear el
    //fichero.
    bool CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche);

    //Abre un fichero y lo carga a memoria. Si ya había un fichero previamente cargado, guardará los datos
    //de la tienda y procederá a cargar el nuevo fichero. Si el fichero es el mismo que el que está en
    //memoria, eliminará los datos y procederá a cargar nuevamente los datos del fichero. Devolverá true
    //si se ha podido cargar el fichero.
    bool AbrirTienda(Cadena pNomFiche);

    //Vuelca los datos de la memoria al fichero. Devolverá true si se han guardado los datos.
    bool GuardarTienda();

    //Guarda los datos de la memoria en el fichero y borra todos los atributos del objeto. Devuelve true
    //si se ha podido guardar los datos.
    bool CerrarTienda();

    bool EstaAbierta(); /*Devuelve true si la tienda está abierta.*/

    int NoEstantes(); /*Devuelve el número de estantes de la tienda.*/

    //Dado un código de estante, devuelve la posición dentro del vector donde se encuentra. Si no lo
    //encuentra devuelve -1.
    int BuscarEstante(int pCodEstante);

    //Devuelve el estante cuya posición es indicada por parámetro.
    TEstante ObtenerEstante(int pPos);

    //Añade un estante nuevo al vector siempre que el estante no esté previamente almacenado en memoria.
    //El vector de estantes debe siempre estar ordenado. Devolverá true si se ha añadido el estante.
    bool AnadirEstante(TEstante pEstante);

    //Dado la posición de un estante lo borra desplazando el resto de estantes una posición hacia abajo.
    //Se debe verificar previamente que la posición sea correcta. Devuelve true si se ha eliminado el
    //estante.
    bool EliminarEstante(int pPos);

    //Dada la posición de un estante, lo actualiza con los datos pasados por parámetros. Se debe verificar
    //previamente que la posición sea correcta Devuelve true si se actualiza el estante.
    bool ActualizarEstante(int pPos, TEstante pEstante);

    //Dada la posición de un estante y un producto del almacén, actualizará el número de productos del
    //estante a su máxima capacidad si hay suficientes unidades en el producto, en caso contrario se
    //añadirán al estante la totalidad de unidades que estén en el producto del almacén. El mismo número
    //de unidades añadidas en el estante deben reducirse del producto. Se debe verificar previamente que
    //la posición sea correcta. El método devuelve:
    // 0 si producto no tiene elementos.
    // 1 si se ha repuesto unidades hasta llegar a la capacidad máxima del estante.
    // 2 si no se ha completado el estante al completo.
    int ReponerEstante(int pPos, TProducto &pProduc); ///OPCION 3 del menu principal


    ///---------------------NUEVO---------------

    //Devuelve el atributo del nombre del fichero de la tienda
    void NombreFicheroTienda(Cadena NomF);
};

#endif // TTIENDA_H_INCLUDED
