#ifndef TTIENDA_H_INCLUDED
#define TTIENDA_H_INCLUDED

#include "TTipos.h"

using namespace std;

class TTienda
{
    Cadena Nombre;          ///Nombre de la tienda.
    Cadena Direccion;       ///Direcci�n de la tienda.
    Cadena NomFiche;        ///Nombre del fichero que contiene los datos de la tienda.
    TEstante *Estantes;     ///Vector din�mico de estantes.
    int NEstan;             ///N�mero de estantes ocupados en el vector din�mico.
    int Tamano;             ///Tama�o de los estantes del vector.

    //M�todo privado para ordenar los estantes del vector. La ordenaci�n se realizar� por el c�digo de
    //producto en orden ascendente. En caso de tener el mismo c�digo se ordenar� por la posici�n del
    //producto en el estante en orden ascendente.
    void OrdenarProductos();

public:
    TTienda(); /*Constructor que debe inicializar los atributos de la clase.*/
    ~TTienda(); /*Destructor que cerrar� la tienda en caso de que el usuario no lo haya hecho.*/

    //Devuelve los atributos nombre y direcci�n por par�metro.
    void DatosTienda(Cadena pNombTienda, Cadena pDirTienda);

    //Crea un fichero binario vac�o con el nombre pasado por par�metro e inicializa los atributos nombre y
    //direcci�n mediante los par�metros y a continuaci�n lo cerrar�. Devolver� true si ha podido crear el
    //fichero.
    bool CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche);

    //Abre un fichero y lo carga a memoria. Si ya hab�a un fichero previamente cargado, guardar� los datos
    //de la tienda y proceder� a cargar el nuevo fichero. Si el fichero es el mismo que el que est� en
    //memoria, eliminar� los datos y proceder� a cargar nuevamente los datos del fichero. Devolver� true
    //si se ha podido cargar el fichero.
    bool AbrirTienda(Cadena pNomFiche);

    //Vuelca los datos de la memoria al fichero. Devolver� true si se han guardado los datos.
    bool GuardarTienda();

    //Guarda los datos de la memoria en el fichero y borra todos los atributos del objeto. Devuelve true
    //si se ha podido guardar los datos.
    bool CerrarTienda();

    bool EstaAbierta(); /*Devuelve true si la tienda est� abierta.*/

    int NoEstantes(); /*Devuelve el n�mero de estantes de la tienda.*/

    //Dado un c�digo de estante, devuelve la posici�n dentro del vector donde se encuentra. Si no lo
    //encuentra devuelve -1.
    int BuscarEstante(int pCodEstante);

    //Devuelve el estante cuya posici�n es indicada por par�metro.
    TEstante ObtenerEstante(int pPos);

    //A�ade un estante nuevo al vector siempre que el estante no est� previamente almacenado en memoria.
    //El vector de estantes debe siempre estar ordenado. Devolver� true si se ha a�adido el estante.
    bool AnadirEstante(TEstante pEstante);

    //Dado la posici�n de un estante lo borra desplazando el resto de estantes una posici�n hacia abajo.
    //Se debe verificar previamente que la posici�n sea correcta. Devuelve true si se ha eliminado el
    //estante.
    bool EliminarEstante(int pPos);

    //Dada la posici�n de un estante, lo actualiza con los datos pasados por par�metros. Se debe verificar
    //previamente que la posici�n sea correcta Devuelve true si se actualiza el estante.
    bool ActualizarEstante(int pPos, TEstante pEstante);

    //Dada la posici�n de un estante y un producto del almac�n, actualizar� el n�mero de productos del
    //estante a su m�xima capacidad si hay suficientes unidades en el producto, en caso contrario se
    //a�adir�n al estante la totalidad de unidades que est�n en el producto del almac�n. El mismo n�mero
    //de unidades a�adidas en el estante deben reducirse del producto. Se debe verificar previamente que
    //la posici�n sea correcta. El m�todo devuelve:
    // 0 si producto no tiene elementos.
    // 1 si se ha repuesto unidades hasta llegar a la capacidad m�xima del estante.
    // 2 si no se ha completado el estante al completo.
    int ReponerEstante(int pPos, TProducto &pProduc); ///OPCION 3 del menu principal


    ///---------------------NUEVO---------------

    //Devuelve el atributo del nombre del fichero de la tienda
    void NombreFicheroTienda(Cadena NomF);
};

#endif // TTIENDA_H_INCLUDED
