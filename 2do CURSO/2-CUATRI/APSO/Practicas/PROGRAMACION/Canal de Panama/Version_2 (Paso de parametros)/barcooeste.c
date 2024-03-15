#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <signal.h>
#include <fcntl.h>

#include "comun.h"
#include "comunbarcos.h"

int main()
{
  int colagrafica, lapipe;
  struct Parametros param;
 
  srand(getpid()); //Inicializamos la semilla
  //Nos preparamos para recibir la señales 10 y 12
  signal(10,R10);
  signal(12,R12);

  colagrafica=creacola(ftok("fichcola.txt",18)); //Creamos la cola de mensajes

  //Leemos de la tuberia
  read(2,&param,sizeof(param));

  //Recuperamos la posicion 2(ERRORES) de la T.C.
  lapipe=dup(2);
  close(2);
  open("/dev/tty",O_RDWR);

  visualiza(colagrafica, VOESTEIN, PINTAR, TIPOOESTE);
  sleep(rand()%(param.lagomax-param.lagomin+1)+param.lagomin); //Intervalo de tiempo de estancia en el lago
  visualiza(colagrafica, VOESTEIN, BORRAR, TIPOOESTE);
  visualiza(colagrafica, VESTEOUT, PINTAR, TIPOOESTE);
}
