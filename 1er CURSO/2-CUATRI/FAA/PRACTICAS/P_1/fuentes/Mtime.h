#ifndef _MTIME
#define _MTIME

#include <stdio.h>
#include <windows.h>
/////////// Declaración de la clase TIEMPO /////////////

class Mtime
{
public:
	/* retorna "a - b" en segundos */
	double performancecounter_diff(LARGE_INTEGER *a, LARGE_INTEGER *b);
};
//
//
///* retorna "a - b" en segundos */
//double performancecounter_diff(LARGE_INTEGER *a, LARGE_INTEGER *b)
//{
//LARGE_INTEGER freq;
//QueryPerformanceFrequency(&freq);
//return (double)(a->QuadPart - b->QuadPart) / (double)freq.QuadPart;
//}

#endif

