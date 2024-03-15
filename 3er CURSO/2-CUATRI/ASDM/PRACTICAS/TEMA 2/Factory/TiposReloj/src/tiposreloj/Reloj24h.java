
package tiposreloj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reloj24h extends Reloj{
    
    public Reloj24h(){}
    
    @Override
    public void dame_hora(){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy kk:mm");
        String hora = dateFormat1.format(new Date());
        
        System.out.println("La hora actual 24H es: " + hora);
    }
}
