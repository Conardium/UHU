/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.HibernateUtil;
import java.util.ArrayList;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ismae
 */
public class ActividadDAO 
{
    Session sesion = null;

    public ActividadDAO(Session sesion) 
    {
        this.sesion = sesion;
    }
    
    
    public ArrayList<Object[]> listaDeSociosPorActividad(String idActividad) throws Exception //Esta vez no se usa un procedimiento almacenado
    {   
        Transaction transaccion = sesion.beginTransaction();
        Query consulta = sesion.createNativeQuery("SELECT DISTINCT S.nombre, S.correo FROM SOCIO S INNER JOIN REALIZA R ON S.numeroSocio=R.numeroSocio"
                + " WHERE R.idactividad = :idA").setParameter("idA", idActividad);
        
        ArrayList<Object[]> socios = (ArrayList<Object[]>)consulta.list();
        
        transaccion.commit();
        return socios;
    }
    
    public void actualizarMonitorResponsableAGenerico(String codigo) throws Exception
    {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion = sesion.beginTransaction();
        
        Monitor monitor = sesion.get(Monitor.class, codigo);
        Monitor mGen = sesion.get(Monitor.class, "M999");
        
        if(monitor == null)
            System.out.println("\n\nEl monitor no existe en la BD\n");
        else
        {
            Set<Actividad> actividades = monitor.getActividadResponsable();
            for(Actividad actividad : actividades)
            {
                actividad.setMonitorresponsable(mGen);
                sesion.save(actividad);
            }
        }
        transaccion.commit();
    }
}
