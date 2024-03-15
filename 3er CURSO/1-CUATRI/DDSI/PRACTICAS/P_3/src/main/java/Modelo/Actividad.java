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
public class Actividad 
{
    String idActividad;
    String nombre;
    String descripcion;
    String precioBaseMes;

    public Actividad() 
    {
        idActividad = null;
        nombre = null;
        descripcion = null;
        precioBaseMes = null;
    }

    public Actividad(String idActividad, String nombre, String descripcion, String precioBaseMes) 
    {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBaseMes = precioBaseMes;
    }
    
    //---- GETTERS ----
    public String getIdActividad() {return idActividad;}

    public String getNombre() {return nombre;}

    public String getDescripcion() {return descripcion;}

    public String getPrecioBaseMes() {return precioBaseMes;}
    
    
    //---- SETTERS ----
    public void setIdActividad(String idActividad) {this.idActividad = idActividad;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public void setPrecioBaseMes(String precioBaseMes) {this.precioBaseMes = precioBaseMes;}
}
