/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plantilla_singleton;


public final class Mi_Singleton { //final para que no se pueda heredar
    
    //Objeto singleton
    private static final Mi_Singleton s = new Mi_Singleton();
    
    //datos del singleton
    private static int cantidad;
    
    private Mi_Singleton(){
        cantidad = 0;
        System.out.println("Soy el constructor y solo existo una vez");
    }
    
    //Getter del objeto singleton
    public static Mi_Singleton obtenerSingleton(){
        cantidad++;
        return s;
    }
    
    public static void veces_llamado(){
        System.out.println("Me has llamado " + cantidad + " veces ");
    }
}
