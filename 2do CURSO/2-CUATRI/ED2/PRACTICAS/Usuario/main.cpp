#include <iostream>
#include "../Persona/Persona.h"
#include "../Multiconjunto/Multiconjunto.h"

using namespace std;

int main()
{
    cout << "\n\nPrueba con personas--------------------------\n";
    Persona p1("Pepe", 16);
    Persona p2("Juan", 17);
    Persona p3("Alfredo", 18);
    Persona p4("Arbin", 19);
    Multiconjunto<Persona> v3;
    return 0;
}
