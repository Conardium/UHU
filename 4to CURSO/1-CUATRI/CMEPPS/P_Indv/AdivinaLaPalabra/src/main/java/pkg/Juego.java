/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pkg;

import java.util.Random;
import java.util.Scanner;
import sun.security.krb5.internal.crypto.Des3;

/**
 *
 * @author ismae
 */
public class Juego {

    public static void main(String[] args) {

        Jugador j1 = new Jugador(1, "Ismael");
        Jugador j2 = new Jugador(2, "Juanito");
        int turno = 1;
        String intento;
        int partida = 1;

        Palabra palabra = new Palabra();

        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\t=========== Vamos a adivinar la palabra ===========\n");
        System.out.println("Jugadores: " + j1.getNombre() + " y " + j2.getNombre());

        boolean gameOver = false;
        boolean siguientePartida = false;

        Random rn = new Random();
        rn.setSeed(System.currentTimeMillis());

        while (!gameOver) {
            System.out.println("##### SE INICIA LA PARTIDA " + partida + "#####");
            System.out.println("\nPalara a adivinar es: " + palabra.getPalabraDesordenada(rn.nextInt(4)));
            System.out.println("¡¡¡LAS PALABRAS ESTÁN EN MAYUSCULAS, ASI QUE ACTIVA EL BLOQ MAYUS!!!");
            siguientePartida = false;
            while (!siguientePartida) {

                if (turno == 1) {
                    System.out.print("Le toca al Jugador 1: ");
                    intento = sc.nextLine();
                    if (palabra.ComprobarPalabra(intento)) {
                        System.out.println("El Jugador 1 - " + j1.getNombre() + " ha ganado la partida\n");
                        j1.setPuntos();
                        partida++;
                        siguientePartida = true;
                    } else {
                        System.out.println("Que pena, esa no era la palabra correcta\n");
                        turno = 2;
                    }
                } else {
                    System.out.print("Le toca al Jugador 2: ");
                    intento = sc.nextLine();
                    if (palabra.ComprobarPalabra(intento)) {
                        System.out.println("El Jugador 2 - " + j2.getNombre() + " ha ganado la partida");
                        j2.setPuntos();
                        partida++;
                        siguientePartida = true;
                    } else {
                        System.out.println("Que pena, esa no era la palabra correcta\n");
                        turno = 1;
                    }
                }
            }
            if (j1.getPuntos() == 2) {
                System.out.println("\nEl jugador 1 ha ganado el juego, ¡¡ENHORABUERA!!");
                gameOver = true;
            } else if (j2.getPuntos() == 2) {
                System.out.println("\nEl jugador 2 ha ganado el juego, ¡¡ENHORABUERA!!");
                gameOver = true;
            }
        }

        System.out.println("=== Se acabó el juego ===");
    }
}
