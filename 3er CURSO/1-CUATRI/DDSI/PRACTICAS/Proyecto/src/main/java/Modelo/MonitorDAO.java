/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MonitorDAO 
{
    Session sesion = null;
    PreparedStatement ps = null;
    
    public MonitorDAO(Session sesion) {this.sesion = sesion;}
    
    //Muestra toda la información de la tabla MONITOR
    public ArrayList<Monitor> listaMonitores() throws Exception
    {
        Transaction transaccion = sesion.beginTransaction();
        
        Query consulta = sesion.createNativeQuery("SELECT * FROM Monitor M", Monitor.class);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>)consulta.list();
       
        transaccion.commit();
        return monitores;
    }
    
    //Metodo igual que el anterior pero filtrado por una letra
    public ArrayList<Monitor> listaMonitorPorLetra(String Letra) throws Exception
    {
        Transaction transaccion = sesion.beginTransaction();
        Letra += "%";
        Query consulta = sesion.createNativeQuery("SELECT * FROM Monitor M"
                + " WHERE nombre LIKE :letra", Monitor.class).setParameter("letra", Letra);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>) consulta.list();
        
        transaccion.commit();
        return monitores;
    }
    
    public boolean insertarMonitor(Monitor M)
    {
       Transaction transaccion = sesion.beginTransaction();
       boolean insertado = false;
       try
       {
            sesion.save(M);
            transaccion.commit();
            insertado = true;
       }
       catch(Exception e)
       {
           transaccion.rollback();
           JOptionPane.showMessageDialog(null,"Error al insertar el monitor.\n\n" + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
       }
       return insertado;
    }
    
    public void actualizarMonitor(Monitor M) throws Exception
    {
       Monitor monitor = sesion.get(Monitor.class, M.getCodmonitor());
       monitor.setActividadResponsable(M.getActividadResponsable());
       monitor.setCodmonitor(M.getCodmonitor());
       monitor.setCorreo(M.getCorreo());
       monitor.setDni(M.getDni());
       monitor.setFechaentrada(M.getFechaentrada());
       monitor.setNick(M.getNick());
       monitor.setNombre(M.getNombre());
       monitor.setTelefono(M.getTelefono());
        
       Transaction transaccion = sesion.beginTransaction();
       sesion.save(monitor);
       transaccion.commit();
    }
    
    public boolean eliminarMonitor(String codigo)
    {
        Transaction transaccion = sesion.beginTransaction();
        boolean eliminado = false;
        try 
        {
            Monitor monitor = sesion.get(Monitor.class, codigo);
            sesion.delete(monitor);
            transaccion.commit(); 
            eliminado = true;
        } 
        catch (Exception e) 
        {
            transaccion.rollback();
            JOptionPane.showMessageDialog(null, "-- Transaccion cancelada --\n\n" + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        return eliminado;
    }
    
    public boolean existeMonitor(String codigo)
    {
        Query consulta = sesion.createNativeQuery("SELECT codMonitor FROM MONITOR WHERE codMonitor = :cod").setParameter("cod",codigo);
        List resultado = consulta.list();
        if(resultado.isEmpty())
            return false;
        else
            return true;
    }
    
    
    //Devuelve el numero de actividades de la que es responsable el monitor pasado por parametro.
    public ArrayList<String> NumeroDeActividadesResponsable(String codigo) throws  Exception 
    {                                                                              
        Query consulta = sesion.createNativeQuery("SELECT count(monitorresponsable) FROM ACTIVIDAD WHERE monitorresponsable = :mR").setParameter("mR", codigo);
        
        ArrayList<String> numero = (ArrayList<String>)consulta.list();
        
        return numero;
    }
}
