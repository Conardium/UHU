package familias;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Hija implements Familia_Prototype{
    //Atributos unicos de la familia
    private String apellido;
    private String telefono; //El telefono será fijo
    private String direccion;
    private String codPostal;  
    private String rol;
    
    //Atributos que pueden cambiar
    private String nombre;
    private String correo;
    private String estudios;

    public Hija() {
        rol = "Hija";
    }
    
    public Hija(String nom, String ap, String tlf, String dir, String cp, String email, String est) {
        nombre = nom;
        apellido = ap;
        telefono = tlf;
        direccion = dir;
        codPostal = cp;
        correo = email;
        estudios = est;
        rol = "Hija";
    }
       
    @Override
    public void setNombre(String n) {
        nombre = n;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setApellido(String a) {
        apellido = a;
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    @Override
    public void setTelefono(String tlf) {
        telefono = tlf;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public void setDireccion(String dir) {
        direccion = dir;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setCodPostal(String cp) {
        codPostal = cp;
    }

    @Override
    public String getCodPostal() {
        return codPostal;
    }

    @Override
    public void setEmail(String em) {
        correo = em;
    }

    @Override
    public String getEmail() {
        return correo;
    }

    @Override
    public void setEstudios(String est) {
        estudios = est;
    }

    @Override
    public String getEstudios() {
        return estudios;
    }

    @Override
    public void setRol(String r) {
        rol = r;
    }

    @Override
    public String getRol() {
        return rol;
    }
    
    @Override
    public Familia_Prototype clonar() {
        Familia_Prototype f = new Hija();
        
        //Atributos que se mantienen, los otros son propios
        f.setApellido(apellido);
        f.setTelefono(telefono);
        f.setDireccion(direccion);
        f.setCodPostal(codPostal);
        
        return f;
    }
    
    @Override
    public Familia_Prototype clonarTodo() {
        Familia_Prototype f = new Hija();
        
        f.setNombre(nombre);
        f.setApellido(apellido);
        f.setTelefono(telefono);
        f.setDireccion(direccion);
        f.setCodPostal(codPostal);
        f.setEmail(correo);
        f.setEstudios(estudios);
        f.setRol(rol);
        
        return f;
    }
    
    public void mostrarDatos(){
        System.out.println("========= Datos del familiar =========");
        System.out.println("      --> Rol: " + rol + " <--");
        System.out.println("       Nombre: " + nombre);
        System.out.println("     Apellido: " + apellido);
        System.out.println("     Telefono: " + telefono);
        System.out.println("    Direccion: " + direccion);
        System.out.println("Codigo Postal: " + codPostal);
        System.out.println("        Email: " + correo);
        System.out.println("     Estudios: " + estudios);
    }
}
