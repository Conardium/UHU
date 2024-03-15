package familias;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public interface Familia_Prototype { 
    public void setNombre(String n);
    public String getNombre();
    
    public void setApellido(String a);
    public String getApellido();
    
    public void setTelefono(String tlf);
    public String getTelefono();
    
    public void setDireccion(String dir);
    public String getDireccion();
    
    public void setCodPostal(String cp);
    public String getCodPostal();
    
    public void setEmail(String em);
    public String getEmail();
    
    public void setEstudios(String est);
    public String getEstudios();
    
    public void setRol(String r);
    public String getRol();
    
    public Familia_Prototype clonar(); //Clona solamente los atributos comunes de un familiar
    
    public Familia_Prototype clonarTodo(); //Clona todos los atributos de un familiar (un clon exacto)
    
    public void mostrarDatos(); //Muestra por pantalla los datos del familiar en cuestion
}
