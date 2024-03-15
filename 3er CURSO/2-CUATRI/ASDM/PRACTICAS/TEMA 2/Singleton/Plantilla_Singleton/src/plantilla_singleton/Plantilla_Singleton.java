
package plantilla_singleton;


public class Plantilla_Singleton {

    public static void main(String[] args) {
        
        Mi_Singleton s1 = Mi_Singleton.obtenerSingleton();
        Mi_Singleton s2 = Mi_Singleton.obtenerSingleton();
        Mi_Singleton s3 = Mi_Singleton.obtenerSingleton();
        Mi_Singleton s4 = Mi_Singleton.obtenerSingleton();
        Mi_Singleton s5 = Mi_Singleton.obtenerSingleton();
        
        System.out.println("\n--He usado los objetos--\n");
        
        s3.veces_llamado();
        s5.veces_llamado();
        
        //Resumiendo: Independientemente de cuantos objetos esten creados 
        //siempre se trabaja sobre el mismo dato.
    }
    
}
