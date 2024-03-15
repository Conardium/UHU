#include "ContratoMovil.h"
#include "Contrato.h"
#include <iomanip>
#include <cstring>

ContratoMovil::ContratoMovil(long int dni, Fecha f, float pXm, int minutos, char* Nac):Contrato(dni, f){ //CONSTRUCTOR
    this->precioXminuto=pXm;
    this->MinutosHabla=minutos;

    this->Nacionalidad= new char[strlen(Nac)+1];
    strcpy(this->Nacionalidad, Nac);
}

ContratoMovil::~ContratoMovil(){ //DESTRUCTOR
    delete [] this->Nacionalidad;
}

ContratoMovil::ContratoMovil(const ContratoMovil& c):Contrato(c.getDniContrato(), c.getFechaContrato()){ //CONSTRUCTOR DE COPIA
    this->precioXminuto=c.precioXminuto;
    this->MinutosHabla=c.MinutosHabla;

    this->Nacionalidad= new char[strlen(c.Nacionalidad)+1];
    strcpy(this->Nacionalidad, c.Nacionalidad);
}

void ContratoMovil::ver() const{
    Contrato::ver(); // MUESTRA EL METODO VER DEL PADRE Y SOLO ME PREOCUPO DE IMPLEMENTEAR LA PARTE DEL HIJO
    cout << " " << this->MinutosHabla << "m, " << this->Nacionalidad << " " << this->precioXminuto;
}

float ContratoMovil::factura() const{
    return precioXminuto*MinutosHabla;
}

void ContratoMovil::setNacionalidad(const char* nac){
    delete [] this->Nacionalidad;
    Nacionalidad= new char[strlen(nac)+1];
    strcpy(Nacionalidad, nac);
}

ostream& operator<<(ostream& s, const ContratoMovil &c){
    s << (Contrato &)c; //CONVERTIMOS EL OBJETO C (CONTRATOMOVIL) EN OBJETO CONTRATO, PARA QUE MUESTRE LA PARTE DEL PADRE Y SOLO ME PREOCUPO DEL RESTO
    s << " " << c.getMinutosHablados() << "m, " << c.getNacionalidad() << " " << c.getPrecioMinuto()
    << " - "<< c.factura() <<char(36);
}






