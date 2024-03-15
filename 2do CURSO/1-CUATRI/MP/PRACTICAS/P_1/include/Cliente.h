#ifndef CLIENTE_H
#define CLIENTE_H

#include <iostream>
#include "Fecha.h"

using namespace std;

class Cliente{
    long int dni; //Sin letra
    char* nombre; //nombre y apellidos del cliente
    Fecha fechaAlta;
public:
    Cliente(long int d, char* nom, Fecha f); //Constructor
    ~Cliente(); //Destructor

    //GET
    long int getDni() const {return dni;} //this->dni
    const char* getNombre() const {return nombre;} //Se pone const delante para evitar que se modifique el nombre desde fuera de la clase (SEGURIDAD)
    Fecha getFecha() const {return fechaAlta;}

    //SET
    //void setDni(long int d) {this->dni=d;}; No aparece en el MAIN
    void setNombre(char* nom);
    void setFecha(Fecha f);

    Cliente &operator=(const Cliente& c); //Sobrecarga de =
    bool operator==(Cliente c) const; //Para comprobar si C1==C2
};

ostream& operator<<(ostream& s, const Cliente &c);

#endif // CLIENTE_H
