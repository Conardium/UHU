package calendario_universal;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Reloj implements I_Reloj{
    
    @Override
    public void mostrarHora(int h, int m, int s, int AmPm) {
        if(AmPm == 0){ //Hora larga o corta
            if (s < 0) 
                System.out.println("La hora es: " + (h<10 ? "0" + h : h) + ":" + (m<10 ? "0" + m : m)); //Hora corta
            else
                System.out.println("La hora es: " + (h<10 ? "0" + h : h) + ":" + (m<10 ? "0" + m : m) + ":" + (s<10 ? "0" + s : s)); //Hora larga
        }
        else{
            if(AmPm == 1)
                System.out.println("La hora es: " + (h<10 ? "0" + h : h) + ":" + (m<10 ? "0" + m : m) + " AM"); //Hora mediana con AM
            else
                System.out.println("La hora es: " + (h<10 ? "0" + h : h) + ":" + (m<10 ? "0" + m : m) + " PM"); //Hora mediana con PM
        }
    }
    
}
