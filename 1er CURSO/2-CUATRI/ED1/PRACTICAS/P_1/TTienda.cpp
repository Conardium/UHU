#include "TTienda.h"


TTienda::TTienda()
{
    strcpy(Nombre, "");
    strcpy(Direccion, "");
    strcpy(NomFiche, "");
    NEstan = -1;
    Tamano = Incremento;
    Estantes = new TEstante[Tamano];
}

TTienda::~TTienda()
{
    fstream f;
    if(EstaAbierta())
    {
        f.close();
        delete [] Estantes;
    }
}

void TTienda::OrdenarProductos()
{
    for(int i=0; i<NEstan-1; i++)
    {
        for(int j=i+1; j<=NEstan-1; j++)
        {
            if(Estantes[i].CodEstante > Estantes[j].CodEstante)
            {
                TEstante aux;
                aux = Estantes[i];
                Estantes[i] = Estantes[j];
                Estantes[j] = aux;
            }
        }
    }
}

void TTienda::DatosTienda(Cadena pNombTienda, Cadena pDirTienda)
{
    strcpy(pNombTienda, Nombre);
    strcpy(pDirTienda, Direccion);
}

bool TTienda::EstaAbierta()
{
    if(NEstan != -1) //Si tiene distinto de -1 significa que esta abierta
        return true;
    else
        return false;
}

int TTienda::NoEstantes() { return NEstan; }

bool TTienda::CrearTienda(Cadena pNombTienda, Cadena pDirTienda, Cadena pNomFiche)
{
    fstream f;
    f.open(pNomFiche, ios::out|ios::binary);

    if(f.fail())
        return false;
    else //Se ha podido crear
    {
        NEstan=0;
        Tamano=Incremento;
        strcpy(Nombre, pNombTienda);
        f.write((char*)Nombre, sizeof(Cadena));
        strcpy(Direccion, pDirTienda);
        f.write((char*)Direccion, sizeof(Cadena));
        f.close();
        strcpy(NomFiche, pNomFiche); //Guardamos en la variable el nombre del fichero
        return true;
    }
}

bool TTienda::AbrirTienda(Cadena pNomFiche)
{
    fstream f;

    if(EstaAbierta()) //Si había un fichero abierto
    {
        cout << "\nYa habia un fichero abierto, lo cerramos\n";
        f.close();
    }

    f.open(pNomFiche, ios::in|ios::out|ios::binary); //Abrimos el fichero pNomFiche

    if(f.fail()) //Si no se consigue abrir
        return false;
    else //Se ha podido abrir el fichero
    {
        //Se carga la info: NOMBRE | DIRECCION | TABLA DINAMICA DE ESTANTES

        strcpy(NomFiche, pNomFiche);

        f.read((char*)Nombre, sizeof(Nombre)); //Actualizamos nom y dir de Tienda
        f.read((char*)Direccion, sizeof(Direccion));

        //Ahora calculamos cuantos Estantes hay:
        f.seekg(0,ios::end); //Nos situamos al final
        int total = f.tellg(); //Guardamos el tamaño del fichero
        f.seekg(sizeof(Cadena)*2,ios::beg); //Nos situamos en el primer Estante
        int estante1 = f.tellg(); //Guardamos el tamaño hasta llegar al primer estante

        NEstan = (total-estante1)/sizeof(TEstante); //Nestan tiene ahora el num de estantes del fichero

        Estantes = new TEstante[NEstan]; //Pedimos memoria para la tabla dinamica de estantes

        if(Estantes != NULL) //Vemos si me ha dado memoria para Estantes
        {
            f.read((char*)Estantes,sizeof(TEstante)*NEstan);
            OrdenarProductos();
            return true;
        }
        else
            return false;
    }
}

TEstante TTienda::ObtenerEstante(int pPos)
{
    return Estantes[pPos];
}

int TTienda::BuscarEstante(int pCodEstante)
{
    int pos = -1;
    int i=0;

    while(pos == -1 && i < NEstan)
    {
        if(Estantes[i].CodEstante == pCodEstante)
            pos = i;
        else
            i++;
    }
    return pos;
}

bool TTienda::AnadirEstante(TEstante pEstante) //Falta ordenar
{
    if(NEstan == Tamano) //Vemos si la tabla esta llena antes de añadir algo
    {
        TEstante *aux = new TEstante[Tamano+Incremento];
        if(aux != NULL) //Vemos si me ha dado memoria o no
        {
            for(int i=0; i<NEstan; i++)
                aux[i]=Estantes[i];

            delete [] Estantes; //Borramos la antigua tabla
            Estantes = aux; //Ahora Estantes apunta al mismo sitio que aux
            Tamano += Incremento; //Ahora tamaño tiene el que tenia antes + el incremento
        }
        else
            return false;
    }
    //Si no está llena pues añadimos el nuevo elemento
    Estantes[NEstan] = pEstante; //Ahora añadimos el estante que queremos
    NEstan++; //Aumentamos Nestan

    //Ordenar el vector
    OrdenarProductos();
    return true;
}

