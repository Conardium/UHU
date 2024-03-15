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


///--------------------------------- METODOS NUEVOS ---------------------------------------------

bool TAlmacen::CargarListaEnvios(Cadena Nomf)
{
    ifstream f;
    TPedido e;
    f.open(Nomf, ios::binary); //Intentamos abrir el fichero
    if(f.fail())
        return false;
    else //Abierto con exito
    {
        //Contamos cuantos pedidos hay en la tienda
        f.seekg(0,ios::end);
        int n = f.tellg();
        n /= sizeof(TPedido);
        cout << "\n\n...Hay " << n << " envios pendientes...\n\n";
        f.seekg(0,ios::beg);

        f.read((char*)&e, sizeof(TPedido)); //Cargamos el primer envio
        int i = 1;
        while(!f.eof())
        {
            Envios.insertar(i,e);
            f.read((char*)&e, sizeof(TPedido));
            i++;
        }
        f.close();
        return true;
    }
}


void TAlmacen::ListarListaEnvios(Cadena Nomtienda)
{
    if(!Envios.esvacia()) //Si la lista esta vacia, no mostramos nada
    {
        TPedido p;
        TProducto prod;
        int pos;
        //Primero vemos si me han pasado o no nombre de la tienda
        if(strcmp(Nomtienda,"")==0)
        { //Muestra el contenido completo de los envios
            cout << "\n\t\t*** LISTADO DE LOS ENVIOS PENDIENTES ***\n"
                 << "---------------------------------------------------------------------------------\n"
                 << "PRODUCTO\tNOMBRE DEL PRODUCTO\tCANTIDAD PEDIDA\t\tPRODUCTOS DEL ALMACEN\tTIENDA\n";
            for(int i=1; i<=Envios.longitud(); i++)
            {
                p = Envios.observar(i); //Guardamos en p el envio i-esimo-1

                //Buscamos el producto en el almacen
                pos = BuscarProducto(p.CodProd);

                if(pos != -1)
                {
                    prod = ObtenerProducto(pos); //Obtenemos el producto encontrado en el almacen
                    cout << p.CodProd << "\t\t" << prod.NombreProd << "\t\t" << p.CantidadPed << "\t\t\t\t" << prod.Cantidad << "\t\t" << p.Nomtienda << endl; //Mostramos el producto
                }
                else
                    cout << p.CodProd << "\t\t" << "??????????????" << "\t\t" << p.CantidadPed << "\t\t\t\t" << "??\t\t" << p.Nomtienda << endl;
            }
            cout << endl << endl;
        }
        //Si recibe un nombre, primero vemos si existe ese fichero
        else
        {
            TPedido p;
            int i=1;
            bool encontrado=false;
            cout << "\n\t\t*** LISTADO DE LOS ENVIOS PENDIENTES DE \"" << Nomtienda << "\" ***\n"
                 << "---------------------------------------------------------------------------------\n"
                 << "PRODUCTO\tNOMBRE DEL PRODUCTO\tCANTIDAD PEDIDA\t\tPRODUCTOS DEL ALMACEN\tTIENDA\n";
            while(i<=Envios.longitud())
            {
                p = Envios.observar(i);
                if(strcmp(p.Nomtienda, Nomtienda)==0) //Si el producto es de la tienda pasada por parametro entramos
                {
                    //Buscamos el prodcuto en el almacen
                    pos = BuscarProducto(p.CodProd);

                    if(pos != -1) //Si existe
                    {
                        prod = ObtenerProducto(pos);
                        cout << p.CodProd << "\t\t" << prod.NombreProd << "\t\t" << p.CantidadPed << "\t\t\t\t" << prod.Cantidad << "\t\t" << p.Nomtienda << endl; //Mostramos el producto
                        encontrado = true;
                    }
                    else
                        cout << p.CodProd << "\t\t" << "??????????????" << "\t\t" << p.CantidadPed << "\t\t\t\t" << "??\t\t" << p.Nomtienda << endl;
                }
                i++;
            }
            cout << endl << endl;
            if(!encontrado)
                cout << "\n...No se ha encontrado la tienda...\n";
        }
    }
    else
        cout << "\nLa lista de envios esta vacia\n";
}


