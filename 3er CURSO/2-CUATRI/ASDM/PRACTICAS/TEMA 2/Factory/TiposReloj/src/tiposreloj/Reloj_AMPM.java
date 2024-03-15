
package tiposreloj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reloj_AMPM extends Reloj{
    
    public Reloj_AMPM(){}
    
    @Override
    public void dame_hora(){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String hora = dateFormat1.format(new Date());
        
        System.out.println("La hora actual AM-PM es: " + hora);
    }
}