bool TTienda::CerrarTienda()
{
    //Abrimos el fichero
    ofstream f;
    f.open(NomFiche, ios::binary);
    if(f)
    {
        f.write((char*)Nombre, sizeof(Nombre)); //Escribimos nombre en el fichero
        strcpy(Nombre,""); //Vaciamos nombre
        f.write((char*)Direccion, sizeof(Direccion));
        strcpy(Direccion,"");
        f.write((char*)Estantes, sizeof(TEstante)*NEstan); //Escribimos todos los estantes en el fichero
        strcpy(NomFiche,"");
        delete [] Estantes; //Borramos la tabla estantes
        NEstan = -1; //Para indicar que no hay ninguna tienda abierta
        return true;
    }
    else
        return false;
}

bool TTienda::GuardarTienda()
{
    ofstream f;
    f.open(NomFiche, ios::binary);
    if(f)
    {
        //Hace lo mismo que cerrar pero solo guardando los datos en el fichero
        f.write((char*)Nombre, sizeof(Nombre));
        f.write((char*)Direccion, sizeof(Direccion));
        f.write((char*)Estantes, sizeof(TEstante)*NEstan);
        return true;
    }
    else
        return false;
}

bool TTienda::EliminarEstante(int pPos)
{
    for(int i=pPos; i<NEstan-1; i++) //Desde la posicion del elemento a borrar hasta el penultimo...
    {
        Estantes[i]=Estantes[i+1]; //Al Estante i-esimo le asignamos el siguiente
    }
    NEstan--;
    return true;
}

 bool TTienda::ActualizarEstante(int pPos, TEstante pEstante)
 {
     Estantes[pPos] = pEstante;
     return true;
 }

 int TTienda::ReponerEstante(int pPos, TProducto &pProduc)
 {
     if(pProduc.Cantidad != 0) //Si el producto tiene unidades
     {
         //Repuesto(llena el estante)
        if(Estantes[pPos].Capacidad - Estantes[pPos].NoProductos <= pProduc.Cantidad)
        {
            //Vemos que Capacidad y Noprodcutos no sean el mismo
            if(Estantes[pPos].Capacidad == Estantes[pPos].NoProductos) //Si ya está lleno, sin tener que meter productos
            {
                //No tocamos los productos
                return 1;
            }
            else
            {
                pProduc.Cantidad -= (Estantes[pPos].Capacidad - Estantes[pPos].NoProductos);
                //Se quitan unidades a producto, pero no lo vacia del todo
                Estantes[pPos].NoProductos = Estantes[pPos].Capacidad;
                return 1;
            }
        }

        //Repuesto parcialmente(no lleno del todo)
        else
        {
            Estantes[pPos].NoProductos += pProduc.Cantidad;
            pProduc.Cantidad = 0;
            return 2;
        }
     }
     else //El producto no tiene unidades
        return 0;
 }


 ///MODIFICACION
 void TTienda::Refunde()
 {
     //Vamos a buscar por todos los estantes
     for(int i=0; i<NEstan; i++)
     {
         //Ahora cogemos el siguiente estante del i-esimo
         int j = i+1;
         while(j < NEstan)
         {
             if(strcmp(Estantes[i].CodProd, Estantes[j].CodProd)==0) //Si coinciden los codigos del producto
             {
                //vemos si el NoProductos caben en uno de los dos estantes
                if((Estantes[i].NoProductos + Estantes[j].NoProductos) <= Estantes[i].Capacidad) //En el i caben los de j
                {
                    Estantes[i].NoProductos += Estantes[j].NoProductos; //Añadimos las unidades de j a i
                    EliminarEstante(j); //Borramos el estante j-esimo
                }
                else if ((Estantes[i].NoProductos + Estantes[j].NoProductos) <= Estantes[j].Capacidad) //En el j caben los de i
                {
                    Estantes[j].NoProductos += Estantes[i].NoProductos; //Añadidmos las unidades de i a j
                    EliminarEstante(i); //Borramos el estante i-esimo
                }
                //En caso de que no quepa, no se hace nada
                else
                    j++;
             }
             else //En caso de que no coincidan continuamos con el siguiente
                j++;
         }
     }
 }




