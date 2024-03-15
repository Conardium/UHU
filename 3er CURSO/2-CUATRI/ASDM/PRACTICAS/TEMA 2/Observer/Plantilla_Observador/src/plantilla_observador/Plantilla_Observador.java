package plantilla_observador;

import java.util.Scanner;

public class Plantilla_Observador {

    
    public static void main(String[] args) {
        I_Sujeto sujeto;
        Observador ob1;
        Observador ob2;
        
        Scanner sc = new Scanner(System.in);
        String accion, lugar;
        
        System.out.print("Introduzca la accion: ");
        accion = sc.nextLine();
        System.out.print("Introduzca el lugar: ");
        lugar = sc.nextLine();
        
        sujeto = new Sujeto(accion, lugar);
        ob1 = new Observador_Concreto_1();
        ob2 = new Observador_Concreto_2();
        System.out.println("\n--- Registro de los observadores ---");
        sujeto.registrarObservador(ob2);
        sujeto.registrarObservador(ob1);
        sujeto.ejecutarAccion(accion, lugar); //Los dos observadores ejecutan la accion
        sujeto.retirarObservador(ob2); //Borramos el observador 2
        sujeto.ejecutarAccion(accion, lugar); //Solo el que queda hace la accion
    }
    
}
