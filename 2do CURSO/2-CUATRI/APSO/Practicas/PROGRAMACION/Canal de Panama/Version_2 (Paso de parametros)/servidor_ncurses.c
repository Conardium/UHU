#include <stdio.h>
#include <stdlib.h>
#include <string.h> 
#include <sys/msg.h>
#include <errno.h>
#include <ncurses.h>
#include <signal.h>
#include <unistd.h>

#include "definiciones.h"
#include "comun.h"

/***** PROTOTIPOS DE FUNCONES *******************/ 
void pinta_escenario(WINDOW **voestein, WINDOW **vestein, WINDOW **voesteout, WINDOW **vesteout, WINDOW **vesclusaoeste, WINDOW **vesclusaeste, WINDOW **vlagoo, WINDOW **vlagoe, WINDOW **vhornos, WINDOW **vmensajes);
void inserta(struct cliente *laventana, int maximo, int elpid, int cualidad);
void quita(struct cliente *laventana, int maximo, int elpid);
void visualiza_peticion(WINDOW *vmensajes,struct tipo_elemento peticion);
void pinta_clientes_inverso(WINDOW *ventana, struct cliente *datos_ventana, int maximo);
void pinta_clientes(WINDOW *vclientes, struct cliente *datos_ventana1, int maximo);
void limpia_array(struct cliente  *datos,int maximo);
void R10();
int crea_cola(key_t clave);

/***** DECLARACION DE VARIABLES GLOBALES ***********/
 
int fin=0;  

 
/***************** MAIN ************************/

