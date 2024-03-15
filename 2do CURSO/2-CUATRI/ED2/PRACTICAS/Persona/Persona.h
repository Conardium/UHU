#ifndef __PERSONA_H__
#define __PERSONA_H__

#include <windows.h>
#include <string>

using namespace std;

/*  To use this exported function of dll, include this header
 *  in your project.
 */

#ifdef BUILD_DLL
    #define DLL_EXPORT __declspec(dllexport)
#else
    #define DLL_EXPORT __declspec(dllimport)
#endif


#ifdef __cplusplus
extern "C"
{
#endif

void DLL_EXPORT SomeFunction(const LPCSTR sometext);

#ifdef __cplusplus
}
#endif

class Persona
{
    string nombre;
    int edad;

    public:
        Persona(const string& n = "", int e = 0);

        const string& getNombre() const;

        int getEdad() const;

        void setNombre(const string& n);

        void setEdad(int e);

        bool operator==(const Persona& p) const;
};

#endif // __PERSONA_H__
