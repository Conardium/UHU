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

/**
 *
 * @author ismae
 */
public class SocioDAO 
{
    Conexion conexion = null;
    PreparedStatement ps = null;
    
    public SocioDAO(Conexion c) {this.conexion = c;}
    
    //Muestra toda la información de la tabla SOCIO
    public ArrayList<Socio> listaSocios() throws SQLException
    {
        ArrayList listaSocios = new ArrayList();
        
        String consulta = "SELECT * FROM SOCIO";
        ps = conexion.getConexion().prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) //Mientras el puntero de la tabla no llegue al final...
        {
            //Creamos un objeto Socio en el que guardamos los valores de la tabla
            //SOCIO y los añadimos al arraylist listaSocios.
            Socio socio = new Socio(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5), 
                    rs.getString(6), rs.getString(7), rs.getString(8));
            
            //Añadimos el socio al que apuntaba el puntero a la lista y nos vamos a la siguiente fila
            listaSocios.add(socio);
        }
        return listaSocios;
    }
    
    //Metodo igual que el anterior pero filtrado por una letra
    public ArrayList<Socio> listaSocioPorLetra(String Letra) throws SQLException
    {
        ArrayList listaSocios = new ArrayList();
        
        String consulta = "SELECT * FROM SOCIO WHERE nombre LIKE ?";
        ps = conexion.getConexion().prepareStatement(consulta);
        Letra = Letra + "%";
        ps.setString(1, Letra);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Socio socio = new Socio(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5), 
                    rs.getString(6), rs.getString(7), rs.getString(8));
            listaSocios.add(socio);
        }        
        return listaSocios;
    }
    
    public void insertarSocio(Socio S) throws SQLException
    {
        String consulta = "INSERT INTO SOCIO VALUES ('" + S.numeroSocio + "', '" +
                S.nombre + "', '" + S.dni + "', '" + S.fechaNacimiento + "', '" + S.telefono + "', '" + S.correo +
                "', '" + S.fechaEntrada + "', '" + S.categoria + "')";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
    
    public void actualizarSocio(Socio S) throws SQLException
    {
        String consulta = "UPDATE SOCIO SET numeroSocio= '" + S.numeroSocio
                + "', nombre ='" + S.nombre + "', dni= '" +S.dni + "', fechaNacimiento= '" + S.fechaNacimiento + "', telefono= '"
                + S.telefono + "', correo= '" + S.correo + "', fechaEntrada= '" + S.fechaEntrada
                + "', categoria= '" + S.categoria + "'WHERE numeroSocio= '" + S.numeroSocio + "'";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
    
    public void eliminarSocio(String numSocio) throws SQLException
    {
        String consulta = "DELETE FROM SOCIO WHERE numeroSocio = '" + numSocio + "'";
        
        ps = conexion.getConexion().prepareStatement(consulta);
        ps.executeUpdate();
    }
}
