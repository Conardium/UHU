#include <typeinfo>

#include "Empresa.h"
#include "Fecha.h"

Empresa::Empresa(): nmaxcli(100) {
    this->ncli=0;
    this->ncon=0;
    this->contratos= new Contrato* [10]; //Inicializamos el array dinamico a 10
    this->nmaxcon=10;
}

Empresa::~Empresa(){
//El destructor debe de eliminar además del array dinamico creado por el
//constructor, eliminar los objetos clientes y contratos apuntados por ambos arrays

    for(int i=0; i<this->ncon; i++){ //primero borramos los objetos contratos
        delete this->contratos[i];
    }
    delete [] this->contratos; //y luego borramos el array de punteros

    for(int i=0; i<this->ncli; i++){ //Al no ser una tabla dinamica solo borramos
                                    //los punteros a cliente
       delete this->clientes[i];
    }
}

int Empresa::altaCliente(Cliente* c) { //Añade el cliente apuntado por c al array de clientes
    int pos = -1;                      //devuelve -1 si no cabe. Y si cabe, muestra la posicion
    if(this->ncli<nmaxcli){            //donde se ha colocado

        this->clientes[this->ncli]=c; //Hace que el array de la posicion ncli apunte hacia donde apunta c
        pos=this->ncli;
        this->ncli++; //Al poder añadirlo, incrementamos el ncli que hay en el array
    }

    else{
      cout << "Lo sentimos, el array de clientes esta lleno" ;
      pos=-1;
    }

    return pos;
}

int Empresa::buscarCliente(long int dni) const { //Si no existe devuelve -1 y si existe
    int pos=-1;                                  //devuelve la posicion donde se encuetra
    bool encontrado=false;                       //el cliente con ese dni
    if(ncli!=0)
    {
        int i=0;
        while(!encontrado && i < this->ncli)
        {
            if(clientes[i]->getDni()==dni)
            {
                pos=i;
                encontrado=true;
            }
            else
                i++;
        }
        if(encontrado)
            cout << "Se ha encontrado al cliente en la posicion " << pos+1 <<endl;
        else
            cout << "No se ha encontrado al cliente"<<endl;
    }
    return pos;
}

void Empresa::crearContrato(){
    long int dni;
    int pos;
    cout << "Introduce el DNI: ";
    cin >> dni;
    ///AHORA LLAMAMOS A buscarCliente(dni) para saber si el cliente existe o no
    pos=this->buscarCliente(dni); //Nos devuelve la pos donde esta el cliente

    if(pos==-1) //EL cliente no existe, hay que pedirle todos los datos(darlo de alta)
    {
        int dia, mes, anio;
        char nombre[100];
        Cliente *c; //Creamos un puntero a cliente
        cout << "Introduce el nombre: ";
        cin.ignore(); //limpia el buffer, necesario para que le programa se detenga para poder escribir el nomrbe
        cin.getline(nombre, 100); //lectura incluyendo espacios, el (cin >>) no lee los espacion en blanco
        cout << "Introduce la Fecha de Alta(dd mm aaaa): ";
        cin>> dia >> mes >> anio;
        c = new Cliente(dni, nombre, Fecha(dia, mes, anio)); //Creamos un nuevo espacio de memoria para c
        pos=this->altaCliente(c);                            //donde metemos todos los datos anteriores en el constructor
    }
    //Con el ultimo pos colocamos al nuevo cliente en el array de clientes en pos, al haberse dado de alta
    //ahora puede entrar al siguiente if, si el dni que introducimos al inicio ya esta de alta, el metodo se iría
    //al segundo if sin pasar antes por el primero.
    if(pos!=-1)
    {
        int opc;
        int dia, mes, anio;
        int minutos;
        float precioXminuto;
        char nacionalidad[100];
        cout << "Tipo de Contrato a abrir (1-Tarifa Plana, 2-Movil): ";
        cin >> opc;
        cout << "Introduce Fecha de Contrato (dd mm aaaa): ";
        cin >> dia >> mes >> anio;
        switch(opc)
        {
        case 1: //Tarifa Plana
            cout << "Minutos hablados: ";
            cin >> minutos;
            contratos[ncon++] = new ContratoTP(dni, Fecha(dia, mes, anio), minutos);
            break;

        case 2: //Movil
            cout << "Minutos hablados: ";
            cin >> minutos;
            cout << "Precio por Minuto: ";
            cin >> precioXminuto;
            cout << "Nacionalidad: ";
            cin >> nacionalidad; //Aqui no pongo (cin.getline) ni (cin.ignore) porque supongo que no van a ser necesarios los espacios en blanco
            contratos[ncon++] = new ContratoMovil(dni, Fecha(dia, mes, anio), precioXminuto, minutos, nacionalidad);
            break;

        default: cout << "\n\nTipo de Contrato invalido, Operacion cancelada";
        }

        ///AHORA COMPROBAMOS SI LA TABLA ESTA LLENA, SI ES EL CASO LA AMPLIO EL DOBLE
        if(ncon==nmaxcon)
        {
            nmaxcon*=2;
            Contrato **aux; //Creamos un puntero aux de tipo Contrato
            aux=contratos; //Hacemos que aux apunte hacia donde apunta contratos
            contratos = new Contrato*[nmaxcon]; //Le damos un nuevo espacio de memoria a contratos

            for(int i=0; i<ncon; i++) //Copiamos del array antiguo al nuevo
                contratos[i]=aux[i];
            delete [] aux; //Liberamos la memoria del rray antiguo
        }
    }
    else
        cout << "\nNo se pudo crear el Contrato\n";

    cout << endl;
}

