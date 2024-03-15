package calendario_universal;

import java.util.Scanner;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Calendario_Universal {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        I_TodosLosRelojes iRelojes = new Adaptador_Reloj();
        I_TodosLosCalendarios iCalendarios = new Adaptador_Calendario();
        
        int opcion = 0;
        int opcion_Hora = 0;
        int opcion_Fecha = 0;
        int hora = 0, min = 0, seg = 0;
        int dia = 0, mes = 0, año = 0;
        
        do {            
            menuPrincipal();
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    do {
                        System.out.print("\nIndica hora: ");
                        hora = sc.nextInt();
                        System.out.print("Indica minutos: ");
                        min = sc.nextInt();
                        System.out.print("Indica segundos: ");
                        seg = sc.nextInt();

                        if (hora < 0 || hora > 23 || min < 0 || min > 60 || seg < 0 || seg > 60) {
                            System.out.println("Error al ingresar los datos. Intentalo de nuevo");
                        }

                    } while (hora < 0 || hora > 23 || min < 0 || min > 60 || seg < 0 || seg > 60);

                    menuHora();
                    System.out.print("Indica en que formato quieres la hora: ");
                    opcion_Hora = sc.nextInt();
                    iRelojes.calcularHora(hora, min, seg, opcion_Hora);
                    espera();
                    break;
                    
                case 2:
                    do {
                        System.out.print("\nIndica dia: ");
                        dia = sc.nextInt();
                        System.out.print("Indica mes: ");
                        mes = sc.nextInt();
                        System.out.print("Indica año: ");
                        año = sc.nextInt();

                        if (dia < 0 || dia > 31 || mes < 0 || mes > 12 || año < 1000 || año > 9999) {
                            System.out.println("\n<<Error al ingresar los datos. Intentalo de nuevo>>\n");
                        }

                    } while (dia < 0 || hora > 31 || mes < 0 || mes > 12 || año < 1000 || año > 9999);

                    menuCalendario();
                    System.out.print("Indica en que formato quieres la fecha: ");
                    opcion_Fecha = sc.nextInt();
                    iCalendarios.calcularFecha(dia, mes, año, opcion_Fecha);
                    espera();
                    break;
                    
                case 3:
                    break;
                default:
                    System.out.println("\n<< Selecciona una opcion correcta >>\n");
            }
        } while (opcion != 3);        
    }
    
    public static void menuPrincipal(){
        System.out.println("======= Calendario Universal =======");
        System.out.println("1.-Indicar una hora");
        System.out.println("2.-Indicar una fecha");
        System.out.println("3.-Salir");
        System.out.println("--------------");
    }
    
    public static void menuHora(){
        System.out.println("\n1.- Hora larga (hh:mm:ss)");
        System.out.println("2.- Hora mediana (hh:mm AM/PM)");
        System.out.println("3.- Hora corta (hh:mm)");
        System.out.println("------------------");
    }
    
    public static void menuCalendario(){
        System.out.println("\n1.- Fecha larga (nom_dia, dd de mes de año)");
        System.out.println("2.- Fecha mediana (dd-mes-yy)");
        System.out.println("3.- Fecha corta (dd/mm/yyyy)");
        System.out.println("------------------");
    }
    
    public static void espera(){
        Scanner sc = new Scanner(System.in);
        int espera = -1;
        do {            
            System.out.println("\nPon 0 para continuar...");
            espera= sc.nextInt();
        } while (espera != 0);
    }
}
