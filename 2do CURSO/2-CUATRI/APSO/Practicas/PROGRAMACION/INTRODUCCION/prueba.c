		        
#include <stdio.h>  //Para poder usar la E/S por defecto (printf)

#include <math.h> //Para poder usar funciones matematicas                               

//#include <ncurses.h> //Para representar la parte gráfica de la practica


int main()
{
  printf("Hola\n");  //Obligatorio poner un "\n" (porque sino no lo muestra)
  return 0;
}


//Para guardar el fichero con otro nombre que no sea "a.out" es ---> cc prueba.c -o nombreKeSea

//A la hora de compilar con la libreria "math.h" hace falta poner la opcion "-lm" al final -----> cc prueba.c -o NombreKeSea -lm

//Para la libreria "ncurses.h" hay que poner al final "-lncurses" ----> cc prueba.c -o NombreKeSea -lncurses


//IMPORTANTISIMO usar los ficheros "makefile" ya que te permite compilar muchos ficheros a la vez sin necesidad de tener
//que hacer cc en todos los ficheros. Para cada practica es obligatorio hacer un makefile.

//En los MakeFile lo normal es no usar variables. Tenemos que poner "all" y dentro se pone el nombre de los ficheros que se
//van a compilar sin el nombre del ejecutable.
//Se pone debajo el nombre del ejecutable, en nuestro caso "prueba". y se dice de quien depende
//Luego debajo se pone el codigo de compilacion: "cc prueba.c -o prueba"

//NO VALE ESPACIOS EN EL MAKEFILE, SOLO VALE CON UN TABULADOR

//Se debe guardar como "Makefile"

//Para ejecutar el Makefile se usa el comando: make


/*
read(canal, variable, tamaño) ---> sirve para leer una posicion en la tabla de canales
                                     ·El canal va a ser 0, 1 o 2 (suele usarse el 0)
                                     ·La variable es donde se va a guardar la info del canal, se pasa por referencia (&)
                                     ·El tamaño en bytes que vamos a leer del canal ( usar sizeof(variable) ).


---- TABLA DE CANALES ---- [PRACTICA 4]
0 -> entrada estandar (teclado)
1 -> salida estandar (pantalla)
2 -> errores


write(canal, variable, tamaño) ---> hace lo mismo que read pero escribiendo en el canal indicado
                                    suele ponerse en los canales 0 y 1 (suele usarse el 1).
                                    ·Se escribe en el canal lo que hay en la variable (la variable no debe estar vacia).
*/









