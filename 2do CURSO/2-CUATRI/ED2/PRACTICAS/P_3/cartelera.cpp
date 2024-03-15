#include "cartelera.h"
#include <iostream>

using namespace std;

Cartelera::Cartelera(): espectaculos()
{
}

void Cartelera::insertaEspectaculo(const string& e)
{
    espectaculos.insert(PEspectaculos(e, DSalas()));
}

void Cartelera::insertaSala(const string& e, const string& s, const string& c)
{
    espectaculos[e].insert(PSalas(s,c));
}

void Cartelera::eliminaEspectaculo(const string& e)
{
    espectaculos.erase(e);
}

void Cartelera::eliminaSala(const string& e, const string& s)
{
    DEspectaculos::iterator it = espectaculos.find(e);
    if(it != espectaculos.end())
        it->second.erase(s);
}

void Cartelera::listaEspectaculos()
{
    if(espectaculos.empty())
        cout << "El diccionario esta vacio" << endl;
    else
    {
        cout << "=== Espectaculos ===" << endl;
        for(DEspectaculos::iterator i = espectaculos.begin(); i != espectaculos.end(); i++)
        {
            cout << i->first << endl;
        }
    }
}

void Cartelera::listaSalas(const string& e)
{
    DEspectaculos::iterator it1 = espectaculos.find(e);
    if(it1 == espectaculos.end())
        cout << "El espectaculo " << e << " no existe" << endl;
    else
    {
        if(it1->second.empty())
            cout << "No hay salas en este espectaculo" << endl;
        else
        {
            cout << "--- Salas ---" << endl;
            for(DSalas::iterator it2 = it1->second.begin(); it2 != it1->second.end(); it2++)
                cout << it2->first << " - " << it2->second << endl;
        }
    }
}