int main()
{

 struct cliente datos_voestein[MAXVOESTEIN];
 struct cliente datos_vestein[MAXVESTEIN];
 struct cliente datos_voesteout[MAXVOESTEOUT];
 struct cliente datos_vesteout[MAXVESTEOUT];
 struct cliente datos_vesclusaoeste[MAXVESCLUSAOESTE];
 struct cliente datos_vesclusaeste[MAXVESCLUSAESTE];
 struct cliente datos_vlagoo[MAXVLAGOO];
 struct cliente datos_vlagoe[MAXVLAGOE];
 struct cliente datos_vhornos[MAXVHORNOS];


 struct tipo_elemento peticion;
 key_t Clave1; 
 int Id_Cola_Mensajes;
 int i; 
 WINDOW *voestein, *vestein, *voesteout, *vesteout, *vesclusaoeste, *vesclusaeste, *vlagoo, *vlagoe, *vhornos, *vmensajes;

 // Nos preparamos para recibir la senyal 10
 signal(10,R10);


 //Limpiamos todas los arrays
 limpia_array(datos_voestein,MAXVOESTEIN);
 limpia_array(datos_vestein,MAXVESTEIN);
 limpia_array(datos_voesteout,MAXVOESTEOUT);
 limpia_array(datos_vesteout,MAXVESTEOUT);
 limpia_array(datos_vesclusaoeste,MAXVESCLUSAOESTE);
 limpia_array(datos_vesclusaeste,MAXVESCLUSAESTE);
 limpia_array(datos_vlagoo,MAXVLAGOO);
 limpia_array(datos_vlagoe,MAXVLAGOE);
 limpia_array(datos_vhornos,MAXVHORNOS);

 // Dibujamos el escenario
 pinta_escenario(&voestein, &vestein, &voesteout, &vesteout, &vesclusaoeste, &vesclusaeste, &vlagoo, &vlagoe, &vhornos, &vmensajes);

 // Creamos y abrimos la cola de mensajes
 Clave1 = ftok ("./fichcola.txt", 18);
 Id_Cola_Mensajes=crea_cola(Clave1);
 
 kill(getppid(),10); // avisamos a principal de que todo va bien para continuar


 //espero que lleguen peticiones a la cola de mensajes
 msgrcv(Id_Cola_Mensajes, (struct tipo_elemento *) &peticion,sizeof(struct tipo_elemento)-sizeof(long), 0, 0);  

 while(!fin) // Esto acaba cuando llegue la senyal 10
 {
 	usleep(200000);

   // Visualizo en la zona de mensajes la informacon sobre la peticion
   visualiza_peticion(vmensajes,peticion);

  //decodifico y ejecuto la peticion sobre los arrays
  switch(peticion.donde)
  {
    case VOESTEIN: 
              if(peticion.que==PINTAR) inserta(datos_voestein,MAXVOESTEIN,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_voestein,MAXVOESTEIN,peticion.pid);
	      pinta_clientes_inverso(voestein,datos_voestein,MAXVOESTEIN);
	      //pinta_clientes(voestein,datos_voestein,MAXVOESTEIN);

              break;

    case VESTEIN: 
              if(peticion.que==PINTAR) inserta(datos_vestein,MAXVESTEIN,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vestein,MAXVESTEIN,peticion.pid);
	      pinta_clientes(vestein,datos_vestein,MAXVESTEIN);
              break;

    case VOESTEOUT: 
              if(peticion.que==PINTAR) inserta(datos_voesteout,MAXVOESTEOUT,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_voesteout,MAXVOESTEOUT,peticion.pid);
	      pinta_clientes(voesteout,datos_voesteout,MAXVOESTEOUT);
              break;

    case VESTEOUT: 
              if(peticion.que==PINTAR) inserta(datos_vesteout,MAXVESTEOUT,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vesteout,MAXVESTEOUT,peticion.pid);
	      pinta_clientes_inverso(vesteout,datos_vesteout,MAXVESTEOUT);
              break;

    case VESCLUSAOESTE: 
              if(peticion.que==PINTAR) inserta(datos_vesclusaoeste,MAXVESCLUSAOESTE,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vesclusaoeste,MAXVESCLUSAOESTE,peticion.pid);
	      pinta_clientes(vesclusaoeste,datos_vesclusaoeste,MAXVESCLUSAOESTE);
              break;

    case VESCLUSAESTE:
              if(peticion.que==PINTAR) inserta(datos_vesclusaeste,MAXVESCLUSAESTE,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vesclusaeste,MAXVESCLUSAESTE,peticion.pid);
	      pinta_clientes(vesclusaeste,datos_vesclusaeste,MAXVESCLUSAESTE);
              break;
    

    case VLAGOE: 
              if(peticion.que==PINTAR) inserta(datos_vlagoe,MAXVLAGOE,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vlagoe,MAXVLAGOE,peticion.pid);
	      pinta_clientes(vlagoe,datos_vlagoe,MAXVLAGOE);
              break;

    case VLAGOO: 
              if(peticion.que==PINTAR) inserta(datos_vlagoo,MAXVLAGOO,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vlagoo,MAXVLAGOO,peticion.pid);
	      pinta_clientes(vlagoo,datos_vlagoo,MAXVLAGOO);
              break;

    case VHORNOS: 
              if(peticion.que==PINTAR) inserta(datos_vhornos,MAXVHORNOS,peticion.pid,peticion.cualidad);         
              if(peticion.que==BORRAR) quita(datos_vhornos,MAXVHORNOS,peticion.pid);
	      pinta_clientes(vhornos,datos_vhornos,MAXVHORNOS);
              break;
   }              
  //espero que lleguen peticiones a la cola de mensajes
  msgrcv(Id_Cola_Mensajes, (struct tipo_elemento *) &peticion,sizeof(struct tipo_elemento)-sizeof(long), 0, 0);  


 }
  
 
 endwin(); //Finaliza el uso de ncurses
 msgctl (Id_Cola_Mensajes, IPC_RMID, 0); //Borra la cola de mensajes
}

/*********** FUNCION: crea_cola ********************************************************/ 
/*********** Obtiene acceso a la cola de mensajes con el id que se pasa ****************/
int crea_cola(key_t clave)
{
 int identificador;
 if(clave == (key_t) -1) 
 {
   printf("Error al obtener clave para cola mensajes\n");
   exit(-1);
 }

 identificador = msgget(clave, 0600 | IPC_CREAT);
 //LA BORRAMOS POR SI TIENE MENSAJES DE EJECUCIONES ANTERIORES
 msgctl(identificador,IPC_RMID,NULL); 
 identificador = msgget(clave, 0600 | IPC_CREAT);

 if (identificador == -1)
 {
   printf("Error al obtener identificador para cola mensajes\n");
   perror("msgget");
   exit (-1);
 }
 
 return identificador;
}


/*********** FUNCION: inserta **********************************************************/ 
/*********** Inserta un pid en el array si hay sitio, sino mata la proceso *************/
void inserta(struct cliente *laventana, int maximo, int elpid, int cualidad)
{
 int i=0;

 while(laventana[i].elpid!=0 && i<maximo) i++;

 if(i==maximo) 
  kill(elpid,12); //mato al proceso porque no se puede representar
 else
 {
  laventana[i].elpid=elpid;
  laventana[i].cualidad= cualidad;
  kill(elpid,10); //le digo al proceso que puede continuar
 }
}


