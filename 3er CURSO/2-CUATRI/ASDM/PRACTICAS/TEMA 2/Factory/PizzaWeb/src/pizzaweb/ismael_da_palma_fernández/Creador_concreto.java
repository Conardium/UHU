package pizzaweb.ismael_da_palma_fernández;

/**
 *
 * @author ismae
 */
public class Creador_concreto extends Creador{
    
    private int tipo;
    
    public Creador_concreto(int t){
        tipo = t;
    }
    
    @Override
    public Pizza Factory_method(){    
        
        if(tipo == 1)
            return new Pizza_Peperoni();
        
        else if(tipo == 2)
            return new Pizza_4Quesos();
        
        else if(tipo == 3)
            return new Pizza_Barbacoa();
        
        else
            return new Pizza_Marinera();
    }
}
