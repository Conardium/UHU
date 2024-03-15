#include <unistd.h>
#include <stdio.h>
#include <signal.h> //Para las señales
#include <stdlib.h> //rand()
#include <fcntl.h>
#include <sys/msg.h>
#include <sys/wait.h>

#define MAXBARCOS 10

void R10();
void R12();
int llega10 = 0;  //Variable local para controlar la llegada de la señal 10 del
		  //servidor gráfico

int creaproceso(const char nombre[], const char redire[]);


//------------------------------- MAIN ---------------------------------
int main()
{
  int pservidorgraf, i;

  signal(10,R10);
  signal(12,R12);

  srand(getpid()); //Inicializamos la semilla

  pservidorgraf = creaproceso("servidor_ncurses",NULL);
  
  //Vemos si llega10 es 1 o 0, si es 0(no ha llegado la señal) asi ke hace el pause().
  if(!llega10) pause(); 

  //Si todo ok, creamos los barcos
  for(i=1; i<= MAXBARCOS; i++) 
  {
    //Si el numero es par se crea el barco este, sino el barco oeste
    if(rand()%2==0) creaproceso("barcoeste",NULL);
    else creaproceso("barcooeste",NULL);
    sleep(rand()%3+1); //Hace un sleep de [1-3] seg
  }

  //Esperamos a que terminen los barcos
  for(i=1; i<= MAXBARCOS; i++) wait(NULL);

  //Una vez termina, enviamos la señal 10 al servidor grafico para acabar el programa
  sleep(5);
  kill(pservidorgraf,10);

  return 0;
}
//---------------

void R10()
{
  llega10=1;
}

void R12()
{
  printf("No hes posible arrancar el servidor grafico\n");
  exit(-1);
}


//---------------- funcion para crear procesos ---------------------
int creaproceso(const char nombre[], const char redire[])
{
  int vpid;
  vpid=fork();
  if(vpid==0)
  {
    if(redire != NULL)
    {
      close(1);
      open(redire, O_WRONLY|O_CREAT,0600);
    }
    execl(nombre,nombre,NULL);
    perror("Error de execl");
    exit(-1);
  }
  else if(vpid==-1)
  {
    perror("Error de fork");
    exit(-1);
  }
  return vpid;
}