/********** FUNCION: elimina ***********************************************************/
/********** Elimina un pid de un array y lo reordena  *********************************/
void quita(struct cliente *laventana, int maximo, int elpid)
{
 int i=0;
 int j;
 
 while(laventana[i].elpid!=elpid && i<maximo) i++;

 if(i==maximo) printf("Error, se intenta borrar un pid que no esta");
 else{ 
  for(j=i;j<maximo-1;j++)
  {
   laventana[j].elpid=laventana[j+1].elpid;
   laventana[j].cualidad=laventana[j+1].cualidad;
  }
  laventana[j].elpid=0;
  laventana[j].cualidad=0;
 }
}



/*********** FUNCION: pinta_escenario ***************************************************/
/*********** Dibuja el escenario *******************************************************/
void pinta_escenario(WINDOW **voestein, WINDOW **vestein, WINDOW **voesteout, WINDOW **vesteout, WINDOW **vesclusaoeste, WINDOW **vesclusaeste, WINDOW **vlagoo, WINDOW **vlagoe, WINDOW **vhornos, WINDOW **vmensajes)
{
 initscr(); // INICIALIZA NCURSES
 start_color(); //INICIALIZA COLORES

 int ANCHO=8;
  
 // ESTO COMPRUEBA SI EL TERMINAL SOPORTA EL USO DE COLORES, SINO ACABA
 if (!has_colors()){
   endwin();
   printf("Esta terminal no tiene colores\n");
   kill(getppid(),12); //mata al principal que espera conformidad con la 10
   exit(-1);
 }
 //COMPROBAMOS QUE TENEMOS LAS LINEAS Y COLUMNAS QUE NECESITAMOS
 if(LINES<30 || COLS<140)
 {
   endwin();
   printf("Se necesitan, al menos 30 lineas y 140 columnas, y tienes %d lineas y %d columnas\n",LINES,COLS);
   kill(getppid(),12); //mata al principal que espera conformidad con la 10
   exit(-1);
 }

  //VISUALIZAMOS LA RESOLUCI ACUTAL DEL TERMINAL
   printw("La pantalla tiene %d lineas y %d columnas)",LINES, COLS);
   refresh();

 // definimos los pares de colores
   init_pair(COLOR_MAR,COLOR_WHITE,COLOR_BLUE);
   init_pair(COLOR_MAR2,COLOR_WHITE,COLOR_CYAN);
   init_pair(COLOR_FONDO,COLOR_WHITE,COLOR_BLACK);
   init_pair(COLOR_BARCOE,COLOR_WHITE,COLOR_BLUE);
   init_pair(COLOR_BARCOO,COLOR_YELLOW,COLOR_BLUE);

 
//***********

 *voestein=newwin(10,ANCHO*5,2,0);
 wbkgd(*voestein,COLOR_PAIR(COLOR_MAR));
 wattron(*voestein,A_BOLD);
 wrefresh(*voestein);

 *voesteout=newwin(10,ANCHO*5,12,0);
 wbkgd(*voesteout,COLOR_PAIR(COLOR_MAR));
 wattron(*voesteout,A_BOLD);
 wrefresh(*voesteout);

 *vesclusaoeste=newwin(3,ANCHO,5,ANCHO*5);
 wbkgd(*vesclusaoeste,COLOR_PAIR(COLOR_MAR2));
 wattron(*vesclusaoeste,A_BOLD);
 wrefresh(*vesclusaoeste);

 *vlagoo=newwin(3,ANCHO*4,4,ANCHO*6);
 wbkgd(*vlagoo,COLOR_PAIR(COLOR_MAR));
 wattron(*vlagoo,A_BOLD);
 wrefresh(*vlagoo);

 *vlagoe=newwin(3,ANCHO*4,7,ANCHO*6);
 wbkgd(*vlagoe,COLOR_PAIR(COLOR_MAR));
 wattron(*vlagoe,A_BOLD);
 wrefresh(*vlagoe);

 *vmensajes=newwin(5,ANCHO*5,11,ANCHO*6);
 wbkgd(*vmensajes,COLOR_PAIR(COLOR_FONDO));
 wattron(*vmensajes,A_BOLD);
 wrefresh(*vmensajes);

 *vesclusaeste=newwin(3,ANCHO,5,ANCHO*10);
 wbkgd(*vesclusaeste,COLOR_PAIR(COLOR_MAR2));
 wattron(*vesclusaeste,A_BOLD);
 wrefresh(*vesclusaeste);

 *vestein=newwin(10,ANCHO*5,2,ANCHO*11);
 wbkgd(*vestein,COLOR_PAIR(COLOR_MAR));
 wattron(*vestein,A_BOLD);
 wrefresh(*vestein);

 *vesteout=newwin(10,ANCHO*5,12,ANCHO*11);
 wbkgd(*vesteout,COLOR_PAIR(COLOR_MAR));
 wattron(*vesteout,A_BOLD);
 wrefresh(*vesteout);

 *vhornos=newwin(10,ANCHO*4,17,ANCHO*6);
 wbkgd(*vhornos,COLOR_PAIR(COLOR_MAR));
 wattron(*vhornos,A_BOLD);
 wrefresh(*vhornos);

}                               