bool TAlmacen::CargarColaPedidos(Cadena Nomf)
{
    ifstream f;
    TPedido e;
    f.open(Nomf, ios::binary); //Intentamos abrir el fichero
    if(f.fail())
        return false;
    else //Abierto con exito
    {
        //Contamos cuantos pedidos hay en la tienda
        f.seekg(0,ios::end);
        int n = f.tellg();
        n /= sizeof(TPedido);
        cout << "\n\n...Hay " << n << " pedidos pendientes...\n\n";
        f.seekg(0,ios::beg);

        f.read((char*)&e, sizeof(TPedido)); //Cargamos el primer pedido
        int i = 0;
        while(!f.eof())
        {
            Pedidos.encolar(e); //Encolamos el pedido i-esimo
            f.read((char*)&e, sizeof(TPedido));
            i++;
        }
        f.close();
        return true;
    }
}


void TAlmacen::ListarPedidosCompleto(Cadena CodProd)
{
    TProducto prod;
    int i=1;

    if(!Pedidos.esvacia()) //Si la cola esta vacia, no mostramos nada
    {
        //Primero vemos si me han pasado o no un codigo de producto
        if(strcmp(CodProd,"")==0)
        { //Muestra el contenido completo de los pedidos
            TPedido p;
            cout << "\n\t\t*** LISTADO DE LOS PEDIDOS PENDIENTES ***\n"
                 << "----------------------------------------------------------------------\n"
                 << "PRODUCTO\t\tNOMBRE DEL PRODUCTO\tCANTIDAD PEDIDA\t\tPRODUCTOS DEL ALMACEN\tTIENDA\n";
            while(i<=Pedidos.longitud())
            {
                p = Pedidos.primero(); //Guardamos en p el primer pedido de la cola

                    int pos = BuscarProducto(p.CodProd);
                    if(pos != -1)
                    {
                        prod = ObtenerProducto(pos);
                        cout << p.CodProd << "\t\t\t" << prod.NombreProd << "\t\t" << p.CantidadPed << "\t\t\t\t" << prod.Cantidad << "\t\t" << p.Nomtienda << endl; //Mostramos el producto
                    }
                    else
                        cout << p.CodProd << "\t\t\t" << "??????????????" << "\t\t" << p.CantidadPed << "\t\t\t\t" << "??\t\t" << p.Nomtienda << endl;

                Pedidos.desencolar(); //Quitamos el primer elemento de la cola
                Pedidos.encolar(p); //Ese elemento que desencolamos lo encolamos al final
                i++;
            }
            cout << endl << endl;
        }

        //Si recibe un codigo, primero vemos si existe el codigo del producto en los pedidos
        else
        {
            TPedido p;
            int i=1;
            cout << "\n\t\t*** PEDIDOS PENDIENTES PARA EL PRODUCTO \"" << CodProd << "\" ***\n"
                << "----------------------------------------------------------------------------------\n"
                << "PRODUCTO\t\tNOMBRE DEL PRODUCTO\tCANTIDAD PEDIDA\t\tPRODUCTOS DEL ALMACEN\tTIENDA\n";
            while(i<=Pedidos.longitud())
            {
                p = Pedidos.primero();

                int pos = BuscarProducto(p.CodProd); //Buscamos a ver si existe el producto

                if(pos != -1) //Si existe
                {
                    if(strcmp(p.CodProd, CodProd)==0) //Si existe en la cola de pedidos
                    {
                        prod = ObtenerProducto(pos);
                        cout << p.CodProd << "\t\t\t" << prod.NombreProd << "\t\t" << p.CantidadPed << "\t\t\t\t" << prod.Cantidad << "\t\t" << p.Nomtienda << endl; //Mostramos el producto
                    }
                }
                else //Si no existe en el almacen
                {
                    if(strcmp(p.CodProd, CodProd)==0) //Si existe en la cola de pedidos
                        cout << p.CodProd << "\t\t\t" << "??????????????" << "\t\t" << p.CantidadPed << "\t\t\t\t" << "??\t\t" << p.Nomtienda << endl;
                }

                Pedidos.desencolar();
                Pedidos.encolar(p);
                i++;
            }
            cout << endl << endl;
        }
    }
    else
        cout << "\nLa cola de pedidos esta vacia\n";
}


bool TAlmacen::SalvarColaPedidos(Cadena Nomf)
{
    if(!Pedidos.esvacia())
    {
        ofstream f;
        TPedido p;
        cola aux;

        f.open(Nomf, ios::binary);
        if(f)
        {
            while(!Pedidos.esvacia()) //Mientras la cola de pedidos no esté vacia
            {
                p=Pedidos.primero();
                f.write((char*)&p,sizeof(TPedido));
                Pedidos.desencolar();
                aux.encolar(p); //Almacenamos en aux los pedidos que se van quitando de PEdidos
            }

            while(!aux.esvacia()) //Añadimos los pedidos de aux a Pedidos
            {
                Pedidos.encolar(aux.primero());
                aux.desencolar();
            }
            f.close();
            return true;
        }
        else
            return false;
    }
    else
    {
        cout << "\n\n...La cola de pedidos esta vacia...\n\n";
        return false;
    }
}


