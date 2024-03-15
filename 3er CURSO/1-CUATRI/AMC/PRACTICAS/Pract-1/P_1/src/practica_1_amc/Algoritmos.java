/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_amc;

public class Algoritmos 
{  
    //Metodo para generar puntos aleatorios
    public static Punto[] GenerarPuntosAleatorios(int n) 
    {
        Punto[] puntos = new Punto[n];
        for (int i = 0; i < n; i++) 
            puntos[i] = new Punto(Math.random()*3000, Math.random()*3000); //Valor aleatorio entre 0 y 3000
        return puntos;
    }
     
    //------------------- QUICKSORT -------------------------
    public static void ordenaQuick(Punto[] puntos) 
    {
        ordenaQuicksort(puntos, 0, puntos.length-1);
    }
    
    private static void ordenaQuicksort(Punto[] puntos, int inicio, int fin)
    {
        if(inicio < fin)
        {
            int mitad = Particion(puntos, inicio, fin);
            ordenaQuicksort(puntos, inicio, mitad);
            ordenaQuicksort(puntos, mitad+1, fin);
        }
    }
    
    private static int Particion(Punto[] puntos, int inicio, int fin)
    {
        Punto p = puntos[inicio];
        int i = inicio - 1;
        int j = fin + 1;
        
        for(;;)
        {
            while(p.getX() < puntos[--j].getX());
            while(puntos[++i].getX() < p.getX());
            if(i >= j) 
                return j;   
            Punto aux = new Punto(puntos[i].getX(), puntos[i].getY());
            puntos[i] = puntos[j];
            puntos[j] = aux;
        }
    }
    
    public static Trio DyV(Punto[] P, int inicio, int fin)
    {
        Trio minimo;
        double dmin;
        
        if(fin-inicio+1 < 5) //Caso base
            return Trio.exhaustivo(P, inicio, fin);
        
        int q = (inicio + fin)/2;
        Trio izq = DyV(P, inicio, q);
        Trio der = DyV(P, q+1, fin);
        
        if(izq.getDMin() < der.getDMin()) //Miramos si Trio izq es menor que Trio der
        {
            minimo = izq;
            dmin = izq.getDMin();
        }     
        else
        {
            minimo = der;
            dmin = der.getDMin();
        }
        
        int a1,a2;
        
        for(a1 = q; a1>=inicio; a1--) //Parte de la izquierda
            if(P[q+1].getX() - P[a1].getX() > dmin)
                break;
        
        for(a2 = q+1; a2<=fin; a2++) //Parte de la derecha
            if(P[a2].getX() - P[q].getX() > dmin)
                break;
        
        for(int a3 = a1+1; a3 <= q; a3++) //Dos puntos de la derch y 1 de la izq
            for(int a4 = q+1; a4 < a2; a4++)
                for(int a5 = a4+1; a5 < a2; a5++)
                {
                    Trio aux = new Trio (P[a3], P[a4], P[a5]);
                    if(aux.getDMin() < minimo.getDMin())
                        minimo = aux;
                }
        
        for(int a3 = a1+1; a3 <= q; a3++) //Dos puntos de la izq y 1 de la derch
            for(int a4 = a3+1; a4 <= q; a4++)
                for(int a5 = q+1; a5 < a2; a5++)
                {
                    Trio aux = new Trio (P[a3], P[a4], P[a5]);
                    if(aux.getDMin() < minimo.getDMin())
                        minimo = aux;
                }
        
        return minimo;
    }
    
    //---------------- PARTE 2 ----------------------
    
    //El algoritmo de Dijkstra necesita apoyarse en una matriz de adyacencia para poder calcular
    //el coste minimo entre cada punto, ya que esta tendra el coste de cada punto con el resto
    public static int[][] generarMatrizDeAdyacencia(Punto[] P) //Genera una matriz con todos los pesos de las aristas
    {
        int[][] matrizAd = new int[P.length][P.length]; //La matriz es de tamaño N*N siendo 'N' el numero de Puntos
        
        for(int i=0;i<P.length;i++)
        {
            for(int j=0;j<P.length;j++)
            {
               matrizAd[i][j] = Integer.MAX_VALUE/2;
            }
        }
        
        for(int i = 0; i<P.length; i++)
        {
            for(int j = i+1; j<P.length; j++)
            {
                double distancia=P[i].distancia(P[j]);
//                int peso=(int)((distancia*100)%100)+1;
                int peso = (int)distancia;
                matrizAd[i][j] = peso;   //Metemos en la posicion i, j el peso de la arista de los puntos i y j
                
                matrizAd[j][i] = matrizAd[i][j]; //Ya que es lo mismo la distancia del punto i al j que del j al i
            }
        }
        return matrizAd;
    }
            
    public static int[] algoritmoDijkstra(int[][] matrizAd, String[] caminoMin)
    {
        int[] solucion = new int[matrizAd.length]; //se guardan los pesos minimos de los diferentes puntos
        boolean[] seleccionados = new boolean[matrizAd.length];
        int posPmin = 0; //Posicion del punto con menor coste al punto origen
        
        //Inicializacion
        for(int i=0; i<matrizAd.length; i++)
        {
            caminoMin[i] = "0";
            seleccionados[i]=false;
            solucion[i] = matrizAd[0][i];
        }
        solucion[0] = 0; //La distancia del punto origen hacia si mismo es 0
        
        //Bucle voraz
        for(int i=0; i<matrizAd.length-1; i++)
        {
            //Buscamos el punto más cercano al punto origen y que todavia no este seleccionado
            int min = Integer.MAX_VALUE;
        
            for(int j=0; j<matrizAd.length; j++)
                if((!seleccionados[j]) && (solucion[j] < min))
                {
                    min = solucion[j];
                    posPmin = j;
                }
            
            //Una vez obtenemos ese punto lo dejamos como ya seleccionado y no lo usamos más
            seleccionados[posPmin]=true;
            
            //Actualizamos el vector de soluciones fijandonos en los adyecentes de posPmin
            for(int j=0; j<matrizAd.length; j++)
            {
                if((!seleccionados[j]) && (solucion[j] >= solucion[posPmin]+matrizAd[posPmin][j]))
                {
                    solucion[j] = solucion[posPmin]+matrizAd[posPmin][j];
                    caminoMin[j] = caminoMin[posPmin] + "->"+j;
                }
            }
        }
        return solucion;
    }
}