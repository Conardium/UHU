#include "TAlmacen.h"
#include <stdlib.h>

TAlmacen::TAlmacen()
{
    strcpy(Nombre, "");
    strcpy(Direccion, "");
    NProduc = -1;
}


TAlmacen::~TAlmacen()
{
    if (EstaAbierto())
        FicheProductos.close();
}


void TAlmacen::DatosAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen)
{
    strcpy(pNombAlmacen, Nombre);
    strcpy(pDirAlmacen, Direccion);
}


bool TAlmacen::EstaAbierto()
{
    if(NProduc != -1) //Si Nproduc es -1 significa que el almacen está cerrado
        return true;
    else
        return false;
}


int TAlmacen::NProductos() {return NProduc;}


bool TAlmacen::CrearAlmacen(Cadena pNombAlmacen, Cadena pDirAlmacen, Cadena pNomFiche)
{
    FicheProductos.open(pNomFiche, ios::out|ios::binary);

    if(FicheProductos.fail())
        return false;
    else //Se ha podido crear
    {
        NProduc=0;
        FicheProductos.write((char*)&NProduc, sizeof(NProduc));
        strcpy(Nombre, pNombAlmacen);
        FicheProductos.write((char*)Nombre, sizeof(Nombre));
        strcpy(Direccion, pDirAlmacen);
        FicheProductos.write((char*)Direccion, sizeof(Direccion));
        FicheProductos.close();
        FicheProductos.open(pNomFiche, ios::in|ios::out|ios::binary);
        if(FicheProductos.fail())
        {
            NProduc=-1;
            return false;
        }
        else
            return true;
    }
}


bool TAlmacen::CerrarAlmacen()
{
    FicheProductos.close();
    NProduc = -1;
    strcpy(Nombre, "");
    strcpy(Direccion, "");
    return true;
}


bool TAlmacen::AbrirAlmacen(Cadena pNomFiche)
{
    FicheProductos.open(pNomFiche, ios::in|ios::out|ios::binary);
    if(FicheProductos.fail())
        return false;
    else //Se ha podido abrir
    {
        FicheProductos.read((char*)&NProduc, sizeof(NProduc));
        FicheProductos.read((char*)Nombre, sizeof(Nombre));
        FicheProductos.read((char*)Direccion, sizeof(Direccion));
        return true;
    }
}


int TAlmacen::BuscarProducto(Cadena pCodProd)
{
    int i=0;
    bool encontrado = false;
    TProducto prod;
    int posicion = -1;

    while(!encontrado && i < NProduc) //Mientras no se encuentre y no llegue al final de los productos
    {
        FicheProductos.seekg(sizeof(int)+sizeof(Cadena)*2+sizeof(TProducto)*i, ios::beg); //Buscamos el producto i-esimo
        FicheProductos.read((char*)&prod, sizeof(TProducto)); //Leemos el producto i-esimo y lo guardamos en prod

        if(strcmp(prod.CodProd, pCodProd)==0) //Si lo encuentra
        {
            encontrado=true;
            posicion=i;
        }
        else //Si no lo encuentra sigue buscando
            i++;
    }

    return posicion;
}


TProducto TAlmacen::ObtenerProducto(int pPos)
{
    TProducto prod;
    if(pPos >= 0 && pPos < NProduc)
    {
        FicheProductos.seekg(sizeof(int)+sizeof(Cadena)*2+sizeof(TProducto)*(pPos), ios::beg); // Nos colocamos en la pos del producto
        FicheProductos.read((char*)&prod, sizeof(TProducto)); //Guardamos la info del producto de esa posicion en prod
    }
    else
        cout << strcpy(prod.CodProd, "NULO");

    return prod;
}


bool TAlmacen::AnadirProducto(TProducto pProduc)
{
    FicheProductos.seekp(sizeof(int)+sizeof(Cadena)*2+sizeof(TProducto)*NProduc, ios::beg); //Nos colocamos en el ultimo producto
    FicheProductos.write((char*)&pProduc, sizeof(TProducto)); //Escribimos el nuevo producto
    NProduc++; //Aumenta el numero de productos
    FicheProductos.seekp(0, ios::beg); //Nos colocamos al principio para cambiar el Nproduc del fichero
    FicheProductos.write((char*)&NProduc, sizeof(int));
    return true;

}


bool TAlmacen::ActualizarProducto(int pPos, TProducto pProduc)
{
    FicheProductos.seekp(sizeof(int)+sizeof(Cadena)*2+sizeof(TProducto)*pPos, ios::beg); //Nos colocamos en el producto a actualizar
    FicheProductos.write((char*)&pProduc, sizeof(TProducto)); //Machacamos la info del antiguo producto con el nuevo
    return true;
}


bool TAlmacen::EliminarProducto(int pPos)
{
    TProducto p;

    FicheProductos.seekg(sizeof(int)+sizeof(Cadena)*2+sizeof(TProducto)*(NProduc-1), ios::beg); //Nos colocamos en el ultimo producto
    FicheProductos.read((char*)&p, sizeof(TProducto)); //Copiamos en 'p' los datos del ultimo producto

    ActualizarProducto(pPos, p);

    NProduc--;
    FicheProductos.seekp(0, ios::beg); //Nos colocamos al principio;
    FicheProductos.write((char*)&NProduc, sizeof(int)); //Escribimos el nuevo NProduc en el fichero
    return true;
}


/// MODIFICACION
void TAlmacen::Modificacion(TFecha fini, TFecha ffin)
{
    //Vamos a buscar en todos los productos
    cout << "\nProductos que caducan entre los años \n" << fini.Anyo << " y " << ffin.Anyo << endl;
    cout << "-----------------------------------------------------------------\n";
    cout << "NOMBRE\t\tFECHA CADUCIDAD\n";
    for(int i=0; i<NProduc; i++)
    {
        TProducto producto; //Para poder guardar la informacion del producto del fichero

        //Nos situamos en el producto i-esimo
        FicheProductos.seekg(sizeof(int)+sizeof(Cadena)*2+sizeof(TProducto)*i, ios::beg);
        FicheProductos.read((char*)&producto, sizeof(TProducto)); //Guardamos el producto i-esimo en producto

        //Vemos si la fecha de caducidad de ese producto está entre las indicadas por parametro
        if((producto.Caducidad.Anyo >= fini.Anyo) && (producto.Caducidad.Anyo <= ffin.Anyo))
        { //Si la fecha está entre las indicadas...
            ofstream ficheCaduce;
            if(ficheCaduce)
            {
                ficheCaduce.open("Modificacion.dat", ios::binary); //Lo habrimos solo para lectura
                ficheCaduce.write((char*)&producto, sizeof(TProducto)); //Escribimos el producto en el fichero
                cout << producto.NombreProd << "\t\t" << producto.Caducidad.Anyo << endl; //Para que lo  muestre por pantalla, para ver que esta bien
            }
        }
        //En caso contrario no escribe en el fichero
    }
}







