package pizzaweb.ismael_da_palma_fernández;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Pizza_4Quesos extends Pizza{
    
    String IngredientesBase;
    String IngredientesPropios;
    String Ing1 = "";
    String Ing2 = "";
    String Ing3 = "";
    
    public Pizza_4Quesos(){
        IngredientesBase = "Tomate, Mozzarella y Orégano";
        IngredientesPropios = "Queso, Crema, Gorgonzola, Chedar y Edam";
    }
    
    @Override
    public void Tipo_Pizza(){
        System.out.println("\n--- Has elegido una Pizza 4 Quesos ---");
    }
    
    @Override
    public void añadir_Ingredientes_extras(String i1, String i2, String i3){
        Ing1 = i1;
        Ing2 = i2;
        Ing3 = i3;
    }
    
    @Override
    public String getIngredientesBase(){
        return IngredientesBase;
    }
    
    @Override
    public String getIngredientesPropios(){
        return IngredientesPropios;
    }
    
    @Override
    public void mostrarIngredientesExtras(){
        System.out.println("" + Ing1 + ", " + Ing2 + ", " + Ing3);
    }
}
