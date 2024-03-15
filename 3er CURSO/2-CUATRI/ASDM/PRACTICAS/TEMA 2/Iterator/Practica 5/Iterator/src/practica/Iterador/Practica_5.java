
package practica.Iterador;

import java.util.Scanner;


public class Practica_5 {

    static Scanner sc_int = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int opcion = -1, tipo, opcionRecorrido;
        boolean saltar = false;
        
        I_Lista lista;
        I_Iterador iterador;
        Creador miFabrica; //Uso del Factory
        
        System.out.print("Indica que tipo de Lista quiere crear (1.-Numerica, 2.-Alfabetica): ");
        tipo = sc_int.nextInt();
        miFabrica = new Creador_Concreto(tipo);
        lista = miFabrica.Factory_method(); //Creamos una Lista de Numeros o de Letras con el Factory_method().
        
        //Segun que tipo de lista sea, rellenamos una u otra
        if(tipo == 1){ //Rellenamos la lista de numeros
            for (int i = 0; i < 10; i++) {
                lista.Agregar(i);
            }
        }
        else{ //Rellenamos la lista de letras
            lista.Agregar('A');
            lista.Agregar('B');
            lista.Agregar('C');
            lista.Agregar('D');
            lista.Agregar('E');
            lista.Agregar('F');
            lista.Agregar('G');
            lista.Agregar('H');
            lista.Agregar('I');
            lista.Agregar('J');
        }
        
        //Ahora se indica si se quiere recorrer de principio a fin(Cola) o al revés(Pila)
        System.out.print("Indica el tipo de recorrido (1.-Cola, 2.-Pila): ");
        opcionRecorrido = sc_int.nextInt();
        System.out.println("\n");
        
        if(opcionRecorrido == 1)
            iterador = lista.crearIteradorCola();
        else
            iterador = lista.crearIteradorPila();
        
        while (iterador.haySiguiente()) {
            
            if(!saltar)
                opcion = MenuLista();
            
            switch(opcion){
                case 0: //Mostrar todos
                    System.out.print(iterador.Siguiente() + (iterador.haySiguiente()? " - " : "\n"));
                    saltar = true;
                    break;
                case 1: //Mostrar siguiente
                    System.out.println("\n<" + iterador.Siguiente() + ">");
                    break;
                case 2: //Mostrar datos del anterior y del siguiente
                    System.out.println("\nAnterior: " + iterador.mostrarAnterior() + " - Siguiente: " + iterador.mostrarSiguiente());
                    break;
            }
        }
    }
    
    public static int MenuLista() {
        int valor;
        System.out.println("1.- Ir al siguiente");
        System.out.println("2.- Mostrar datos de posicion actual");
        System.out.println("0.- Recorrer el resto");
        System.out.print("\nElija una opcion: ");
        valor = sc_int.nextInt();
        return valor;
    }
}
