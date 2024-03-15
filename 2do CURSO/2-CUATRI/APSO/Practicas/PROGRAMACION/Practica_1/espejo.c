#include <unistd.h> //para el read y el write

int main() //Es el codigo del comando 'cat'
{
  char c;
  while (read(0,&c,sizeof(c)) > 0) //El read devuelve la cantidad de bits escritos
	write(1,&c,sizeof(c));     //El write tb devuelve un entero
  
  return 0;
}
