/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_amc;

import java.util.Scanner;


public class Main 
{

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String opcionPrincipal, op1, op2, op11, op12;
        String[] caminoMin;
        int[][] matrizAd;
        String fichero;
        int sumaTotal = 0;
                
        System.out.print("Desea ejecutar la parte 1 o la parte 2 de la practica: " );
        opcionPrincipal = sc.nextLine();
        switch(opcionPrincipal)
        {
            case "1": //Parte 1 de la practia
                System.out.print("\nDesea abrir un archivo ya existente(1), crear puntos aleatorios(2) o realizar un estudio del tiempo(3): ");
                op1 = sc.nextLine();
                switch(op1)
                {
                    case "1": //Abrir un archivo
                        Punto[] puntos = AbrirFichero();
                        System.out.print("\nDesea ejecutarlo con exhaustivo(1) o con DyV(2): ");
                        op11 = sc.nextLine();
                        switch(op11)
                        {
                            case "1": //EXHAUSTIVO
                                MostrarExhaustivo(puntos);
                                break;
                                
                            case "2": //DyV
                                MostrarDyV(puntos);
                                break;
                        }
                        break;
                        
                    case "2": //Generar puntos aleatorios
                        int n=0;
                        System.out.print("Cuantos puntos quieres generar: ");
                        n = sc.nextInt();
                        Scanner scaner = new Scanner(System.in);
                        Punto[] p = Algoritmos.GenerarPuntosAleatorios(n);
                       
                        System.out.print("\nDesea ejecutarlo con exhaustivo(1) o con DyV(2): ");
                        op12 = scaner.nextLine();
                        switch(op12)
                        {
                            case "1": //EXHAUSTIVO
                                MostrarExhaustivo(p);
                                break;
                                
                            case "2": //DyV
                                MostrarDyV(p);
                                break;
                        }
                        break;
                        
                    case "3": //Estudio para calcular el coste temporal de cada algoritmo
                        int TallaIni = 200;
                        int TallaFin = 5000; //hace falta 17 ejecuciones para llegar a 5000 partiendo de 200 con un incremento de 300
                        int Incremento = 300;
                        long comienzoEx, comienzoDyV, finEx, finDyV; //Para obtener el tiempo de cada 
                        
                        Trio solEx, solDyV;
                        
                        System.out.println("\n\tExhaustivo\t\tDyV");
                        
                        for(int i = TallaIni; i<=TallaFin; i+=Incremento)
                        {
                            //Primero generamos un array de puntos aleatorios con la talla iesima
                            Punto[] pRandom = Algoritmos.GenerarPuntosAleatorios(i);
                            
                            //Utilizamos System.nanoTime() para calcular el tiempo que tarda cada algoritmo con el array de puntos
                            comienzoEx = System.nanoTime();
                            solEx = Trio.exhaustivo(pRandom, 0, pRandom.length-1);
                            finEx = System.nanoTime() - comienzoEx;
                            
                            
                            Algoritmos.ordenaQuick(pRandom);  //Primero ordenamos ya que es necesario para DyV
                            comienzoDyV = System.nanoTime();
                            solDyV = Algoritmos.DyV(pRandom, 0, pRandom.length-1);
                            finDyV = System.nanoTime() - comienzoDyV;
                            
                            System.out.println(i + "\t" + finEx + "\t\t" + finDyV);
                        }
                        
                        break;
                }
            break;
            
            case "2": //Parte 2 de la practica
                System.out.print("\nDesea abrir un archivo ya existente(1), crear puntos aleatorios(2) o realizar un estudio empirico(3): ");
                op2 = sc.nextLine();
                switch(op2)
                {
                    case "1": //Abrir un archibo tsp
                        Punto[] puntos = null;
                        Scanner scan = new Scanner(System.in);
                        System.out.print("\nEscribe el nombre del archivo: ");
                        fichero = scan.nextLine();
                        String ruta = "./src/DataSet/"+ fichero + ".tsp" + "/" + fichero + ".tsp";
                        puntos = FicherosTSP.LeerFichero(ruta); //Asigamos al array P los puntos del fichero seleccionado
                        
                        caminoMin= new String[puntos.length];
                        
                        String op21;
                        matrizAd = Algoritmos.generarMatrizDeAdyacencia(puntos);
                        
                        int[] D = Algoritmos.algoritmoDijkstra(matrizAd, caminoMin);
          
                        System.out.print("\nAplicando Dijkstra...");
                        System.out.print("\nLa solucion es: {");
                        for(int i = 1; i<D.length; i++)
                        {
                            System.out.print(D[i] + ", ");
                            sumaTotal = sumaTotal + D[i];
                        }
                        System.out.println("}");
                        System.out.println("\nEl total es: " + sumaTotal);
                        
                        for(int i=1; i< puntos.length; i++)
                        {
                            System.out.println(caminoMin[i]);
                        }
                        
                        System.out.print("Desea guardar el resultado(s),(n): ");
                        op21 = sc.nextLine();
                        switch(op21)
                        {
                            case "s":
                                String rutaDeGuardado = "./src/DataSet/" + fichero + ".opt.tour";
                                System.out.println("\nEl fichero se guardara en: " + rutaDeGuardado + "\n");
                                FicherosTSP.guardarTour(rutaDeGuardado, puntos, sumaTotal, caminoMin, D);
                                break;
                    
                            default:
                                System.out.println("El resultado no se guardara...\n");
                                break;
                        }
                        break;
                        
                    case "2": //Generar puntos aleatorios
                        int n=0;
                        System.out.print("\nCuantos puntos quieres generar: ");
                        n = sc.nextInt();
                        Punto[] p = Algoritmos.GenerarPuntosAleatorios(n);
                        caminoMin= new String[n];
                        
                        String op22;
                        Scanner scaner = new Scanner(System.in);
                        matrizAd = Algoritmos.generarMatrizDeAdyacencia(p);
                        
                        int[] E = Algoritmos.algoritmoDijkstra(matrizAd, caminoMin);
          
                        System.out.print("\nAplicando Dijkstra...");
                        System.out.print("\nLa solucion es: {");
                        for(int i = 1; i<E.length; i++)
                        {
                            System.out.print(E[i] + ", ");
                            sumaTotal = sumaTotal + E[i];
                        }
                        System.out.println("}");
                        System.out.println("\nEl total es: " + sumaTotal);
                        
                        for(int i=1; i< p.length; i++)
                            System.out.println(caminoMin[i]);
                        
                        System.out.print("Desea guardar el resultado(s),(n): ");
                        op22 = scaner.nextLine();
                        switch(op22)
                        {
                            case "s":
                                String fich;
                                Scanner escan = new Scanner(System.in);
                                System.out.print("\nIndica el nombre del fichero: ");
                                fich = escan.nextLine();
                                String rutaDeGuardado = "./src/DataSet/" + fich + ".opt.tour";
                                System.out.println("\nEl fichero se guardara en: " + rutaDeGuardado + "\n");
                                FicherosTSP.guardarTour(rutaDeGuardado, p, sumaTotal, caminoMin, E);
                                break;
                    
                            default:
                                System.out.println("El resultado no se guardara...\n");
                                break;
                        }
                        break;
                        
                    case "3": //Estudio empirico
                        int TallaIni = 200;
                        int TallaFin = 5000; //hace falta 17 ejecuciones para llegar a 5000 partiendo de 200 con un incremento de 300
                        int Incremento = 300;
                        long comienzo, fin = 0;
                        double media;
                        
                        System.out.println("\n\tDijkstra");
                        for(int i = TallaIni; i<=TallaFin; i+=Incremento)
                        {
                            for(int j = 0; j < 10; j++) //10 ejecuciones para cada talla
                            {
                                Punto[] pRandom = Algoritmos.GenerarPuntosAleatorios(i);
                                caminoMin= new String[pRandom.length];
                                matrizAd = Algoritmos.generarMatrizDeAdyacencia(pRandom);
                        
                                comienzo = System.nanoTime();
                                int[] solucion = Algoritmos.algoritmoDijkstra(matrizAd, caminoMin);
                                fin += (System.nanoTime() - comienzo);
                            }
                            media = (double)fin/10;
                            System.out.println(i + "\t" + media);
                        }
                        
                        break;
                }
                break;
        }
    }
    
    public static void MostrarExhaustivo(Punto[] Pfichero)
    {
        Trio sol = Trio.exhaustivo(Pfichero, 0, Pfichero.length-1); 
        System.out.println("\n---Con exhaustivo---");
        System.out.println("El trio de puntos más cercano es:");
        System.out.println(sol.getP1());
        System.out.println(sol.getP2() + "\t\tEl punto comun a otros dos es: " + sol.getPuntoComun());
        System.out.println(sol.getP3());
    }
    
    public static void MostrarDyV(Punto[] Pfichero)
    {
        Algoritmos.ordenaQuick(Pfichero);  //Primero ordenamos ya que es necesario para las tecnicas de DyV
        Trio sol2 = Algoritmos.DyV(Pfichero, 0, Pfichero.length-1); //A continuacion aplicamos DyV
        System.out.println("\n---Con DyV---");
        System.out.println("El trio de puntos más cercano es:");
        System.out.println(sol2.getP1());
        System.out.println(sol2.getP2() + "\t\tEl punto comun a otros dos es: " + sol2.getPuntoComun());
        System.out.println(sol2.getP3());
    }

    //GENERAL
    public static Punto[] AbrirFichero() 
    {
        Punto[] Pfichero = null;
        String fichero;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEscribe el nombre del archivo: ");
        fichero = sc.nextLine();
        String ruta = "./src/DataSet/"+ fichero + ".tsp" + "/" + fichero + ".tsp";
        Pfichero = FicherosTSP.LeerFichero(ruta); //Asigamos al array P los puntos del fichero seleccionado
        
        return Pfichero;           
    }
}
