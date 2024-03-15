/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class Monitor 
{
    String codMonitor;
    String nombre;
    String dni;
    String telefono;
    String correo;
    String fechaEntrada;
    String nick;
    
    //Constructor por defecto
    public Monitor()
    {
        codMonitor = null;
        nombre = null;
        dni = null;
        telefono = null;
        correo = null;
        fechaEntrada = null;
        nick = null;
    };
    
    //Constructor con parametros
    public Monitor(String codMonitor, String nombre, String dni,
       String telefono, String correo, String fechaEntrada, String nick)
    {
        this.codMonitor = codMonitor;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaEntrada = fechaEntrada;
        this.nick = nick;
    }

    //---- GETTERS ----
    public String getCodMonitor() {return codMonitor;}

    public String getNombre() {return nombre;}

    public String getDni() {return dni;}

    public String getTelefono() {return telefono;}

    public String getCorreo() {return correo;}

    public String getFechaEntrada() {return fechaEntrada;}

    public String getNick() {return nick;}
    
    
    //---- SETTERS ----
    public void setCodMonitor(String codMonitor) {this.codMonitor = codMonitor;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setDni(String dni) {this.dni = dni;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setCorreo(String correo) {this.correo = correo;}

    public void setFechaEntrada(String fechaEntrada) {this.fechaEntrada = fechaEntrada;}

    public void setNick(String nick) {this.nick = nick;}
}
