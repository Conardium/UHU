/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ismae
 */
public class Socio 
{
    String numeroSocio;
    String nombre;
    String dni;
    String fechaNacimiento;
    String telefono;
    String correo;
    String fechaEntrada;
    String categoria;

    public Socio() 
    {
        numeroSocio = null;
        nombre = null;
        dni = null;
        fechaNacimiento = null;
        telefono = null;
        correo = null;
        fechaEntrada = null;
        categoria = null;
    }
    
    public Socio(String numeroSocio, String nombre, String dni, 
            String fechaNacimiento, String telefono, String correo, 
            String fechaEntrada, String categoria) 
    {
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaEntrada = fechaEntrada;
        this.categoria = categoria;
    }
    
    //---- GETTERS ----
    public String getNumeroSocio() {return numeroSocio;}

    public String getNombre() {return nombre;}

    public String getDni() {return dni;}

    public String getFechaNacimiento() {return fechaNacimiento;}

    public String getTelefono() {return telefono;}

    public String getCorreo() {return correo;}

    public String getFechaEntrada() {return fechaEntrada;}

    public String getCategoria() {return categoria;}
    
    //---- SETTERS ----

    public void setNumeroSocio(String numeroSocio) {this.numeroSocio = numeroSocio;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setDni(String dni) {this.dni = dni;}

    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setCorreo(String correo) {this.correo = correo;}

    public void setFechaEntrada(String fechaEntrada) {this.fechaEntrada = fechaEntrada;}

    public void setCategoria(String categoria) {this.categoria = categoria;}   
}
