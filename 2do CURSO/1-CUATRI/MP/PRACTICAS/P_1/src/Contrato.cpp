#include "Contrato.h"

int Contrato::contador=1;

Contrato::Contrato(long int dni, Fecha f):idContrato(contador), fechaContrato(f){
    Contrato::contador++;
    this->dniContrato=dni;
}

Contrato::~Contrato(){
}

void Contrato::ver() const{
    cout << this->dniContrato << " (" << this->idContrato << " - ";
    this->fechaContrato.ver(); //LLAMAMOS AL METODO VER DE FECHA (dd/mm/aaaa)
    cout << ")";
}

ostream& operator<<(ostream& s, const Contrato &c){
    s << c.getDniContrato() << " (" << c.getIdContrato() << " - "
    << c.getFechaContrato() << ")";
    return s;
}
