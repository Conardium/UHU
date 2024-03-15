#ifndef TALMACEN_H_INCLUDED
#define TALMACEN_H_INCLUDED

#include "TTipos.h"
#include "TADcola.h"
#include "TADlista.h"

using namespace std;

class TAlmacen
{
    Cadena Nombre;              ///Nombre del almac�n.
    Cadena Direccion;           ///Direcci�n del almac�n.
    fstream FicheProductos;     ///Fichero que almacena los productos del almac�n.
    int NProduc;                ///N�mero de productos que hay en el almac�n. Si el almac�n est� cerrado
                                ///debera tener valor -1
    cola Pedidos; //Colas
    lista Envios; //Listas

public:
    TAlmacen();     /*Constructor que debe inicializar los atributos de la clase.*/
    ~TAlmacen();    /*Destructor que cerrar� el almac�n en caso de que el usuario no lo haya hecho.*/

    void DatosAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen);  /*Devuelve los atributos nombre y direcci�n por par�metro.*/

    //bool CrearAlmacen(Cadena pNomFiche);  /*Crea un fichero binario vac�o con el nombre pasado por par�metro. Crea la cabecera del fichero
              //NO IMPLEMENTAR              y lo deja abierto para su utilizaci�n. Devuelve true si se ha creado.*/

    bool CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche);  /*Igual que el m�todo anterior, pero actualizando los
                                                                        atributos nombre y direcci�n con los valores pasados por par�metro.
                                                                        Devuelve true si ha podido crear el fichero.*/

    bool AbrirAlmacen(Cadena pNomFiche); /*Abre un fichero y actualiza los atributos de la clase con los datos de cabecera del fichero y lo
                                           deja abierto. No se puede abrir un fichero si previamente el almac�n est� abierto. Devuelve true
                                           si ha podido abrir el fichero.*/

    bool CerrarAlmacen(); /*Cierra el fichero e inicializa los atributos a valores iniciales. Devuelve true si se ha cerrado el almacen.*/

    bool EstaAbierto(); /*Devuelve true si el fichero est� abierto.*/

    int NProductos(); /*Devuelve el n�mero de productos.*/

    int BuscarProducto(Cadena pCodProd); /*Dado un c�digo de producto, devuelve la posici�n dentro del fichero donde se encuentra. Si no
                                           lo encuentra devuelve -1.*/

    TProducto ObtenerProducto(int pPos); /*Dada la posici�n devuelve el producto del fichero situado en dicha posici�n. Debe verificar
                                           previamente que la posici�n sea correcta. Si la posici�n no es correcta devolver� un producto cuyo
                                           c�digo tendr� el valor �NULO�.*/

    bool AnadirProducto(TProducto pProduc); /*Dado un producto, lo busca en el fichero y si no lo encuentra lo a�ade al final del fichero.
                                              Devuelve true si se ha a�adido el producto.*/

    bool ActualizarProducto(int pPos, TProducto pProduc); /*Dada la posici�n de un producto en el fichero lo actualiza con la informaci�n
                                                del producto pasado por par�metro. Devuelve true si se ha actualizado el producto. Se debe verificar
                                                previamente que la posici�n sea correcta.*/

    bool EliminarProducto(int pPos); /*Dado la posici�n de un producto en el fichero lo borra. Devuelve true si se ha podido borrar. Se
                                       debe verificar que la posici�n sea correcta. Luego hay que poner el ultimo producto en la posicion donde
                                       se borr� el producto de la posicion pPos*/



///--------------------------------- METODOS NUEVOS ---------------------------------------------

    //M�todo que carga la lista de env�os a partir del nombre del fichero que se le pasa por par�metro.
 //El fichero tiene una sucesi�n de elementos de tipo TPedido.
 bool CargarListaEnvios(Cadena Nomf); ///X

 //M�todo que carga la cola de pedidos a partir del nombre del fichero que se le pasa por par�metro.
 //El fichero tiene una sucesi�n de elementos de tipo TPedido.
 bool CargarColaPedidos(Cadena Nomf); ///X

 //A�adir� un nuevo pedido a la cola de pedidos.
 void AniadirPedido(TPedido p); ///X

 //M�todo que atiende los pedidos del producto en cuesti�n pendientes de suministrar con la cantidad
 //comprada por el almac�n, los incorpora a la lista de Env�os, eliminando de la cola de pedidos los
 //pedidos atendidos.
 //Si alg�n pedido es atendido parcialmente por que se acabe el producto, la cola se modificar�
 //modificando y dejando pendiente la cantidad correspondiente, introduciendo en la lista de Envios
 //la cantidad que se puede suministrar.
 //Si el producto comprado excede de la cantidad pendiente de servir en los pedidos, la cantidad
 //sobrante, entra en el Almac�n.
 bool AtenderPedidos(Cadena CodProd,int cantidadcomprada); ///X

 //Muestra el contenido completo, con todos los datos de los productos le�dos del almac�n, de la cola
 //si CodProd es '' o muestra los pedidos del Codprod pasado con todos sus datos del almac�n.
 void ListarPedidosCompleto(Cadena CodProd); ///X

 //Muestra la cantidad pendiente de cada tipo de producto si CodProd es '' o del producto concreto
 //que se pase por par�metro.
 void ListarCantidadesPendientes(Cadena CodProd);

 //Se encarga de meter en la lista de env�os, de forma ordenada, por nombre del fichero de tienda, el
 //pedido a enviar.
 bool InsertarEnvios(TPedido p); ///X

 //Se encarga de sacar de la lista los env�os que tienen por destino la tienda que se le pasa por
 //par�metro mostrando por pantalla los env�os que van en el cami�n.
 bool SalidaCamionTienda(Cadena NomTienda); ///X

 //Si recibe Nomtienda a '' muestra por pantalla todo el contenido de la lista de env�os.
 //Si se le pasa el nombre de una tienda muestra por pantalla los env�os a dicha tienda.
 void ListarListaEnvios(Cadena Nomtienda); ///X

 //M�todo que vuelca en el fichero nomf la cola de pedidos.
 bool SalvarColaPedidos(Cadena Nomf); ///X

 //M�todo que vuelca en el fichero nomf la lista de env�os.
 bool SalvarListaEnvios(Cadena Nomf); ///X

};

#endif // TALMACEN_H_INCLUDED
