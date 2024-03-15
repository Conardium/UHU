/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaConsola;
import Vista.VistaLogin;
import Vista.VistaMensajes;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author usuario
 */
public class ControladorLogin implements ActionListener
{

    private Conexion conexion = null;
    private boolean conexionOK;
    private boolean desconexionOK;
    private boolean metadatosOK;
    private VistaConsola vConsola = null;
    private VistaLogin vLogin = null;
    private VistaPrincipal vConectado = null;

    public ControladorLogin() throws SQLException 
    {
        vLogin = new VistaLogin();
        vConsola = new VistaConsola();
        vConectado = new VistaPrincipal();
        
        addListeners();
        
        //Para mostrar la interfaz jframe
        vLogin.setLocationRelativeTo(null);
        vLogin.setVisible(true);
        
        vLogin.Servidor.setSelectedIndex(0);
        vLogin.IP.setText("172.17.20.75");
        vLogin.ServidorBD.setText("rabida");
        vLogin.Usuario.setText("DDSI_007");
        vLogin.Password.setText("DDSI_007");
    }

    public void addListeners() //Para gestionar los eventos
    {
        vLogin.jButtonConectar.addActionListener(this);
        vLogin.jButtonSalir.addActionListener(this);
        vConectado.jButtonCerrar.addActionListener(this);
    }
    
    @Override //Sobreescribir
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "ConectarDialogoConexion":
                conexionOK=conectar();
                if(conexionOK)
                {
                    vLogin.dispose();
                    
                    //Llamamos al controlador del jFrame VistaPrincipal
                    Controlador controlador = new Controlador(conexion);
                    VistaMensajes M = new VistaMensajes(conexionOK);
                }
                else
                {
                    VistaMensajes M = new VistaMensajes(conexionOK);
                }
                break;
                
            case "SalirDialogoConexion":
                vLogin.dispose(); //Cierra la ventana jframe
                System.exit(0);
                break;
        }
    }
    
    public boolean conectar()
    {
        boolean resultado = false;
        try
        {
            String server = (String) (vLogin.Servidor.getSelectedItem());
            String ip = vLogin.IP.getText();
            String service_bd = vLogin.ServidorBD.getText();
            String usuario = vLogin.Usuario.getText();
            String password = new String (vLogin.Password.getPassword());
            
            conexion= new Conexion(server, ip, service_bd, usuario, password);
            
            resultado = true;
            vConsola.vistaConsolaLogin("Conexion Correcta. Enhorabuena");
            metadatosOK = MetaDatos();
            if(metadatosOK)
                desconectar();
            
        } catch(SQLException ex){
            vConsola.vistaConsolaLogin("Error en la conexion", ex.getMessage());
        }
        return resultado;
    }
    
   public boolean desconectar()
   {
        boolean resultado = false;
        try
        {
            conexion.desconexion();
            
            vConsola.vistaConsolaLogin("Desconexion correcta. ¡¡Adios!!");
            resultado = true;
        } catch(SQLException ex){
            vConsola.vistaConsolaLogin("Error al desconectar", ex.getMessage());
        }
        return resultado;
   }
   
   //----------- EJERCICIO 3_Practica 1---------------
   public boolean MetaDatos() throws SQLException
   {
       boolean resultado = false;
       
       try
       {
           DatabaseMetaData data = conexion.informacionBD();
           
           vConsola.vistaMetadatos(data);
           resultado = true;
       } catch(SQLException ex) {
           vConsola.vistaConsolaLogin("Error al conseguir la Metadata", ex.getMessage());
       }
       
       return resultado;
   }
    
}
