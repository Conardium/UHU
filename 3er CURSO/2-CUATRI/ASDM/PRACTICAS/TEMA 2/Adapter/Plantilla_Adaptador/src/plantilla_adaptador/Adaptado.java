package plantilla_adaptador;

public class Adaptado {
    private String nombre;

    public Adaptado(String nombre) {
        this.nombre = nombre;
    }
    
    public void setNombre(String n){
        nombre = n;
    }
    
    public void mostrarNombre(String forma){
        if(forma.equalsIgnoreCase("MAYUS"))
            System.out.println("EL NOMBRE ES: " + nombre.toUpperCase());
        else
            System.out.println("el nombre es: " + nombre.toLowerCase());
    }
    
}
