package calendario_universal;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Adaptador_Calendario implements I_TodosLosCalendarios{

    private Calendario cl = new Calendario();
    private String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    
    @Override
    public void calcularFecha(int d, int m, int a, int formato) {
        
        String dia_adaptado = Integer.toString(d);
        String mes_adaptado = Integer.toString(m);
        
        switch (formato) {
            case 1: //Fecha larga(nom_dia, dd de mes de año)
                dia_adaptado = dias[(d-1)%7];
                mes_adaptado = meses[m-1];
                cl.mostarCalendarioDiaMes(dia_adaptado, d, mes_adaptado, a);
                break;
                
            case 2: //Fecha mediana (dd-nom_mes-yy)
                mes_adaptado = meses[m-1];
                cl.mostarCalendarioMes(d, mes_adaptado, a);
                break;
                
            default: //Fecha corta (dd/mm/yyyy)
                cl.mostarCalendario(d, m, a);
        }
    }
}
