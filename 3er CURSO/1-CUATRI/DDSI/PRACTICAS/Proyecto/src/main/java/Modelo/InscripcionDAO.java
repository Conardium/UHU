/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.HibernateUtil;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ismae
 */
public class InscripcionDAO 
{
    Session sesion = null;

    public InscripcionDAO(Session sesion) 
    {
        this.sesion = sesion;
    }
    
    public List<Socio> listaDeInscripciones() throws Exception
    {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = sesion.beginTransaction();
        
        Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO S", Socio.class);
        List<Socio> socios = consulta.list();
        
        transaction.commit();
        return socios;
    }
    
    public void darDeAlta(String numSocio, String idAct) throws Exception
    {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        
        Socio socio = sesion.get(Socio.class, numSocio);
        Actividad actividad = sesion.get(Actividad.class, idAct);
        
        if(actividad == null || socio == null)
        {
            JOptionPane.showMessageDialog(null, "La actividad o socio no existe en la BD.", "Error",JOptionPane.ERROR_MESSAGE);
            transaccion.rollback();
        }
                  
        else
        {
            actividad.addSocio(socio);       
            sesion.save(actividad);
            transaccion.commit();
        }
        
    }
    
    public void darDeBaja(String numSocio, String idAct) throws Exception
    {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        
        Socio socio = sesion.get(Socio.class, numSocio);
        Actividad actividad = sesion.get(Actividad.class, idAct);
        
        if(actividad == null ||socio == null)
        {
            JOptionPane.showMessageDialog(null, "La actividad o socio no existe en la BD.", "Error",JOptionPane.ERROR_MESSAGE);
            transaccion.rollback();
        }
        else
        {
            actividad.eliminaSocio(socio);      
            sesion.save(actividad);
            transaccion.commit();
        }  
    }
}
