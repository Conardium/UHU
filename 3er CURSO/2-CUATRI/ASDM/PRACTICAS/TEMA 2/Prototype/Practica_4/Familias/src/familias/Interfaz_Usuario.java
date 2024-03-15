package familias;

/**
 *
 * @author Ismael Da Palma Fernandez
 */
public class Interfaz_Usuario {
    
    public void menuPrincipal(){
        System.out.println("======= UNIDAD DE FAMILIAS =======");
        System.out.println("   1.- Clonar el ultimo familiar");
        System.out.println("   2.- Añadir un nuevo familiar");
        System.out.println("   3.- Mostrar datos simplificados de los Familiares");
        System.out.println("   4.- Mostrar datos completos de los Familiares");
        System.out.println("   5.- Eliminar Familiar");
        System.out.println("   6.- Salir");
        System.out.println("   ------------");
        System.out.print("   Elige una opcion: ");
    }
    
    public void menuFamiliares(){
        System.out.println("======= LISTA DE FAMILIARES =======");
        System.out.println("   1.- Padre");
        System.out.println("   2.- Madre");
        System.out.println("   3.- Hijo");
        System.out.println("   4.- Hija");
        System.out.println("   ------------");
        System.out.print("   Elige una opcion: ");
    }
}
