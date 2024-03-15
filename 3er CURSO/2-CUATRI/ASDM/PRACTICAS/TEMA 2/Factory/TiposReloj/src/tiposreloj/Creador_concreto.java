
package tiposreloj;

public class Creador_concreto extends Creador{
    
    private String tipo;
    
    public Creador_concreto(String t){
        tipo = t;
    }
    
    @Override
    public Reloj Factory_method(){    
        
        if(tipo.equalsIgnoreCase("24"))
            return new Reloj24h();
        else
            return new Reloj_AMPM();
    }
}
