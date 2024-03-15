/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaConsola;
import java.sql.*;

/**
 *
 * @author usuario
 */
public class ControladorLogin 
{

    private Conexion conexion = null;
    private boolean conexionOK;
    private boolean desconexionOK;
    private boolean metadatosOK;
    private VistaConsola vista = new VistaConsola();

    public ControladorLogin() throws SQLException 
    {
        conexionOK = conectar();
        metadatosOK = MetaDatos();
        desconexionOK = desconectar();
    }

    public boolean conectar()
    {
        boolean resultado = false;
        try
        {
            conexion = new Conexion();
            
            vista.vistaConsolaLogin("Conexion correcta. ¡¡Enhorabuena!!");
            resultado = true;
        } catch(SQLException ex){
            vista.vistaConsolaLogin("Error en la conexion", ex.getMessage());
        }
        return resultado;
    }
    
   public boolean desconectar()
   {
        boolean resultado = false;
        try
        {
            conexion.desconexion();
            
            vista.vistaConsolaLogin("Desconexion correcta. ¡¡Adios!!");
            resultado = true;
        } catch(SQLException ex){
            vista.vistaConsolaLogin("Error al desconectar", ex.getMessage());
        }
        return resultado;
   }
   
   //----------- EJERCICIO 3---------------
   public boolean MetaDatos() throws SQLException
   {
       boolean resultado = false;
       
       try
       {
           DatabaseMetaData data = conexion.informacionBD();
           
           vista.vistaMetadatos(data);
           resultado = true;
       } catch(SQLException ex) {
           vista.vistaConsolaLogin("Error al conseguir la Metadata", ex.getMessage());
       }
       
       return resultado;
   }
    
}