/************** FUNCION: visualiza_peticion  ******************************************************/
/************** Visualiza las peticiones recibidas en la ventana de mensajes *********************/
void visualiza_peticion(WINDOW *vmensajes,struct tipo_elemento peticion)
{    
  werase(vmensajes);
  wprintw(vmensajes,"Recibido mensaje:\n");
  wprintw(vmensajes,"\t pid: %d\n", peticion.pid);
  wprintw(vmensajes,"\t ventana: %d\n", peticion.donde);
  wprintw(vmensajes,"\t operacion: %d\n",peticion.que);
  wprintw(vmensajes,"\t cualidad: %d\n",peticion.cualidad);
  wrefresh(vmensajes);
}


/************** FUNCION: pinta_clientes **********************************************************/
/************** Pinta los clientes en la ventana que indiquemos *********************************/
void pinta_clientes(WINDOW *ventana, struct cliente *datos_ventana, int maximo)
{
    int i;
   
    werase(ventana);
    wprintw(ventana,"\n");
    for(i=0;i<maximo;i++) 
    {
     if(datos_ventana[i].elpid!=0)
     {
       if(datos_ventana[i].cualidad==TIPOOESTE)
       {
	 wattron(ventana,COLOR_PAIR(COLOR_BARCOE));
	 wprintw(ventana," ((%02d>> ",datos_ventana[i].elpid%100);
       }
       if(datos_ventana[i].cualidad==TIPOESTE)      
       {
	 wattron(ventana,COLOR_PAIR(COLOR_BARCOO));
	 wprintw(ventana," <<%02d)) ",datos_ventana[i].elpid%100);
       }
     }
    } 
    wrefresh(ventana);
}

/************** FUNCION: pinta_clientes inverso****************************************************/
/************** Pinta los clientes en la ventana que indiquemos del último al primero*************/
void pinta_clientes_inverso(WINDOW *ventana, struct cliente *datos_ventana, int maximo)
{
    int i,j, lineas;

    lineas=maximo/5; //Está preparado para que represente 5 por linea, por eso el 5
    werase(ventana);
    wprintw(ventana,"\n");
    for(i=0;i<lineas;i++) 
     for(j=4; j>=0; j--)
     {
      if(datos_ventana[j+5*i].elpid!=0)
      {
       if(datos_ventana[j+5*i].cualidad==TIPOOESTE)
       {
	 wattron(ventana,COLOR_PAIR(COLOR_BARCOE));
	 wprintw(ventana," ((%02d>> ",datos_ventana[j+5*i].elpid%100);
       }
       if(datos_ventana[j+5*i].cualidad==TIPOESTE)      
       {
	 wattron(ventana,COLOR_PAIR(COLOR_BARCOO));
	 wprintw(ventana," <<%02d)) ",datos_ventana[j+5*i].elpid%100);
       }
      }
      else
      {
	 wprintw(ventana,"        ");
      }
     
    }
     
    wrefresh(ventana);
}


/************** FUNCION: limpia_array **********************************************************/
/************** Limpia los campos del array que indiquemos ************************************/
void limpia_array(struct cliente  *datos,int maximo)
{
 int i;
   for(i=0;i<maximo;i++) {
      datos[i].elpid=0; 
      datos[i].cualidad=0;
   }
}


/************** FUNCION: R10 *******************************************************************/
/************** Atiende a la senyal 10 cuando llega *******************************************/
void R10()
{
 fin=1; //cuando llega la senyal 10 finalizo el servidor.
}
