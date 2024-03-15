#ifndef EMPRESA_H
#define EMPRESA_H

#include "Fecha.h" //definicion clase Fecha
#include "Cliente.h" // definicion clase Cliente
#include "Contrato.h" // definicion de la clase Contrato
#include "ContratoTP.h" // definicion de la clase ContratoTP
#include "ContratoMovil.h" // definicion de la clase ContratoMovil

using namespace std;

class Empresa
{
    Cliente *clientes[100]; //array estático (tamaño 100) de punteros a Clientes
    int ncli; //para saber cuantos clientes hay en el array (Al principio 0)
    const int nmaxcli; //constante que indica el numero maximo de clientes que caben en el array(100)
    Contrato **contratos; //array dinámico de punteros a Contratos (capacidad ilimitada)
    int ncon; //para saber cuantos contratos hay en el array (al principio 0)
    int nmaxcon; //para saber cuantos caben en el array. No es constante porque
                 //varia conforme la tabla aumenta (10, 20, 40, 80...)

protected: //PARA USARLOS CON LOS METODOS PUBLICOS
     int buscarCliente(long int dni) const;//Busca un cliente y devuelve -1 si no existe
                                           //y si existe devuelve la posicion donde se encuentra

    int altaCliente(Cliente *c); //añade al cliente apuntado por c al array de clientes
                                 //devuelve la posicion donde lo mete (-1 si no cabe)
public:
    Empresa();
    virtual ~Empresa();
    //EL CONTRUCTOR DE COPIA Y EL OPERADOR DE ASIGNACION NO LO IMPLEMENTAMOS
    //PORQUE EXPLICITAMENTE SE INDICA EN LA PRACTICA QUE NO SE HAGA

    void crearContrato();
    bool cancelarContrato(int idContrato); //true si el contrato existe, false si no
    bool bajaCliente(long int dni); //idem
    int descuento(float porcentaje) const; //devuelve a cuantos les aplica el descuento
    int nContratosTP() const;
    void cargarDatos();
    void ver() const;
};

#endif // EMPRESA_H
