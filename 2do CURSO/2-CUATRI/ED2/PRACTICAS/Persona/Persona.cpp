#include "Persona.h"
// ------------------------------- METODOS ----------------------------

Persona::Persona(const string& n, int e) //Constructor parametrizado para inicializar un objeto de tipo Persona
{
    nombre = n;
    edad = e;
}

const string& Persona::getNombre() const //Devuelve el nombre de la Persona que llame a este método
{
    return nombre;
}

int Persona::getEdad() const //Devuelve la edad de la Persona que llame a este método
{
    return edad;
}

void Persona::setNombre(const string& n) //Establece el nombre de la Persona que llama al método con el nombre pasado por parametro
{
    nombre = n;
}

void Persona::setEdad(int e) //Establece la edad de la Persona que llama al método con la edad pasada por parametro
{
    edad = e;
}

bool Persona::operator==(const Persona& p) const //Comprueba si la persona que llama al metodo y otra pasada por parametro son iguales (comparando la edad y el nombre)
{
    if(p.nombre == nombre && p.edad == edad)
        return true;
    else
        return false;
}