void TAlmacen::AniadirPedido(TPedido p) {Pedidos.encolar(p); }


bool TAlmacen::SalvarListaEnvios(Cadena Nomf)
{
    if(!Envios.esvacia())
    {
        ofstream f;
        TPedido p;

        f.open(Nomf, ios::binary);
        if(f)
        {
            for(int i=1; i<=Envios.longitud(); i++)
            {
                p = Envios.observar(i); //Guardamos en p el envio i-esimo
                f.write((char*)&p, sizeof(TPedido)); //Lo guardamos en el fichero
            }
            f.close();
            return true;
        }
        else
            return false;
    }
    else
    {
        cout << "\n...La lista de envios esta vacia...\n";
        return false;
    }
}


bool TAlmacen::InsertarEnvios(TPedido p)
{
    bool insertado = false;
    int i = 1;
    TPedido ped;
    while(!insertado && i<=Envios.longitud())
    {
        ped = Envios.observar(i); //Guardamos en p el envio i-esimo

        //Vemos si son de la misma tienda
        if(strcmp(ped.Nomtienda,p.Nomtienda)==0) //Si es la misma
            insertado=true;
        else
            i++;
    }
    Envios.insertar(i, p);
    return true;
}


bool TAlmacen::SalidaCamionTienda(Cadena NomTienda)
{
    if(Envios.longitud()==0) //Vemos si la lista de envios esta vacia
        return false;
    else
    {
        TPedido p;
        cout << "\n\n*** ENVIOS QUE VAN EN EL CAMION A LA TIENDA \"" << NomTienda << "\" ***\n"
             << "-------------------------------------------------------------------\n"
             << "COD. PRODUCTO\tCANTIDAD PEDIDA\t\tTIENDA\n";
        int i = 1;
        while(i <= Envios.longitud())
        {
            p = Envios.observar(i); //Guardamos en p el envio i-esimo
            if(strcmp(p.Nomtienda,NomTienda)==0) //Si es la tienda que buscamos, lo quitamos
            {
                Envios.eliminar(i); //Eliminamos el envio i-esimo que coincide con la tienda
                cout << p.CodProd << "\t\t\t" << p.CantidadPed << "\t\t" << p.Nomtienda << endl;
            }
            else
                i++;
        }
        return true;
    }
}


bool TAlmacen::AtenderPedidos(Cadena CodProd,int cantidadcomprada)
{
    if(Pedidos.longitud()==0)
        cout << "\nLa cola de pedidos esta vacia\n";
    else
    {
        int i=1;
        TPedido p;
        int n = Pedidos.longitud();
        while(i <= n)
        {
            p = Pedidos.primero();
            if(strcmp(CodProd, p.CodProd)==0) //Si es el mismo
            {
                //Se puede atender completamente
                if(cantidadcomprada >= p.CantidadPed)
                {
                    InsertarEnvios(p); //Llamamos al insertar envios para meter "p" en la lista de envios
                    Pedidos.desencolar();
                    i++;

                    cantidadcomprada -= p.CantidadPed; //Le quitamos la cantidad de "p" a cantidadcomprada
                }

                //Se puede antender parcialmente
                else
                {
                    if((cantidadcomprada < p.CantidadPed) && (cantidadcomprada != 0)) //Si es menor y no es 0 la cantidad comprada
                    {
                        p.CantidadPed -= cantidadcomprada;
                        Pedidos.desencolar();
                        Pedidos.encolar(p); //Metemos al final "p" pero con su cantidad modificada

                        p.CantidadPed = cantidadcomprada; //Insertamos el pedido parcialmente atendido en envios
                        InsertarEnvios(p);
                        i++;

                        cantidadcomprada = 0; //Nos quedamos sin nada.
                    }
                    else //Si cantidadcomprada es 0
                        i++;
                }
                //Sobra cantidad comprada
            }
            else
            {
                i++;
                Pedidos.desencolar();
                Pedidos.encolar(p);
            }
        }
        if(cantidadcomprada != 0) //Si ha sobrado algo de la cantidadcomprada la añadimos al producto del almacen correspondiente
        {
            cout << "\nHan sobrado " << cantidadcomprada << " unidades, las metemos en el almacen\n";
            int pos=0;
            pos=BuscarProducto(CodProd);
            TProducto prod = ObtenerProducto(pos);
            prod.Cantidad += cantidadcomprada; //Añadimos al producto la cantidad que ha sobrado
            ActualizarProducto(pos, prod); //Actualizamos el producto
        }
    }
    return true;
}


