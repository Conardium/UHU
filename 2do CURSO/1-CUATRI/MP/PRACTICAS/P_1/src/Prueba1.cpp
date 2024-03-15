//1ra PARTE DE LA PRACTICA
#include <cstdlib>
#include <iostream>
#include "Fecha.h"
#include "Cliente.h"

using namespace std;

/*
int main()
{
    Fecha f1(29,2,2001), f3(29,2,2004), f4(29,2,1900);
    const Fecha f2=f1; //Se ejecuta el CONSTURCTOR DE COPIA
    f1.setFecha(f3.getDia()-3, f3.getMes()-2, 2007);

    cout << "Fechas: "; f1.ver(); cout << ", ";
    f2.ver(); cout << ", ";f3.ver();cout << ", ";f4.ver();
    cout << endl;

    if (f3.bisiesto() && !f2.bisiesto() && f4.bisiesto()==false)
        cout << f3.getAnio() << " es bisiesto, " << f2.getAnio() << " y " << f4.getAnio() << " no\n";
    f4.setFecha(31, 12, 2000); //f4=31/12/2000
    f3=f4++; //Se ejecuta el metodo [Fecha operator++(const int i)]
    ++f4;
    f1=2+f2+3;

    cout << "Fechas: "; f1.ver(); cout << ", ";
    f2.ver(); cout << ", ";f3.ver();cout << ", ";f4.ver();
    cout << endl;

    Cliente *p = new Cliente(75547001, "Susana Diaz", f1);
    f1.setFecha(7, 10, 2015);
    Cliente c(75547999, "Juan Sin Miedo", Fecha(29,2,2000));
    const Cliente j(44228547, "Luis", f1);
    c.setNombre("Juan Palomo");
    if(c==j)
        cout << "\nj y c son iguales\n";
    else
        cout << "\nj y c no son iguales\n";
    cout << p->getDni() << " - " << c.getNombre() << ": " << j.getFecha() << endl;
    cout << *p << "\n" << c << "\n" << j << "\n";
    c= *p;
    p->setNombre("Susanita"); p->setFecha(p->getFecha()+10);
    cout << "\nDatos de los clientes: \n";
    cout << *p << "\n" << c << "\n" << j << "\n";
    delete p; p = NULL;

    system("pause");
    return 0;
}
*/
