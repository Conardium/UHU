
#include "TADlista.h"


lista::lista()
{
    elementos=new TPedido[Incremento];
    if (elementos!=NULL)
    {
        Tama=Incremento;
        n=0;
    }
    else
    {
        Tama=n=-1;
    }
}

lista::~lista()
{
 if (elementos!=NULL)
 delete [] elementos;
 elementos=NULL;
 Tama=n=0;
}

void lista::insertar(int i, TPedido e)
{
    int pos;
    if (n==Tama)
    {
        TPedido *NuevaZona=new TPedido[Tama+Incremento];
        if (NuevaZona!=NULL)
        {
            for (int i=0;i<n; i++)
                NuevaZona[i]=elementos[i];
            Tama+=Incremento;
            delete [] elementos;
            elementos=NuevaZona;
        }
    };

    if (n<Tama)
    {
        for (pos=n-1; pos>=i-1; pos--)
            elementos[pos+1]=elementos[pos]; // Desplazamiento
        elementos[i-1]=e;
        n++;
    }
}

void lista::modificar(int i, TPedido e)
{
    elementos[i-1]=e;
}

TPedido lista::observar(int i)
{
    return(elementos[i-1]);
}

bool lista::esvacia()
{
    return (n == 0);
}

void lista::eliminar(int i)
{
    while (i<n)
    {
        elementos[i-1]=elementos[i]; // Desplazamiento
        i++;
    }
    n--;
    if (Tama-n>=Incremento && Tama>Incremento)
    {
        TPedido *NuevaZona=new TPedido[Tama-Incremento];
        if (NuevaZona!=NULL)
        {
            Tama-=Incremento;
            for (int i=0;i<Tama; i++)
                NuevaZona[i]=elementos[i];
            delete [] elementos;
            elementos=NuevaZona;
        };
    };
}

int lista::posicion(TPedido e) ///CAMBIADO
{
    int i=0;
    while (elementos[i].CodProd != e.CodProd && i < n)
        i++;
    return (i == n ? -1 : i+1);
}

int lista::longitud()
{
    return n;
}

lista::lista(TPedido e)
{
    elementos=new TPedido[Incremento];
    if (elementos!=NULL)
    {
        Tama=Incremento;
        n=1;
        elementos[0]=e;
    }
    else
        Tama=n=-1;
}

void lista::anadirIzq(TPedido e)
{
    insertar(1, e);
}

void lista::anadirDch(TPedido e)
{
    insertar(n+1, e);
}

void lista::eliminarIzq()
{
    eliminar(1);
}

void lista::eliminarDch()
{
    eliminar(n);
}

TPedido lista::observarIzq()
{
    return(observar(1));
}

TPedido lista::observarDch()
{
    return(observar(n));
}

void lista::concatenar(lista l)
{
    int lon = l.longitud();
    for (int i=1; i<=lon; i++)
        insertar(n+1, l.observar(i));
}

bool lista::pertenece(TPedido e)
{
 return (posicion(e) != -1);
}





