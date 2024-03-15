/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ismae
 */
public class SocioDAO 
{
    Session sesion = null;
    PreparedStatement ps = null;
    
    public SocioDAO(Session sesion) {this.sesion = sesion;}
    
    //Muestra toda la información de la tabla SOCIO
    public ArrayList<Socio> listaSocios() throws Exception
    {
        Transaction transaccion = sesion.beginTransaction();
        
        Query consulta = sesion.createNativeQuery("SELECT * FROM Socio S", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>)consulta.list();
       
        transaccion.commit();
        return socios;
    }
    
    //Metodo igual que el anterior pero filtrado por una letra
    public ArrayList<Socio> listaSocioPorLetra(String Letra) throws Exception
    {
        Transaction transaccion = sesion.beginTransaction();
        Letra += "%";
        Query consulta = sesion.createNativeQuery("SELECT * FROM Socio S"
                + " WHERE nombre LIKE :letra", Socio.class).setParameter("letra", Letra);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.list();
        
        transaccion.commit();
        return socios;
    }
    
    public boolean insertarSocio(Socio S) throws Exception
    {
        Transaction transaccion = sesion.beginTransaction();
        boolean insertado = false;
        try 
        {
            sesion.save(S);
            transaccion.commit();
            insertado = true;
        } 
        catch (Exception e) 
        {
            transaccion.rollback();
            JOptionPane.showMessageDialog(null, "Error al insertar el socio.\n\n" + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        return insertado;
    }
    
    public void actualizarSocio(Socio S) throws Exception
    {
       Socio socio = sesion.get(Socio.class, S.getNumerosocio());
       socio.setActividades(S.getActividades());
       socio.setCategoria(S.getCategoria());
       socio.setCorreo(S.getCorreo());
       socio.setDni(S.getDni());
       socio.setFechaentrada(S.getFechaentrada());
       socio.setFechanacimiento(S.getFechanacimiento());
       socio.setNombre(S.getNombre());
       socio.setNumerosocio(S.getNumerosocio());
       socio.setTelefono(S.getTelefono()); 
       
       Transaction transaccion = sesion.beginTransaction();
       sesion.save(socio);
       transaccion.commit();
    }
    
    public boolean eliminarSocio(String numSocio) throws Exception
    {
        Transaction transaccion = sesion.beginTransaction();
        boolean eliminado = false;
        try 
        {
            Socio socio = sesion.get(Socio.class, numSocio);
            sesion.delete(socio);
            transaccion.commit();
            eliminado = true;
        } 
        catch (Exception e) 
        {
            transaccion.rollback();
            JOptionPane.showMessageDialog(null, "Error al eliminar el socio.\n\n" + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        return eliminado;
    }
}
