package plantilla_iterator;

public class Plantilla_Iterator {

    public static void main(String[] args) {
        
        I_Agregado ln = new Agregado_Concreto();
        I_ListaPalabras lp = new ListaPalabras();
        
        System.out.println("Introduzco datos... ");
        for (int i = 0; i < 10; i++) 
            ln.Agregar(i);
        
        System.out.println("Introduzco las palabras...");
        lp.Agregar("cero");
        lp.Agregar("uno");
        lp.Agregar("dos");
        lp.Agregar("tres");
        lp.Agregar("cuatro");
        lp.Agregar("cinco");
        lp.Agregar("seis");
        lp.Agregar("siete");
        lp.Agregar("ocho");
        lp.Agregar("nueve");
        
        //Iterador concreto 1
        System.out.println("Mostramos los datos en orden...");
        I_Iterador iterador = ln.crearIterador1();      
        while (iterador.tieneSiguiente()) {
            System.out.println(iterador.Siguiente());
        }
        
        //Iterador concreto 2
        System.out.println("\nMostramos los datos al reves...");
        I_Iterador iterador2 = ln.crearIterador2();
        while (iterador2.tieneSiguiente()) {
            System.out.println(iterador2.Siguiente());
        }
        
        //IteradorListarPalabras
        System.out.println("\nMostramos los datos de las palabras...");
        I_Iterador iterador3 = lp.CrearIterador();
        while (iterador3.tieneSiguiente()) {
            System.out.println(iterador3.Siguiente());
        }
    }
    
}
