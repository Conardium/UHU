package calendario_universal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Adaptador_Reloj implements I_TodosLosRelojes{

    private Reloj rj = new Reloj();
    
    @Override
    public void calcularHora(int hora, int minutos, int segundos, int formato) {
        
        int AmPm = 0; //0 = no se aplica, 1 = AM, 2 = PM
        
        switch (formato) {
            case 1: //Hora larga (hh:mm:ss)
                break;
                
            case 2: //Hora mediana (hh:mm AM-PM)
                if(hora > 12){
                    hora -= 12;
                    AmPm = 2;
                }
                else
                    AmPm = 1;
                break;
                
            default: //Hora corta (hh:mm)
                segundos = -1; //No se tienen en cuenta los segundos
        }
        
        rj.mostrarHora(hora, minutos, segundos, AmPm);
    }
}
