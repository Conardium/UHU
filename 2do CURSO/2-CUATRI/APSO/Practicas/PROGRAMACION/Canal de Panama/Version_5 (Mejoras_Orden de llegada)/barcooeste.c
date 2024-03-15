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

//Variables Globales
int colagrafica, fesclusae, fesclusao, flago, llega16, fcolaoeste, fcolaeste, fcolalago;

extern int llega14; //Ya que la variable llega14 es externa (de otro fichero)

void R16()
{
  llega16=1;
}


int main()
{
  int lapipe, mipid;
  struct Parametros param;
  int testigo = 1;
 
  srand(getpid()); //Inicializamos la semilla
  //Nos preparamos para recibir la señales 10, 12, 14 y 16
  signal(10,R10);
  signal(12,R12);
  signal(14,R14);
  signal(16,R16);

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

  fcolaeste = open("colaeste", O_RDWR);
  fcolaoeste = open("colaoeste", O_RDWR);
  fcolalago = open("colalago", O_RDWR);


  visualiza(colagrafica, VOESTEIN, PINTAR, TIPOOESTE);

  //Lanzamos la alarma
  alarm(rand()%(param.mevoymax-param.mevoymin+1)+param.mevoymin);

  
  //---------------- NUEVO ---------------//
  mipid = getpid();
  write(fcolalago,&mipid, sizeof(mipid));
  if(!llega16) pause();

  if(llega14)
  {
    visualiza(colagrafica, VOESTEIN, BORRAR, TIPOOESTE);
    visualiza(colagrafica, VHORNOS, PINTAR, TIPOOESTE);
    if(!llega16) pause();
    write(flago, &testigo, sizeof(testigo));
  }

  else
  {
    llega16 = 0;
    
    //Descativamos la alarma
    alarm(0);

    write(fcolaoeste,&mipid, sizeof(mipid));
    if(!llega16) pause();
    llega16=0;
  
    //Del oceano a la esclusa oeste
    visualiza(colagrafica, VOESTEIN, BORRAR, TIPOOESTE);
    visualiza(colagrafica, VESCLUSAOESTE, PINTAR, TIPOOESTE);
    sleep(param.tesclusa);

    //De la esclusa este al lago (se pasa el testigo de la esclusa)
    visualiza(colagrafica, VESCLUSAOESTE, BORRAR, TIPOOESTE);
    visualiza(colagrafica, VLAGOO, PINTAR, TIPOOESTE);
    write(fesclusao, &testigo, sizeof(testigo));

    sleep(rand()%(param.lagomax-param.lagomin+1)+param.lagomin); //Intervalo de tiempo de estancia en el lago
  
    //Del lago a la esclusa este
    write(fcolaeste,&mipid, sizeof(mipid));
    if(!llega16) pause();
    llega16=0;    


    visualiza(colagrafica, VLAGOO, BORRAR, TIPOOESTE);
    visualiza(colagrafica, VESCLUSAESTE, PINTAR, TIPOOESTE);
    write(flago, &testigo, sizeof(testigo));

    sleep(param.tesclusa); //Tiempo de estancia en la esclusa
    visualiza(colagrafica, VESCLUSAESTE, BORRAR, TIPOOESTE);
    visualiza(colagrafica, VESTEOUT, PINTAR, TIPOOESTE);
    write(fesclusae, &testigo, sizeof(testigo));
  }
 //--------------------------------------//

  //Cerramos las FIFOS
  close(fesclusae);
  close(fesclusao);
  close(flago);

  close(fcolaeste);
  close(fcolaoeste);
  close(fcolalago);
}
