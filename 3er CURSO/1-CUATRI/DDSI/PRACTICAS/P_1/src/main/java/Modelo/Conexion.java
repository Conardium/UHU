/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author usuario
 */
public class Conexion 
{
    
    Connection conexion = null;
    
    public Conexion() throws SQLException //Establecer la conexion
    {
        String cadenaConexion = "jdbc:oracle:thin:@172.17.20.75:1521:rabida";
        String usuario = "DDSI_007";
        String password = "DDSI_007";
        
        conexion = DriverManager.getConnection(cadenaConexion,usuario,password);
    }
    
    public Connection getConexion()
    {
        return (conexion);
    }
    
    public void desconexion() throws SQLException //Cerrar la conexion
    {
        conexion.close();
    }
    
    //---------- EJERCICIO 3 ---------------
    public DatabaseMetaData informacionBD() throws SQLException
    {
        return conexion.getMetaData(); //Devuelve la metadata de la conexion
    }
}
