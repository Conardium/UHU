#define VOESTEIN 1
#define VESTEIN 2
#define VOESTEOUT 3
#define VESTEOUT 4
#define VESCLUSAOESTE 5
#define VESCLUSAESTE 6
#define VLAGOE 7
#define VLAGOO 8
#define VHORNOS 9

#define TIPOOESTE 1
#define TIPOESTE 2

#define PINTAR 1
#define BORRAR 0

// informacion que se lee de peticiones de escritura
struct tipo_elemento{
 long tipo; //obligatorio para la cola de mensajes
 int pid;
 int donde;
 int que;
 int cualidad;
};
 

