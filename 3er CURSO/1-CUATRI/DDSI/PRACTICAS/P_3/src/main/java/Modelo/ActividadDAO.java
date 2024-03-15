/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author ismae
 */
public class ActividadDAO 
{
    private Conexion conexion;
    PreparedStatement ps = null;

    public ActividadDAO(Conexion conexion) 
    {
        this.conexion = conexion;
    }
    
    
    public ResultSet listaDeSociosPorActividad(String idActividad) throws SQLException
    {   
        CallableStatement stmt = null; //Objeto para realizar la llamada al procedimiento almacenado
        ResultSet rs = null;
        String llamadaP = "{ call PACTIVIDADSOCIOS(?,?) }"; //Tiene 2 parametros: 1 de salida y 1 de entrada
        
        stmt = conexion.getConexion().prepareCall(llamadaP);
        stmt.setString(1, idActividad);
        stmt.registerOutParameter(2, OracleTypes.CURSOR);
        
        stmt.executeUpdate();
        
        rs = (ResultSet)stmt.getObject(2);
        
        return rs;
    }
    
    public void actualizarMonitorResponsableAGenerico(String codigo) throws SQLException
    {
        String consulta = "UPDATE ACTIVIDAD SET monitorresponsable= 'M999' WHERE monitorresponsable= '" + codigo + "'";
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
}