void TAlmacen::ListarCantidadesPendientes(Cadena CodProd)
{
    if(Pedidos.esvacia())
        cout << "\nLa cola de pedidos esta vacia\n";
    else
    {

        if(strcmp(CodProd,"")==0) //Mostrar todos los productos
        {
            TPedido tablap[5];
            TPedido p;
            int rellena = 0; //Numero de posiciones rellenas del array local
            for(int i = 1; i<=Pedidos.longitud(); i++)
            {
                bool encontrado = false;
                p = Pedidos.primero(); //Guardamos en p el primer producto
                int j = 0;
                while(j < rellena)
                {
                    if(strcmp(tablap[j].CodProd,p.CodProd)==0) //Si ya existe el prodcuto, le suma la cantidad
                    {
                        encontrado = true;
                        tablap[j].CantidadPed += p.CantidadPed;
                    }
                    j++;
                }

                if(!encontrado)
                {
                    tablap[rellena] = p;
                    rellena++;
                }
                Pedidos.desencolar();
                Pedidos.encolar(p);
            }

            //Mostramos los datos
            cout << "\n*** CANTIDADES PENDIENTES DE TODOS LOS PRODUCTOS ***\n";
            cout << "---------------------------------------------------\n";
            cout << "PRODUCTO\t\tCANTIDAD TOTAL PENDIENTE\n";
            for(int i = 0; i < rellena; i++)
            {
                cout << tablap[i].CodProd << "\t\t\t\t  " << tablap[i].CantidadPed << endl;
            }
            cout << endl << endl;
        }

        //Me pasan un codigo de producto
        else
        {
            //Comprobamos antes si existe el producto en la cola
            int i = 1;
            TPedido p, aux;
            bool encontrado = false;

            //--- Busqueda del producto en la cola ---
            while(i <= Pedidos.longitud())
            {
                p = Pedidos.primero();

                if(strcmp(CodProd, p.CodProd)==0)
                    encontrado = true;

                Pedidos.desencolar();
                Pedidos.encolar(p);
                i++;
            }
            //---------------------------------------

            if(encontrado == true)
            {
                int repetido = 0;
                for(int i=1; i<=Pedidos.longitud(); i++)
                {
                    p = Pedidos.primero();
                    if(strcmp(p.CodProd, CodProd)==0) //Si lo encuentra
                    {
                        if(repetido == 0) //Si es la primera vez que lo ve, copiamos en aux lo que hay en p
                        {
                            repetido = 1;
                            aux = p;
                        }
                        else
                            aux.CantidadPed += p.CantidadPed;
                    }
                    Pedidos.desencolar();
                    Pedidos.encolar(p);
                }

                //Mostramos el producto
                cout << "\n*** CANTIDADES PENDIENTES DEL PRODUCTO \"" << CodProd << "\" ***\n";
                cout << "---------------------------------------------------\n";
                cout << "PRODUCTO\t\tCANTIDAD TOTAL PENDIENTE\n";
                cout << aux.CodProd << "\t\t\t\t  " << aux.CantidadPed << endl << endl;
            }
            else
                cout << "\nEl producto no existe en la cola de pedidos\n";
        }
    }
}



//--------------- MODIFICACION --------------------
void TAlmacen::generarpedidosporstockminimo(int stockminimo)
{
    TPedido p; //Para mandar el pedido
    strcpy(p.Nomtienda,"almacen.dat"); //Dejamos ya puesto el nombre del fichero tienda que va a tener cada pedido

    TProducto prod; //Para recoger el producto i-esimo del almacen

    //Recorremos el almacen entero, comparando la cantidad de cada producto con la de stockminimo, si es menor, añadimos un pedido nuevo, sino NO
    for(int i=0; i<NProduc; i++)
    {
        prod = ObtenerProducto(i); //Obtenemos el producto i-esimo

        //Si la cantidad de ese producto es menor que stockminimo, se genera el pedido, en caso contrario no lo añade y va al siguiente producto
        if(prod.Cantidad < stockminimo)
        {
            //Rellenamos los campos de p con los del producto
            strcpy(p.CodProd, prod.CodProd);
            p.CantidadPed = stockminimo - prod.Cantidad;
            AniadirPedido(p); //Añadimos el pedido a la cola de pedidos
        }
    }
}



