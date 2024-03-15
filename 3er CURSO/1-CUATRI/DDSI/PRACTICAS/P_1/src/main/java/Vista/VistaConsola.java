/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class VistaConsola 
{
    public void vistaConsolaLogin(String texto)
    {
        System.out.println("***********************************");
        System.out.println(texto);
        System.out.println("***********************************");
    }
    
    public void vistaConsolaLogin(String texto, String error)
    {
        System.out.println("***********************************");
        System.out.println(texto);
        System.out.println(error);
        System.out.println("***********************************");
    }
    
    //----------- EJERCICIO 3 ---------------
    public void vistaMetadatos(DatabaseMetaData dbmd) throws SQLException
    {
        System.out.println();
        System.out.println("***********************************");
        System.out.println("-La version es: " + dbmd.getDatabaseProductVersion()); //Version de la Base de Datos
        System.out.println("-La URL es: " + dbmd.getURL()); //URL de la conexion
        System.out.println("-El nombre del driver es: " + dbmd.getDriverName()); //Nombre del driver
        System.out.println("-El nombre de usuario es: " + dbmd.getUserName()); //Nombre de usuario con el que ha conectado
        System.out.println("-Num. max. de caracc. del usuario: " + dbmd.getMaxUserNameLength()); //Numero maximo de caracteres para el nombre de usuario
        System.out.println("-Num. max de columnas en una tabla: " + dbmd.getMaxColumnsInTable()); //Numero maximo de columnas que se permiten en una tabla
        System.out.println("***********************************");
        System.out.println();
    }
}
