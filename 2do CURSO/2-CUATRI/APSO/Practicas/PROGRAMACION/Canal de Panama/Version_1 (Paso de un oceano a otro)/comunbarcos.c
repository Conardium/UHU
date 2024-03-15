#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/msg.h>

#include "comun.h"
#include "comunbarcos.h"

//Variable Global
int llega10 = 0;


int creacola(key_t clave)
{
  int identificador;
  if(clave == (key_t)-1)
  {
    printf("Error al obtener clave para la cola de mensajes\n");
    exit(-1);
  }
  identificador = msgget(clave, 0600 | IPC_CREAT); //LINEA IMPORTANTEDE LA FUNCION
  if(identificador == -1)
  {
    printf("Error al obtener identificador para la cola de mensajes\n");
    perror("msgget"); //No es necesario ponerlo
    exit(-1);
  }
  return identificador;
}


//------------------FUNCION MUY IMPORTANE------------------
void visualiza(int cola, int donde, int que, int como)
{
  struct tipo_elemento peticion;

  peticion.tipo=1; //Da igual que numero se ponga
  peticion.pid=getpid();
  peticion.donde=donde;
  peticion.que=que;
  peticion.cualidad=como;

  msgsnd(cola,(struct tipo_elemento *)&peticion,sizeof(peticion)-sizeof(long),0);
  
  if(que == PINTAR) //Si vamos a BORRAR, no esperamos a la señal 10
  {
    if(!llega10) pause(); //Si no ha llegado la señal 10 hacemos pause().
    llega10=0; //Inicializamos llega10 a 0
  }
}


void R10()
{
  llega10=1;
}


void R12()
{
  printf("EL BARCO %i: No se puede pintar en la ventana, no hay mas sitio\n",getpid());
  exit(-1);
}


