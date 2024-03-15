#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <signal.h>

#include "comun.h"
#include "comunbarcos.h"

int main()
{
  int colagrafica;
 
  srand(getpid()); //Inicializamos la semilla
  //Nos preparamos para recibir la señales 10 y 12
  signal(10,R10);
  signal(12,R12);

  colagrafica=creacola(ftok("fichcola.txt",18)); //Creamos la cola de mensajes

  visualiza(colagrafica, VESTEIN, PINTAR, TIPOESTE);
  sleep(rand()%5+3); //Espera entre [3-7]
  visualiza(colagrafica, VESTEIN, BORRAR, TIPOESTE);
  visualiza(colagrafica, VOESTEOUT, PINTAR, TIPOESTE);

}
