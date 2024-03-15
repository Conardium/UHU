#ifndef CONTRATOTP_H
#define CONTRATOTP_H

#include <iostream>

#include "Contrato.h"
#include "Fecha.h"

using namespace std;

class ContratoTP:public Contrato{
    static int MinutosTP;
    static float PrecioTP;
    int MinutosHablados;
    static const float PrecioExceso;
public:
    ContratoTP(long int dni, Fecha f, int minutosHabla);
    ContratoTP(const ContratoTP& c); //Constructor de  copia
    ~ContratoTP();

    static int getLimiteMinutos(){return ContratoTP::MinutosTP;}
    static float getPrecio(){return PrecioTP;} //es lo mismo que ContratoTP::PrecioTP
    int getMinutosHablados() const{return MinutosHablados;}

    static void setTarifaPlana(int minutos, float precio);
    static void setPrecioTP(float p) {PrecioTP=p;}
    static void setLimiteMinutos(int limite) {MinutosTP=limite;}
    void setMinutosHablados(int minutos) {this->MinutosHablados=minutos;}

    void ver() const;
    float factura() const;
};
ostream& operator<<(ostream& s, const ContratoTP &c);
#endif // CONTRATOTP_H
