#include "Multiconjunto.h"

//---------------------- METODOS -------------------------------------


template <typename T> Multiconjunto<T>::Multiconjunto() // Constructor
{
    //Inicializamos el numero de elementos del array a 0
    num = 0;
}

template <typename T> bool Multiconjunto<T>::esVacio() const // Comprueba si el multiconjunto es o no vac�o
{
    return num==0;
}

template <typename T> int Multiconjunto<T>::cardinalidad() const // Devuelve el n�mero de elementos
{
    return num;
}

template <typename T> void Multiconjunto<T>::anade(const T& objeto)// A�ade un objeto de tipo T al multiconjunto se permiten elementos repetidos
{
    if(num < 100)
    {
        c[num] = objeto;
        num++;
    }
}

template <typename T> void Multiconjunto<T>::elimina(const T& objeto)// Elimina todas las ocurrencias del objeto pasado como par�metro
{
    int i = 0;
    while(i < num)
    {
        if(c[i] == objeto)
        {
            c[i] = c[num - 1];
            num--;
        }
        else
            i++;
    }
}

template <typename T> bool Multiconjunto<T>::pertenece(const T& objeto) const // Comprueba si el objeto pasado como par�metro existe en el multiconjunto
{
    bool encontrado = false;
    int i = 0;

    while(!encontrado && i < num)
    {
        if(c[i] == objeto)
            encontrado = true;
        else
            i++;
    }
    return encontrado;
}

/// ---- Instancias ----
template class Multiconjunto<int>;
template class Multiconjunto<char>;
template class Multiconjunto<Persona>;
