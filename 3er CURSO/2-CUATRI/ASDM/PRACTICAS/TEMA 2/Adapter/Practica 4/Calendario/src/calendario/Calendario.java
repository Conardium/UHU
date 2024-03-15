package calendario_universal;

/**
 *
 * @author ismae
 */
public class Calendario implements I_Calendario{

    @Override
    public void mostarCalendarioDiaMes(String dia, int d, String m, int a) {
        System.out.println(dia + ", " + d + " de " + m + " de " + a);
    }

    @Override
    public void mostarCalendarioMes(int d, String m, int a) {
        System.out.println((d<10 ? "0" + d : d) + "-" + m + "-" + a);
    }

    @Override
    public void mostarCalendario(int d, int m, int a) {
        System.out.println((d<10 ? "0" + d : d) + "/" + (m<10 ? "0" + m : m) + "/" + a);
    }
    
    
    
}
