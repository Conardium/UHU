#include "ContratoTP.h"

float ContratoTP::PrecioTP=10;
const float ContratoTP::PrecioExceso=0.15;
int ContratoTP::MinutosTP=300;

ContratoTP::ContratoTP(long int dni, Fecha f, int minutosHabla):Contrato(dni, f){ //LLAMAMOS AL CONSTRUCTOR DE LA CLASE BASE
    this->MinutosHablados=minutosHabla;
}

ContratoTP::ContratoTP(const ContratoTP& c): Contrato(c.getDniContrato(), c.getFechaContrato()){
    this->MinutosHablados=c.MinutosHablados;
}

ContratoTP::~ContratoTP(){
}

void ContratoTP::setTarifaPlana(int minutos, float precio){
    ContratoTP::MinutosTP=minutos;
    ContratoTP::PrecioTP=precio;
}

void ContratoTP::ver() const{
    Contrato::ver();
    cout<<" "<<this->MinutosHablados<<"m, "<< this->MinutosTP
    << "(" << this->PrecioTP << ")";
}

float ContratoTP::factura() const {
    return PrecioTP + (MinutosHablados>MinutosTP?(MinutosHablados-MinutosTP)*PrecioExceso:0);
}

ostream& operator<<(ostream& s, const ContratoTP &c){
    s <<(Contrato &)c; //CONVERTIMOS EL OBJETO C(CONTRATOTP) EN UN OBJETO DE TIPO CONTRATO
    s << " " << c.getMinutosHablados() << "m, " << c.getLimiteMinutos() << "(" << c.getPrecio()
    << ") - " << c.factura() << char(36); //Como € no funciona pongo $
}
