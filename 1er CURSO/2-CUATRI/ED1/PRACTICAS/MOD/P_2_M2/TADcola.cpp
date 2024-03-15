
//FICHERO TADCola.cpp
#include "TADcola.h"


cola::cola() //Constructor
{
    elementos=new TPedido[Incremento];

    if (elementos!=NULL)
    {
        ne=fin=inicio=0;
        Tama=Incremento;
    }
    else
    {
        ne=fin=inicio=-1;
        Tama=-1;
    }
}


cola::~cola() //Destructor
{
    if (elementos!=NULL)
        delete [] elementos;

    elementos=NULL;
    ne=fin=inicio=-1;
    Tama=0;
}


TPedido cola::primero() { return elementos[inicio]; }


bool cola::esvacia() { return (ne==0); }


int cola::longitud() { return ne; }


void cola::encolar(TPedido e)
{
    if (ne==Tama)
        {
            TPedido *NuevaZona=new TPedido[Tama+Incremento];
            if (NuevaZona!=NULL)
            {
                for (int i=0;i<ne; i++)
                {
                    NuevaZona[i]=elementos[inicio];
                    inicio++;

                    if (inicio==Tama) // inicio=(inicio+1)%Tama
                        inicio=0;
                }
        inicio=0;
        fin=ne;
        Tama+=Incremento;
        delete elementos;
        elementos=NuevaZona;
            }
        }

    if (ne<Tama)
        {
            elementos[fin]=e;
            fin=(fin+1)%Tama;
            ne++;
        }
}


void cola::desencolar()
{
    inicio++; // inicio=(inicio+1)%Tama;
    if (inicio==Tama)
        inicio=0;
    ne--;

    if (Tama-ne>=Incremento && Tama>Incremento)
        {
            TPedido *NuevaZona=new TPedido[Tama-Incremento];

            if (NuevaZona!=NULL)
            {
                for (int i=0;i<ne; i++)
                {
                    NuevaZona[i]=elementos[inicio++];
                    if (inicio==Tama)
                        inicio=0;
                }

                Tama-=Incremento;
                inicio=0;
                fin=0;
                delete [] elementos;
                elementos=NuevaZona;
            }
        }
}


