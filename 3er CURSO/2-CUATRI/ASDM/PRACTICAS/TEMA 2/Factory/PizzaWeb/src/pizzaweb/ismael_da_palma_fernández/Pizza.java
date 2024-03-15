package pizzaweb.ismael_da_palma_fernández;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public abstract class Pizza {
    
    public void Tipo_Pizza(){
        
    }
    
    public void añadir_Ingredientes_extras(String i1, String i2, String i3){}
    
    public String getIngredientesBase(){return null;}
    
    public String getIngredientesPropios(){return null;}
    
    public void mostrarIngredientesExtras(){}
}
