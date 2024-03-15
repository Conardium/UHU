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

public class MonitorDAO 
{
    Conexion conexion = null;
    PreparedStatement ps = null;
    
    public MonitorDAO(Conexion c) {this.conexion = c;}
    
    //Muestra toda la información de la tabla MONITOR
    public ArrayList<Monitor> listaMonitores() throws SQLException
    {
        ArrayList listaMonitores = new ArrayList();
        
        String consulta = "SELECT * FROM MONITOR";
        ps = conexion.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) //Mientras el puntero de la tabla no llegue al final...
        {
            //Creamos un objeto Monitor en el que guardamos los valores de la tabla
            //MONITOR y los añadimos al arraylist listaMonitores.
            Monitor monitor = new Monitor(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5), 
                    rs.getString(6), rs.getString(7));
            
            //Añadimos el monitor al que apuntaba el puntero a la lista y nos vamos a la siguiente fila
            listaMonitores.add(monitor);
        }
        return listaMonitores;
    }
    
    //Metodo igual que el anterior pero filtrado por una letra
    public ArrayList<Monitor> listaMonitorPorLetra(String Letra) throws SQLException
    {
        ArrayList listaMonitores = new ArrayList();
        
        String consulta = "SELECT * FROM MONITOR WHERE nombre LIKE ?";
        ps = conexion.getConexion().prepareStatement(consulta);
        Letra = Letra + "%";
        ps.setString(1, Letra);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Monitor monitor = new Monitor(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5), 
                    rs.getString(6), rs.getString(7));
            listaMonitores.add(monitor);
        }        
        return listaMonitores;
    }
    
    public void insertarMonitor(Monitor M) throws SQLException
    {
        String consulta = "INSERT INTO MONITOR VALUES ('" + M.codMonitor + "', '" +
                M.nombre + "', '" + M.dni + "', '" + M.telefono + "', '" + M.correo +
                "', '" + M.fechaEntrada + "', '" + M.nick + "')";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
    
    public void actualizarMonitor(Monitor M) throws SQLException
    {
        String consulta = "UPDATE MONITOR SET codMonitor= '" + M.codMonitor
                + "', nombre ='" + M.nombre + "', dni= '" +M.dni + "', telefono= '"
                + M.telefono + "', correo= '" + M.correo + "', fechaEntrada= '" + M.fechaEntrada
                + "', nick= '" + M.nick + "'WHERE codMonitor= '" + M.codMonitor + "'";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
    
    public void eliminarMonitor(String codigo) throws SQLException
    {
        String consulta = "DELETE FROM MONITOR WHERE codMonitor = '" + codigo + "'";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
    
    //Practia 4
    public int NumeroDeActividadesResponsable(String codigo) throws  SQLException //Devuelve el numero de actividades de la que es responsable el
    {                                                                             //monitor pasado por parametro.
        int numActividades = 0;
        
        String consulta = "SELECT count(monitorresponsable) FROM ACTIVIDAD WHERE monitorresponsable = '" + codigo + "'";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            numActividades = rs.getInt(1); //Coge el valor obtenido del ResultSet
        
        return numActividades;
    }
}
