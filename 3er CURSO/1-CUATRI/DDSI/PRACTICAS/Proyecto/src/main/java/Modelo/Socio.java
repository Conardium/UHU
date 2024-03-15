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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ismae
 */
@Entity
@Table(name = "SOCIO")
@NamedQueries({
    @NamedQuery(name = "Socio.findAll", query = "SELECT s FROM Socio s"),
    @NamedQuery(name = "Socio.findByNumerosocio", query = "SELECT s FROM Socio s WHERE s.numerosocio = :numerosocio"),
    @NamedQuery(name = "Socio.findByNombre", query = "SELECT s FROM Socio s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Socio.findByDni", query = "SELECT s FROM Socio s WHERE s.dni = :dni"),
    @NamedQuery(name = "Socio.findByFechanacimiento", query = "SELECT s FROM Socio s WHERE s.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Socio.findByTelefono", query = "SELECT s FROM Socio s WHERE s.telefono = :telefono"),
    @NamedQuery(name = "Socio.findByCorreo", query = "SELECT s FROM Socio s WHERE s.correo = :correo"),
    @NamedQuery(name = "Socio.findByFechaentrada", query = "SELECT s FROM Socio s WHERE s.fechaentrada = :fechaentrada"),
    @NamedQuery(name = "Socio.findByCategoria", query = "SELECT s FROM Socio s WHERE s.categoria = :categoria")})
public class Socio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMEROSOCIO")
    private String numerosocio;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DNI")
    private String dni;
    @Column(name = "FECHANACIMIENTO")
    private String fechanacimiento;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "FECHAENTRADA")
    private String fechaentrada;
    @Basic(optional = false)
    @Column(name = "CATEGORIA")
    private Character categoria;
    @ManyToMany(mappedBy = "socios")
    private Set<Actividad> actividades = new HashSet<Actividad>();

    public Socio() {
    }

    public Socio(String numerosocio) {
        this.numerosocio = numerosocio;
    }

    public Socio(String numerosocio, String nombre, String dni, Character categoria) {
        this.numerosocio = numerosocio;
        this.nombre = nombre;
        this.dni = dni;
        this.categoria = categoria;
    }
    
    //Añadimos manualmente el constructor con todos los parametros
    public Socio(String numerosocio, String nombre, String dni, String fechanacimiento,
    String telefono, String correo, String fechaentrada, Character categoria)
    {
        this.numerosocio = numerosocio;
        this.nombre = nombre;
        this.dni = dni;
        this.fechanacimiento = fechanacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaentrada = fechaentrada;
        this.categoria = categoria;
    }

    public String getNumerosocio() {
        return numerosocio;
    }

    public void setNumerosocio(String numerosocio) {
        this.numerosocio = numerosocio;
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

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
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

    public Character getCategoria() {
        return categoria;
    }

    public void setCategoria(Character categoria) {
        this.categoria = categoria;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerosocio != null ? numerosocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socio)) {
            return false;
        }
        Socio other = (Socio) object;
        if ((this.numerosocio == null && other.numerosocio != null) || (this.numerosocio != null && !this.numerosocio.equals(other.numerosocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Socio[ numerosocio=" + numerosocio + " ]";
    }
    
}
