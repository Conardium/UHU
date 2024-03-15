#include <unistd.h>
#include <stdio.h>
#include <signal.h> //Para las señales
#include <stdlib.h> //rand()
#include <fcntl.h>
#include <sys/msg.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>

struct Parametros
{
 int tesclusa;  // Tiempo de estancia en la esclusa
 int lagomin;   //Intervalo de tiempo en cruzar el lago MIN
 int lagomax;   //Intervalo de tiempo en cruzar el lago MAX
 int mevoymin;  //Intervalo de tiempo en esperar para irse MIN
 int mevoymax;  //Intervalo de tiempo en esperar para irse MAX
};

void R10();
void R12();
int llega10 = 0;  //Variable local para controlar la llegada de la señal 10 del
		  //servidor gráfico

void leeparametros(struct Parametros *param,int *maxbarcos,int *creamin,int *creamax, int *caplago);
int creaproceso(const char nombre[], const char redire[]);
int creaprocesotub(const char nombre[], int tub[], int canal);


//------------------------------- MAIN ---------------------------------
int main()
{
  int pservidorgraf, i;
  int testigo = 1;

  struct Parametros param;
  int maxbarcos,creamin,creamax,caplago;
  int fesclusae, fesclusao, flago;

  //Creamos la tuberia:
  int tubo[2];
  pipe(tubo);

  //Creamos y abrimos las FIFOS
  mkfifo("esclusae",0600);
  mkfifo("esclusao",0600);
  mkfifo("lago",0600);

  fesclusae=open("esclusae", O_RDWR);
  fesclusao=open("esclusao", O_RDWR);
  flago=open("lago", O_RDWR);

  //Nos preparamos para recibir las señales 10 y 12
  signal(10,R10);
  signal(12,R12);
  srand(getpid()); //Inicializamos la semilla

  leeparametros(&param,&maxbarcos,&creamin,&creamax,&caplago); //Parametros para la practica

  for(i=0; i<caplago; i++)
    write(flago, &testigo, sizeof(testigo));

  //Escribimos en las FIFOS los testigos (ponemos un 1 en las esclusas y tantos 1 como capacidad tenga el lago)
  write(fesclusae, &testigo, sizeof(testigo));
  write(fesclusao, &testigo, sizeof(testigo));

  pservidorgraf = creaproceso("servidor_ncurses",NULL);
  
  //Vemos si llega10 es 1 o 0, si es 0(no ha llegado la señal) asi ke hace el pause().
  if(!llega10) pause(); 

  //---Si todo ok, creamos los barcos---
  for(i=1; i<= maxbarcos; i++) 
  {
    //Si el numero es par se crea el barco este, sino el barco oeste
    if(rand()%2==0) creaprocesotub("barcoeste",tubo,2);
    else creaprocesotub("barcooeste",tubo,2);

    write(tubo[1],&param,sizeof(param)); //Escribimos en la tuberia
    sleep((rand()%(creamax-creamin+1))+creamin);
  }

  //Esperamos a que terminen los barcos
  for(i=1; i<= maxbarcos; i++) wait(NULL);

  //Una vez termina, enviamos la señal 10 al servidor grafico para acabar el programa
  sleep(5);
  kill(pservidorgraf,10);

  close(fesclusae);
  close(fesclusao);
  close(flago);

  unlink("esclusae");
  unlink("esclusao");
  unlink("lago");

  return 0;
}
//---------------------------------------------------------------------------------------------------------

void R10()
{
  llega10=1;
}

void R12()
{
  printf("No hes posible arrancar el servidor grafico\n");
  exit(-1);
}


//------------------ CREAPROCESO -----------------------/
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

//---------------- CREAPROCESOTUB --------------------/
int creaprocesotub(const char nombre[], int tub[], int canal)
{
  int vpid;
  vpid=fork();
  if(vpid==0)
  {
    close(canal);
    dup(tub[0]);
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

//------------------------------------ LEEPARAMETROS ---------------------------------------------/
void leeparametros(struct Parametros *param,int *maxbarcos,int *creamin,int *creamax, int *caplago)
{
 int ok=9;

 *maxbarcos=10; //Numero de barcos que se crearan
 *creamin=1;   //Intervalo de tiempo para crear nuevos barcos MIN
 *creamax=5;   //Intervalo de tiempo para crear nuevos barcos MAX
 *caplago=6;   //Capacidad del lago
 param->tesclusa=7;  // Tiempo de estancia en la esclusa
 param->lagomin=5;   //Intervalo de tiempo en cruzar el lago MIN
 param->lagomax=10;   //Intervalo de tiempo en cruzar el lago MAX
 param->mevoymin=10;  //Intervalo de tiempo en esperar para irse MIN
 param->mevoymax=15;  //Intervalo de tiempo en esperar para irse MAX
 
 while(ok == 9)
 {
	system("clear");
	printf("Valores de los parámetros...\n\n");
	printf("Numero de barcos que se crearan: %d\n",*maxbarcos);
	printf("Intervalo de tiempo para crear nuevos barcos: [%d-%d] \n",*creamin,*creamax);
	printf("Tiempo de estancia en la esclusa: %d\n",param->tesclusa);
	printf("Capacidad del lago: %d\n",*caplago);
	printf("Intervalo de tiempo en cruzar el lago: [%d-%d]\n",param->lagomin,param->lagomax);
	printf("Intervalo de tiempo en esperar para irse: [%d-%d]\n",param->mevoymin,param->mevoymax); 
	printf("Pulse 9 si desea introducir nuevos valores, cualquier otro valor si desea continuar.\n");
	scanf("%d",&ok);

	if(ok == 9){
		do{
			printf("Numero de barcos que se crearan [maximo 50]:\n");
			scanf("%d",maxbarcos);
		}while(*maxbarcos <= 0 || *maxbarcos > 50);

		do{
			printf("Intervalo de tiempo para crear nuevos barcos MIN [entre 1 y 10]: \n");
			scanf("%d",creamin);
		}while(*creamin< 1 ||*creamin > 10 );

		do{
			printf("Intervalo de tiempo para crear nuevos barcos MAX [entre 5 y 20]: \n");
			scanf("%d",creamax);
		}while(*creamax < 5 || *creamax > 20 || *creamax<=*creamin);
 
		do{
			printf("Tiempo de estancia en la esclusa [maximo 20]: \n");
			scanf("%d",&param->tesclusa);
		}while(param->tesclusa <= 0 || param->tesclusa > 20);
	
		do{
			printf("Capacidad del lago [maximo 8]: \n");
			scanf("%d",caplago);
		}while(*caplago <= 1 || *caplago > 8);
	
		do{
			printf("Intervalo de tiempo en cruzar el lago MIN [entre 1 y 10]: \n");
			scanf("%d",&param->lagomin);
		}while(param->lagomin< 1 ||param->lagomin > 10 );
	
		do{
			printf("Intervalo de tiempo en cruzar el lago MAX [entre 5 y 20]: \n");
			scanf("%d",&param->lagomax);
		}while(param->lagomax < 5 || param->lagomax > 20 || param->lagomax <= param->lagomin );
	
		do{
			printf("Intervalo de tiempo en esperar para irse MIN [entre 1 y 10]:\n");
			scanf("%d",&param->mevoymin);
		}while(param->mevoymin< 1 ||param->mevoymin > 10 );
	
		do{
			printf("Intervalo de tiempo en esperar para irse MAX [entre 5 y 20]:\n");
			scanf("%d",&param->mevoymax);
		}while(param->mevoymax < 5 || param->mevoymax > 20 || param->mevoymax <= param->mevoymin);
	}
 }
}

