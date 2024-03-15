/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ismae
 */
@Entity
@Table(name = "MONITOR")
@NamedQueries({
    @NamedQuery(name = "Monitor.findAll", query = "SELECT m FROM Monitor m"),
    @NamedQuery(name = "Monitor.findByCodmonitor", query = "SELECT m FROM Monitor m WHERE m.codmonitor = :codmonitor"),
    @NamedQuery(name = "Monitor.findByNombre", query = "SELECT m FROM Monitor m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Monitor.findByDni", query = "SELECT m FROM Monitor m WHERE m.dni = :dni"),
    @NamedQuery(name = "Monitor.findByTelefono", query = "SELECT m FROM Monitor m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "Monitor.findByCorreo", query = "SELECT m FROM Monitor m WHERE m.correo = :correo"),
    @NamedQuery(name = "Monitor.findByFechaentrada", query = "SELECT m FROM Monitor m WHERE m.fechaentrada = :fechaentrada"),
    @NamedQuery(name = "Monitor.findByNick", query = "SELECT m FROM Monitor m WHERE m.nick = :nick")})
public class Monitor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODMONITOR")
    private String codmonitor;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DNI")
    private String dni;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "FECHAENTRADA")
    private String fechaentrada;
    @Column(name = "NICK")
    private String nick;
    @OneToMany(mappedBy = "monitorresponsable")
    private Set<Actividad> actividadesResponsable = new HashSet<Actividad>();

    public Monitor() {
    }

    public Monitor(String codmonitor) {
        this.codmonitor = codmonitor;
    }

    public Monitor(String codmonitor, String nombre, String dni) {
        this.codmonitor = codmonitor;
        this.nombre = nombre;
        this.dni = dni;
    }

    //Añadimos manualmente el constructor con todos los parametros
    public Monitor(String codmonitor, String nombre, String dni, String telefono,
    String correo, String fechaentrada, String nick)
    {
        this.codmonitor = codmonitor;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaentrada = fechaentrada;
        this.nick = nick;
    }
    
    public String getCodmonitor() {
        return codmonitor;
    }

    public void setCodmonitor(String codmonitor) {
        this.codmonitor = codmonitor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(String fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Set<Actividad> getActividadResponsable() {return actividadesResponsable;}

    public void setActividadResponsable(Set<Actividad> actividadResponsable) {this.actividadesResponsable = actividadResponsable;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmonitor != null ? codmonitor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.codmonitor == null && other.codmonitor != null) || (this.codmonitor != null && !this.codmonitor.equals(other.codmonitor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Monitor[ codmonitor=" + codmonitor + " ]";
    }
    
}
