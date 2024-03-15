#include <unistd.h>
#include <stdio.h>
#include <signal.h> //Para las señales
#include <stdlib.h> //rand()
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>


void R10()
{
  exit(0);
}


int main()
{
  int fesclusao, fcolaesto;
  int testigo, pidbarco;

  signal(10,R10);
  
  //Abrimos las FIFOS que necesitamos
  fesclusao = open("esclusao", O_RDWR);
  fcolaesto = open("colaoeste", O_RDWR);

  //Hacemos un bucle infinito
  while(1)
  {
    read(fesclusao, &testigo, sizeof(testigo)); //Leemos el testigo de la esclusa

    read(fcolaesto, &pidbarco, sizeof(pidbarco)); //Leemos el pid del barco al que le vamos a dar el testigo

    kill(pidbarco,16); //Mandamos la señal 16 al barco con el pid "pidbarco"
  }
}

