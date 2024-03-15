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
  int fesclusae, fesclusao, flago;
  int testigo = 1;
 
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

  //Abrimos las FIFOS
  fesclusae=open("esclusae", O_RDWR);
  fesclusao=open("esclusao", O_RDWR);
  flago=open("lago", O_RDWR);

  visualiza(colagrafica, VESTEIN, PINTAR, TIPOESTE);

  //NUEVO
  read(flago, &testigo, sizeof(testigo));
  read(fesclusae, &testigo, sizeof(testigo));
  
  //Del oceano a la esclusa este
  visualiza(colagrafica, VESTEIN, BORRAR, TIPOESTE);
  visualiza(colagrafica, VESCLUSAESTE, PINTAR, TIPOESTE);
  sleep(param.tesclusa);

  //De la esclusa este al lago (se pasa el testigo de la esclusa)
  visualiza(colagrafica, VESCLUSAESTE, BORRAR, TIPOESTE);
  visualiza(colagrafica, VLAGOE, PINTAR, TIPOESTE);
  write(fesclusae, &testigo, sizeof(testigo));

  sleep(rand()%(param.lagomax-param.lagomin+1)+param.lagomin); //Intervalo de tiempo de estancia en el lago
  
  //Del lago a la esclusa oeste
  read(fesclusao, &testigo, sizeof(testigo));
  visualiza(colagrafica, VLAGOE, BORRAR, TIPOESTE);
  visualiza(colagrafica, VESCLUSAOESTE, PINTAR, TIPOESTE);
  write(flago, &testigo, sizeof(testigo));

  sleep(param.tesclusa); //Tiempo de estancia en la esclusa
  visualiza(colagrafica, VESCLUSAOESTE, BORRAR, TIPOESTE);
  visualiza(colagrafica, VOESTEOUT, PINTAR, TIPOESTE);
  write(fesclusao, &testigo, sizeof(testigo));

  close(fesclusae);
  close(fesclusao);
  close(flago);
}
