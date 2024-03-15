#include <iostream>
#include <stdlib.h>

#include "TTipos.h"
#include "TAlmacen.h"
#include "TTienda.h"

using namespace std;

int main()
{
    TAlmacen Al;
    TTienda T;
    TProducto prod;
    TEstante estante;
    TFecha fini, ffin;

    int opT, opA, op, pos, codEst;
    bool ok;
    Cadena nomAl, nomTi, dirAl, dirTi, nomFich, codProd;

    do { ///MENU PRINCIPAL
        system("cls");

        if(Al.NProductos() != -1)
            Al.DatosAlmacen(nomAl, dirAl);
        else
        {
            strcpy(nomAl, "");
            strcpy(dirAl, "");
        }
        //-------------
        if(T.NoEstantes() != -1)
            T.DatosTienda(nomTi, dirTi);
        else
        {
            strcpy(nomTi, "");
            strcpy(dirTi, "");
        }

        cout << "\n----- Menu Principal ----- " << nomAl << " " << nomTi << "\n";
        cout << "\n";
        cout << "1.- Gestion del Almacen\n";
        cout << "2.- Gestion de la Tienda\n";
        cout << "3.- Reposicion de Productos en Tienda\n";   //Debe haberse abierto antes un fichero Almacen y una Tienda
        cout << "0.- Salir\n\n";

        cout << "Elige una opcion: ";
        cin >> op;

        switch(op)
          {
            case 1: ///MENU DEL ALMACEN (TRABAJAR CON FICHERO BINARIO)
                do
                  {
                    system("cls");

                    if(Al.NProductos() != -1)
                        Al.DatosAlmacen(nomAl, dirAl);
                    else
                    {
                        strcpy(nomAl, "");
                        strcpy(dirAl, "");
                    }

                    cout << "\n----- Menu Almacenes ----- " << nomAl <<"\n";
                    cout << "\n";
                    cout << "1.- Crear un almacen vacio.\n";                ///X
                    cout << "2.- Abrir un fichero de almacen.\n";           ///X
                    cout << "3.- Cerrar un almacen.\n";                     ///X
                    cout << "4.- Listar productos del almacen.\n";
                    cout << "5.- A" << char(164) << "adir un producto.\n";
                    cout << "6.- Actualizar un producto.\n";
                    cout << "7.- Consultar un producto.\n";
                    cout << "8.- Eliminar un producto.\n";
                    cout << "9.- Listar productos entre dos fechas.\n";
                    cout << "0.- Salir.\n\n";                               ///X

                    cout << "Elige una opcion: ";
                    cin >> opA;

                    switch(opA)
                    {
                    case 1: // Crear un almacen vacio (pasando como parametro nombre, direccion del almacen y nombre del fichero)
                        if(Al.EstaAbierto())
                            cout << "\nYa hay un almacen abierto\n";
                        else
                        {
                            cout << "\nIndica el nombre del almacen: ";
                            cin.ignore();
                            cin.getline(nomAl, 90);
                            cout << "\nIndica la direccion del almacen: ";
                            cin.ignore();
                            cin.getline(dirAl, 90);
                            cout << "\nIndica nombre del fichero en el que guardar estos datos: ";
                            cin >> nomFich;
                            ok=Al.CrearAlmacen(nomAl, dirAl, nomFich);

                            if(ok)
                                cout << "\n\nFichero creado correctamente.\n";
                            else
                                cout << "\n\nError al crear el fichero.\n";
                        }
                        system("pause");
                        break;
                    //---
                    case 2: // Abrir un fichero de almacen
                        if(Al.EstaAbierto()) //COMPROBAMOS QUE NO HAYA UN FICHERO ABIERTO
                            cout << "\nYa hay un almacen abierto.\n";
                        else
                        {
                            cout << "\nIndica el nombre del fichero: ";
                            cin >> nomFich;
                            ok=Al.AbrirAlmacen(nomFich);
                            if(ok)
                                cout << "\nFichero abierto correctamente.\n";
                            else
                                cout << "\nError, fichero no existente\n";
                        }
                        system("pause");
                        break;
                    //---
                    case 3:
                        if(Al.EstaAbierto()==true)
                        {
                            ok=Al.CerrarAlmacen();
                            if(ok)
                                cout << "\nFichero cerrado correctamente.\n\n";
                            else
                                cout << "\nError al cerrar el almacen.\n\n";
                        }
                        else
                            cout << "\nNo hay un almacen abierto.\n\n";
                        system("pause");
                        break;
                    //---
                    case 4: //Listar almacen
                        if(Al.EstaAbierto())
                        {
                            if(Al.NProductos() == 0)
                                cout << "\nEl almacen esta vacio\n";
                            else
                            {
                                system("cls"); //Cabecera del almacen
                                cout << "\nListado del almacen '" << nomAl << "' localizado en " << dirAl << endl;
                                cout << "********************************************************************************************" << endl;
                                cout << "CODIGO\t\tNOMBRE\t\t\tPRECIO\t\tCANTIDAD\tFECHA CADUCIDAD"<<endl;
                                for(int i=0; i < Al.NProductos(); i++)
                                {
                                    prod = Al.ObtenerProducto(i); //prod va a obtener los datos del producto de la posicion i

                                    cout << prod.CodProd << "\t\t" << prod.NombreProd << "\t\t" << prod.Precio;
                                    cout << "\t\t" << prod.Cantidad << "\t\t" << prod.Caducidad.Dia << "/";
                                    cout << prod.Caducidad.Mes << "/" << prod.Caducidad.Anyo << endl;
                                }
                                cout << endl;
                            }
                        }
                        else
                            cout << "\nNo hay ningun almacen abierto\n";
                        system("pause");
                        break;
                    //---
                    case 5:
                        system("cls");
                        if(Al.EstaAbierto())
                        {
                            cout << "\nIntroduce el codigo del producto: ";
                            cin >> codProd;
                            //Primero vemos si el producto ya esta en el almacen
                            pos = Al.BuscarProducto(codProd);
                            if(pos==-1) //No existe
                            {
                                strcpy(prod.CodProd, codProd);
                                Cadena nom;
                                int cantidad, dia, mes, anyo;
                                float precio;
                                cout << "\nIngresa nombre: ";
                                cin.ignore();
                                cin.getline(nom, 90);
                                strcpy(prod.NombreProd, nom);
                                cout << "\nPrecio por unidad: ";
                                cin >> precio;
                                prod.Precio=precio;
                                cout << "\nCantidad: ";
                                cin >> cantidad;
                                prod.Cantidad=cantidad;
                                cout << "\n\n-- Fecha de caducidad --";
                                cout << "\nDia: ";
                                cin >> dia;
                                cout << "Mes: ";
                                cin >> mes;
                                cout << "Anyo: ";
                                cin >> anyo;
                                prod.Caducidad.Dia=dia;
                                prod.Caducidad.Mes=mes;
                                prod.Caducidad.Anyo=anyo;

                                ok=Al.AnadirProducto(prod);
                                if(ok)
                                    cout << "\n\nProducto a" << char(164) << "adido con exito\n";
                                else
                                    cout << "\nError al añadir el producto\n";
                            }
                            else
                                cout << "\nEl producto ya existe en el almacen\n";
                        }
                        else
                            cout << "\nNo hay ningun almacen abierto\n";
                        system("pause");
                        break;
                    //---
                    case 6: //Actualizar un producto
                        if(Al.EstaAbierto())
                        {
                            if(Al.NProductos()==0)
                                cout << "\nEl almacen no tienen ningun producto\n";
                            else
                            {
                                system("cls");
                                cout << "\nIndica codigo del producto a actualizar: ";
                                cin >> codProd;
                                pos=Al.BuscarProducto(codProd);

                                if(pos == -1) //Si no encuentra el producto
                                    cout << "\nNo se ha encontrado el producto\n";
                                else //Si lo encuentra
                                {
                                    prod = Al.ObtenerProducto(pos);
                                    cout << "\nSe ha encontrado el producto:\n";
                                    cout << "Posicion: " << pos << endl;
                                    cout << "---------------------------------------\n";
                                    cout << prod.CodProd << "  " <<prod.NombreProd << "  " <<prod.Precio << "  " << prod.Cantidad;
                                    cout << "  " << prod.Caducidad.Dia << "/" << prod.Caducidad.Mes << "/" << prod.Caducidad.Anyo << endl;

                                    char opcion;
                                    cout << "\nDesea cambiar el codigo? (S o N): ";
                                    cin >> opcion;
                                    opcion = toupper(opcion);
                                    if(opcion=='S')
                                    {
                                        Cadena codigo;
                                        cout << "\nIntroduzca nuevo codigo: ";
                                        cin >> codigo;
                                        strcpy(prod.CodProd, codigo);
                                    }

                                    cout << "\nDesea cambiar el nombre? (S o N): ";
                                    cin >> opcion;
                                    opcion = toupper(opcion);
                                    if(opcion=='S')
                                    {
                                        Cadena nom;
                                        cout << "\nIntroduzca nuevo nombre: ";
                                        cin.ignore();
                                        cin.getline(nom, 90);
                                        strcpy(prod.NombreProd, nom);
                                    }

                                    fflush(stdin); //Limpiar buffer

                                    cout << "\nDesea cambiar el precio? (S o N): ";
                                    cin >> opcion;
                                    opcion = toupper(opcion);
                                    if(opcion=='S')
                                    {
                                        float precio;
                                        cout << "\nIntroduzca nuevo precio: ";
                                        cin >> precio;
                                        prod.Precio=precio;
                                    }

                                    cout << "\nDesea cambiar la cantidad? (S o N): ";
                                    cin >> opcion;
                                    opcion = toupper(opcion);
                                    if(opcion=='S')
                                    {
                                        int cantd;
                                        cout << "\nIntroduzca nueva cantidad: ";
                                        cin >> cantd;
                                        prod.Cantidad=cantd;
                                    }

                                    cout << "\nDesea cambiar la fecha de caducidad? (S o N): ";
                                    cin >> opcion;
                                    opcion = toupper(opcion);
                                    if(opcion=='S')
                                    {
                                        int dia, mes, anyo;
                                        cout << "\n-- Introduzca nueva fecha-- \n";
                                        cout << "Dia: ";
                                        cin >> dia;
                                        cout << "Mes: ";
                                        cin >> mes;
                                        cout << "Anyo: ";
                                        cin >> anyo;
                                        prod.Caducidad.Dia=dia;
                                        prod.Caducidad.Mes=mes;
                                        prod.Caducidad.Anyo=anyo;
                                    }
                                    ok=Al.ActualizarProducto(pos, prod);
                                    if(ok)
                                        cout << "\nProducto actualizado con exito\n";
                                    else
                                        cout << "\nError al actualizar el producto\n";
                                }//Fin del si lo encuentra
                            }
                        }
                        else
                            cout << "\nNo hay ningun almacen abierto\n";
                        system("pause");
                        break;
                    //---
                    case 7:
                        if(Al.EstaAbierto())
                        {
                            if(Al.NProductos()==0)
                                cout << "\nEl almacen no tiene productos\n";
                            else //Si tiene productos
                            {
                                system("cls");
                                cout << "\nIndica el codigo del producto a visualizar: ";
                                cin >> codProd;
                                pos=Al.BuscarProducto(codProd);

                                if(pos == -1)
                                    cout << "\nNo se ha encontrado el producto\n";
                                else //Encuentra el producto
                                {
                                    prod = Al.ObtenerProducto(pos);
                                    cout << "\nSe ha encontrado el producto con codigo " << prod.CodProd << " En la posicion: "<< pos << endl;
                                    cout << "-------------------------------------------------------\n";
                                    cout << "CODIGO    NOMBRE   PRECIO   CANTIDAD    FECHA CADUCIDAD\n";
                                    cout << prod.CodProd << "    " <<prod.NombreProd << "     " <<prod.Precio << "      " << prod.Cantidad;
                                    cout << "     " << prod.Caducidad.Dia << "/" << prod.Caducidad.Mes << "/" << prod.Caducidad.Anyo << endl;
                                }
                            }
                        }
                        else
                            cout << "\nNo hay ningun almacen abierto\n";
                        system("pause");
                        break;
                    //---
                    case 8: ///ELIMINAR UN PRODUCTO
                        if(Al.EstaAbierto())
                        {
                            if(Al.NProductos()==0)
                                cout << "El almacen no tiene productos";
                            else //Si tiene prodcutos
                            {
                                system("cls");
                                cout << "\nIndica el codigo del producto a eliminar: ";
                                cin >> codProd;
                                pos=Al.BuscarProducto(codProd);
                                cout << "\n\nPosicion del producto: " << pos <<endl;

                                if(pos==-1)
                                    cout << "\nNo se ha encontrado el producto\n";
                                else //Ha encontrado el producto
                                {
                                    ok=Al.EliminarProducto(pos);
                                    if(ok)
                                        cout << "\nProducto eliminado exitosamente\n";
                                    else
                                        cout << "\nError al eliminar el producto\n";
                                }
                            }
                        }
                        else
                            cout << "\nNo hay ningun almacen abierto\n";
                        system("pause");
                        break;
                    //---
                    case 9:
                        system("cls");
                        if(Al.EstaAbierto())
                        {
                            cout << "\nIndica las fechas entre las que debe estar el prodcuto: ";
                            cout << "\nAnyo de Fini: ";
                            cin >> fini.Anyo;
                            cout << "\nAnyo de Ffin: ";
                            cin >> ffin.Anyo;

                            Al.Modificacion(fini, ffin);
                        }
                        else
                            cout << "\nDebe de haber un almacen abierto\n";
                        system("pause");
                        break;
                    }
                   }while(opA != 0);
                    break;
      //--------------------------------------------------------
     //--------------------------------------------------------------------
            case 2: ///MENU DE LA TIENDA (TRABAJAR CON TABLAS DINAMICAS)
                do
                {
                    system("cls");

                    if(T.NoEstantes() != -1) //Si hay una tienda abierta
                    {
                        T.DatosTienda(nomTi, dirTi);
                        //estante = new TEstante[T.NoEstantes()];
                    }
                    else
                    {
                        strcpy(nomTi, "");
                        strcpy(dirTi, "");
                    }

                    cout << "\n----- Menu Tienda ----- " << nomTi << "\n";
                    cout << "\n";
                    cout << "1.- Crear una tienda vacia.\n";
                    cout << "2.- Abrir un fichero tienda.\n";
                    cout << "3.- Cerrar la tienda.\n";
                    cout << "4.- Actualizar el fichero tienda.\n";
                    cout << "5.- Listar productos de la tienda.\n";
                    cout << "6.- A" << char(164) << "adir un estante.\n";
                    cout << "7.- Actualizar un estante.\n";
                    cout << "8.- Consultar un estante.\n";
                    cout << "9.- Eliminar un estante.\n";
                    cout << "10.- Refundir la Tienda.\n";
                    cout << "0.- Salir.\n\n";

                    cout << "Elige una opcion: ";
                    cin >> opT;

                    switch(opT)
                    {
                    case 1: //Crear tienda
                        if(T.EstaAbierta())
                            cout << "\nYa hay una Tienda abierta\n";
                        else
                        {
                            cout << "\nIndica el nombre de la tienda: ";
                            cin.ignore();
                            cin.getline(nomTi, 90);
                            cout << "\nIndica la direccion de la tienda: ";
                            cin.ignore();
                            cin.getline(dirTi, 90);
                            cout << "\nIndica nombre del fichero en el que guardar estos datos: ";
                            cin >> nomFich;
                            ok=T.CrearTienda(nomTi, dirTi, nomFich);

                            if(ok)
                                cout << "\n\nTienda creada correctamente.\n";
                            else
                                cout << "\n\nError al crear la Tienda.\n";
                        }
                        system("pause");
                        break;
                    //---
                    case 2: //Abrir uan tienda
                            cout << "\nIndica el nombre del fichero: ";
                            cin >> nomFich;
                            ok=T.AbrirTienda(nomFich);
                            if(ok)
                                cout << "\nFichero abierto correctamente.\n";
                            else
                                cout << "\nError, fichero no existente\n";
                        system("pause");
                        break;
                    //---
                    case 3:
                        if(T.EstaAbierta()) //Si la tienda esta abierta
                        {
                            ok=T.CerrarTienda();
                            if(ok)
                                cout << "\nTienda cerrada con exito\n";
                            else
                                cout << "\nError al cerrar la tienda\n";
                        }
                        else
                            cout << "\nNo hay ninguna tienda abierta\n";
                        system("pause");
                        break;
                    //---
                    case 4:
                        if(T.EstaAbierta())
                        {
                            ok = T.GuardarTienda();
                            if(ok)
                                cout << "\nFichero tienda guardado correctamente\n";
                            else
                                cout << "\nError al guardar el fichero\n";
                        }
                        else
                            cout << "\nNo hay ninguna tiend abierta\n";
                        system("pause");
                        break;
                    //---
                    case 5: //Listar tienda
                        if(T.EstaAbierta())
                        {
                            if(T.NoEstantes() == 0) //No hay estantes
                                cout << "\nNo hay estantes en la tienda\n";
                            else //Hay estantes
                            {
                                if(Al.EstaAbierto() == false) //Vemos si hay un almacen abierto
                                    cout << "\nDebes de tener abierto un almacen si quieres mostrar el nombre de los productos\n";
                                else
                                {
                                    system("cls"); //Cabecera de la tienda
                                    cout << "\nListado de la tienda '" << nomTi << "' localizado en " << dirTi << endl;
                                    cout << "********************************************************************************************" << endl;
                                    cout << "CODIGO\t\tPOSICION\t\tCAPACIDAD\t\tPRODUCTO\t\tNoPRODUCTOS"<<endl;

                                    for(int i=0; i<T.NoEstantes(); i++)
                                    {
                                        estante = T.ObtenerEstante(i);

                                        cout << estante.CodEstante << "\t\t" << estante.Posicion << "\t\t\t" << estante.Capacidad
                                             << "\t\t\t" << estante.CodProd << "\t\t\t" << estante.NoProductos << endl;
                                        //En vez del CodProd debería de verse el nombre que pertenece a ese producto
                                    }
                                    cout << endl;
                                }
                            }
                        }
                        else
                            cout << "\nNo hay ninguna tienda abierta\n";
                        system("pause");
                        break;
                    //---
                    case 6: ///Añadir un estante a la tienda
                        system("cls");
                        if(T.EstaAbierta()) //Esta abierta la tienda
                        {
                            if(Al.EstaAbierto())
                           {
                            cout << "\nIngresa el codigo del estante: ";
                            cin >> estante.CodEstante;

                            pos = T.BuscarEstante(estante.CodEstante); //Vamos a ver si existe el estante

                            if(pos != -1) //SI existe
                                cout << "\nEl estante " << estante.CodEstante << " ya existe\n";
                            else
                            {
                                cout << "\nIngresa posicion del estante(0,1,2): ";
                                cin >> estante.Posicion;

                                cout << "\nIngresa capacidad del estante: ";
                                cin >> estante.Capacidad;

                                cout << "\nIngresa el codigo del producto que va a ir en el estante: ";
                                cin >> estante.CodProd;

                                cout << "\nVamos a ver si ese producto existe en el almacen...\n";
                                int existeProducto = Al.BuscarProducto(estante.CodProd);
                                if(existeProducto == -1) //No estaba el producto
                                    cout << "\nEl producto no existe en el almacen\n";
                                else //El producto está en la pos existeproducto
                                {
                                    prod = Al.ObtenerProducto(existeProducto); //Ahora prod tiene la info de la pos existeProdcuto
                                    cout << "Todo correcto, el producto existe, se trata de \"" << prod.NombreProd << "\" y tiene " << prod.Cantidad << " unidades\n\n";
                                    cout << "\nIngresa que cantidad quieres de ese producto: ";
                                    cin >> estante.NoProductos;

                                    if(estante.NoProductos > estante.Capacidad) //Si supera la capacidad del estante
                                        cout << "\n\nNo puedes poner mas productos que la capacidad del estante...\n";
                                    else //Si no la supera
                                    {
                                        if(estante.NoProductos > prod.Cantidad) //Si supera la cantidad
                                        {
                                            cout << "\nEn el almacen solo hay " << prod.Cantidad << " unidades, se pondra esa cantidad\n";
                                            estante.NoProductos = prod.Cantidad;
                                            prod.Cantidad = 0;
                                            Al.ActualizarProducto(existeProducto, prod); //Actualizamos la cantidad del producto del almacen
                                            ok = T.AnadirEstante(estante); //Añadimos el estante
                                            if(ok)
                                                cout << "\nEstante aniadido con exito\n";
                                            else
                                                cout << "\nError al aniadir el estante\n";
                                        }
                                        else //Si no se supera la cantidad del producto
                                        {
                                            cout << "\nCantidad de productos aniadida sin problemas\n";
                                            prod.Cantidad -= estante.NoProductos;
                                            Al.ActualizarProducto(existeProducto, prod);
                                            ok = T.AnadirEstante(estante); //Añadimos el estante
                                            if(ok)
                                                cout << "\nEstante aniadido con exito\n";
                                            else
                                                cout << "\nError al aniadir el estante\n";
                                        }
                                    }
                                }
                            }
                           }
                           else
                            cout << "\nNo hay un almacen abierto todavia\n";
                        }
                        else
                            cout << "\nNo hay una tienda abierta\n";
                        system("pause");
                        break;
                    //---
                    case 7:
                        if(T.EstaAbierta())
                        {
                            if(T.NoEstantes()==0)
                                cout << "\nNo hay estantes en la tienda\n";
                            else
                            {//Hay estantes...
                                cout << "\nIndica codigo del estante a modificar: ";
                                cin >> codEst;
                                //Vemos si existe el estante
                                pos=T.BuscarEstante(codEst);
                                if(pos==-1)
                                    cout << "\nEl estante no existe en la tienda\n";
                                else
                                {//Encuentra el estante
                                    estante = T.ObtenerEstante(pos);
                                    cout << "\n\nSe ha encontrado el estante " << estante.CodEstante << ". Su num de productos es: "
                                         << estante.NoProductos << endl;
                                    int posProduc = Al.BuscarProducto(estante.CodProd);
                                    prod = Al.ObtenerProducto(posProduc);
                                    char eleccion;
                                    cout << "\nDesea actualizar la cantidad de productos del estante? (s/n): ";
                                    cin >> eleccion;
                                    eleccion = toupper(eleccion);
                                    if(eleccion == 'S')
                                    {//Si se quiere modificar
                                        int nuevaCantidad;
                                        cout << "\nIndica la nueva cantidad de productos: ";
                                        cin >> nuevaCantidad;
                                        if(nuevaCantidad < estante.NoProductos)
                                        {//Si añade menos productos que antes
                                            cout << "\nComo vamos a poner menos productos en el estante, devolvemos los sobrantes\n";
                                            prod.Cantidad += (estante.NoProductos - nuevaCantidad);
                                            Al.ActualizarProducto(posProduc, prod);

                                            estante.NoProductos = nuevaCantidad;
                                            ok=T.ActualizarEstante(pos, estante); //ACTUALIZO
                                            if(ok)
                                                cout << "\nEstante actualizado con exito\n";
                                            else
                                                cout << "\nError al actualizar el estante\n";
                                        }
                                        else
                                        {//Si añade mas que antes
                                            if(nuevaCantidad > prod.Cantidad)
                                            {//Si supera la cantidad del almacen
                                                cout << "\nEn el almacen solo hay " << prod.Cantidad << " unidad/es, se aniadira esa cantidad\n";
                                                estante.NoProductos += prod.Cantidad;
                                                prod.Cantidad = 0;
                                                Al.ActualizarProducto(posProduc, prod);

                                                ok=T.ActualizarEstante(pos, estante); //ACTUALIZO
                                                if(ok)
                                                    cout << "\nEstante actualizado con exito\n";
                                                else
                                                    cout << "\nError al actualizar el estante\n";
                                            }
                                            else
                                            {//Añade mas pero no se pasa de las unidades del producto
                                                cout << "\nSe aniaden " << nuevaCantidad - estante.NoProductos << " unidades mas al estante\n";
                                                prod.Cantidad -= (nuevaCantidad - estante.NoProductos);
                                                Al.ActualizarProducto(posProduc, prod);

                                                estante.NoProductos = nuevaCantidad;
                                                ok=T.ActualizarEstante(pos, estante); //ACTUALIZO
                                                if(ok)
                                                    cout << "\nEstante actualizado con exito\n";
                                                else
                                                    cout << "\nError al actualizar el estante\n";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else
                            cout << "\nNo hay una tienda abierta\n";
                        system("pause");
                        break;
                    //---
                    case 8: //Consultar un estante
                        if(T.EstaAbierta())
                        {
                            if(T.NoEstantes()==0)
                                cout << "\nLa tienda no tiene estantes\n";
                            else
                            {
                                cout << "\nIndica el codigo del estante a consultar: ";
                                cin >> codEst;
                                pos=T.BuscarEstante(codEst);

                                if(pos == -1)
                                    cout << "\nNo se ha encontrado el estante\n";
                                else
                                {
                                    estante = T.ObtenerEstante(pos);
                                    cout << "\n\nSe ha encontrado el estante " << codEst << " en la posicion " << pos << endl;
                                    cout << "--------------------------------------------\n";
                                    cout << "CODIGO\t\tPOSICION\t\tCAPACIDAD\t\tPRODCUTO\t\tNoPRODUCTOS\n";
                                    cout << estante.CodEstante << "\t\t" << estante.Posicion << "\t\t\t" << estante.Capacidad
                                         << "\t\t\t" << estante.CodProd << "\t\t\t" << estante.NoProductos << "\n\n\n";
                                }
                            }
                        }
                        else
                            cout << "\nNo hay una tienda abierta\n";
                        system("pause");
                        break;
                    //---
                    case 9: //Eliminar un estante
                        if(T.EstaAbierta())
                        {
                            if(T.NoEstantes()==0)
                                cout << "\nNo hay estantes en la tienda\n";
                            else //Hay estantes en la tienda
                            {
                                cout << "\nIndica el codigo del estante a eliminar: ";
                                cin >> codEst;

                                //Primero vemos si el estante introducido existe en la tienda
                                pos = T.BuscarEstante(codEst);
                                if(pos == -1)
                                    cout << "\nNo se ha encontrado el estante o este no existe\n";
                                else //Hemos encontrado el estante
                                {
                                    //Antes de eliminarlo hay que devolver el num de productos al almacen
                                    estante = T.ObtenerEstante(pos);
                                    int posProducto = Al.BuscarProducto(estante.CodProd);

                                    if(posProducto == -1)
                                        cout << "\nEl prodcuto del estante no existe en el almacen\n";
                                    else
                                    {
                                        prod=Al.ObtenerProducto(posProducto);
                                        prod.Cantidad += estante.NoProductos;
                                        Al.ActualizarProducto(posProducto, prod);

                                        ok = T.EliminarEstante(pos);
                                        if(ok)
                                            cout << "\nEstante con codigo " << codEst << " eliminado con exito\n";
                                        else
                                            cout << "\nError al eliminar el estante\n";
                                    }
                                }
                            }
                        }
                        else
                            cout << "\nNo hay una tienda abierta\n";
                        system("pause");
                        break;
                    //---
                    case 10:
                        if(T.EstaAbierta())
                        {
                            cout << "\nProcedemos a reducir la tienda\n";
                            T.Refunde();
                        }
                        else
                            cout << "\nNo hay una tienda abierta\n";
                        system("pause");
                        break;
                    }
                }while(opT != 0);
                break;
      //--------------------------------------------
     //------------------------------------------------
            case 3: /// Ejecuta el metodo que repone los productos de la tienda
            system("cls");
            if(T.EstaAbierta())
            {
                if(Al.EstaAbierto())
                {
                    cout << "\nVamos a reponer los estantes de la tienda\n";
                    cout << "-------------------------------------------------------------------------\n";
                    cout << "CODIGO\t\tCAPACIDAD\t\tPRODUCTO\t\tESTADO\t\t\tPORCENTAJE\n";

                    int posProduc, reponer;
                    float porcentaje;

                    for(int i=0; i<T.NoEstantes(); i++) //Hasta que no llegue al final del array Estantes no para
                    {
                        //Primero guardamos en prod el producto a reponer
                        estante = T.ObtenerEstante(i);
                        posProduc = Al.BuscarProducto(estante.CodProd);
                        prod = Al.ObtenerProducto(posProduc);

                        cout << estante.CodEstante << "\t\t" << estante.Capacidad << "\t\t\t" << prod.NombreProd;

                        //ESTADO
                        reponer = T.ReponerEstante(i, prod);

                        switch(reponer)
                        {
                        case 0:
                            cout << "\t\tNo repuesto\t\t";
                            break;

                        case 1:
                            cout << "\t\tRepuesto Completamente\t\t";
                            Al.ActualizarProducto(posProduc, prod); //Actualizamos el producto del almacen
                            break;

                        case 2:
                            cout << "\t\tRepuesto Parcialmente\t\t";
                            Al.ActualizarProducto(posProduc, prod);
                            break;
                        }
                        //PORCENTAJE
                        estante = T.ObtenerEstante(i);
                        porcentaje = (estante.NoProductos/estante.Capacidad)*100.0;

                        cout << porcentaje << "%" << endl;
                    }
                }
                else
                    cout << "\nDebes tener tambien un almacen abierto\n";
            }
            else
                cout << "\nNo hay una tienda abierta\n";
            system("pause");
            break;
          }
        }while(op != 0);

    return 0;
}