bool Empresa::cancelarContrato(int idContrato){
    bool borrado = false;
    int i=0;

    while(!borrado && i < ncon)
    {
        if(contratos[i]->getIdContrato()==idContrato)
        {
            delete contratos[i];
            while(i < ncon-1) //Movemos los contratos que precedian al borrado. Ponemos ncon-1 para evitar que el ultimo elemento del array copie un valor fuera de ella.
                contratos[i]=contratos[++i]; //Primero i++ y luego lo asigna al puntero contratos
            ncon--;
            borrado=true;
        }
        else
            i++;
    }
    return borrado;
}

bool Empresa::bajaCliente(long int dni){
    bool baja = false;

    int i=0;
    while(i < ncon)
    {
        if(contratos[i]->getDniContrato()==dni)
        {
            cancelarContrato(contratos[i]->getIdContrato());
        }
        else
            i++;
    }

    i=0;
    while(i<ncli && !baja)
    {
        if(clientes[i]->getDni()==dni)
        {
            delete clientes[i];
            while(i<ncli-1)
            {
                clientes[i]=clientes[i+1];
                i++;
            }
            ncli--;
            baja=true;
        }
        else
            i++;
    }
    return baja;
}

int Empresa::descuento(float porcentaje) const{
    int NcontrMovil;
    porcentaje = 1-porcentaje/100;

    for(int i=0; i<ncon; i++)
    {
        if(ContratoMovil *c = dynamic_cast<ContratoMovil*>(contratos[i])) //Vemos si contratos[i] es de tipo ContratoMovil y si es asi se lo asignamos a c
        {
            c->setPrecioMinuto(c->getPrecioMinuto()*porcentaje);
            NcontrMovil++;
        }
    }
    return NcontrMovil;
}

int Empresa::nContratosTP() const{
    int nTP=0;

    for(int i=0; i<ncon; i++)
    {
        if(typeid(*contratos[i]) == typeid(ContratoTP))
            nTP++;
    }
    return nTP;
}

void Empresa::cargarDatos(){
    Fecha f1(29,2,2001), f2(31,1,2002), f3(1,2,2002);
    this->clientes[0] = new Cliente(75547001, "Peter Lee", f1);
    this->clientes[1] = new Cliente(45999000, "Juan Perez", Fecha(29,2,2000));
    this->clientes[2] = new Cliente(37000017, "Luis Bono", f2);
    this->ncli=3;

    this->contratos[0] = new ContratoMovil(75547001, f1, 0.12, 110, "DANES"); //habla 110m a 0.12€/m
    this->contratos[1] = new ContratoMovil(75547001, f2, 0.09, 170, "DANES"); //habla 170m a 0.09€/m
    this->contratos[2] = new ContratoTP(37000017, f3, 250); //habla 250m (300m a 10€, exceso 0.15€/m)
    this->contratos[3] = new ContratoTP(75547001, f1, 312); //habla 312m (300m a 10€, exceso 0.15€/m)
    this->contratos[4] = new ContratoMovil(45999000, f2, 0.10, 202, "ESPANOL"); //habla 202m a 0.10/m
    this->contratos[5] = new ContratoMovil(75547001, f2, 0.15, 80, "DANES"); //habla 80m a 0.15€/m
    this->contratos[6] = new ContratoTP(45999000, f3, 400); //habla 400m (300m a 10€, exceso 0.15€/m)
    this->ncon=7;
}

void Empresa::ver() const{
    cout << "La Empresa tiene " << ncli << " clientes y " << ncon << " contratos"<<endl;

    cout << "Clientes: "<<endl;
    for(int i=0; i<this->ncli; i++)
        cout << *clientes[i] << endl; //Sobrecarga del operador<< de cliente

    cout << "\nContratos: "<<endl;
    for(int i=0; i<ncon; i++)
    {
        if(ContratoMovil *c=dynamic_cast<ContratoMovil*>(contratos[i]))
            cout << *c <<endl;
        else if(ContratoTP *tp=dynamic_cast<ContratoTP*>(contratos[i]))
            cout << *tp<<endl;
    }
}


