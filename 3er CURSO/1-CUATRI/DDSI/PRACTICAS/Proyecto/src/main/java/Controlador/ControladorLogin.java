/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VistaConsola;
import Vista.VistaLogin;
import Vista.VistaMensajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import org.hibernate.Session;


/**
 *
 * @author usuario
 */
public class ControladorLogin implements ActionListener
{

    private Session sesion = null;
    private VistaMensajes vMensajes = null;
    private VistaConsola vConsola = null;
    private VistaLogin vLogin = null;

    public ControladorLogin() throws SQLException 
    {
        vLogin = new VistaLogin();
        vConsola = new VistaConsola();
        vMensajes = new VistaMensajes();
        
        addListeners();
        
        //Para mostrar la interfaz jframe
        vLogin.setLocationRelativeTo(null);
        vLogin.setVisible(true);
        
        vLogin.Servidor.setSelectedIndex(0);
        vLogin.IP.setText("172.17.20.39");
        vLogin.ServidorBD.setText("etsi");
        vLogin.Usuario.setText("DDSI_007");
        vLogin.Password.setText("DDSI_007");
    }

    public void addListeners() //Para gestionar los eventos
    {
        vLogin.jButtonConectar.addActionListener(this);
        vLogin.jButtonSalir.addActionListener(this);
    }
    
    @Override //Sobreescribir
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "ConectarDialogoConexion":
                sesion=conectar();
                if(sesion != null)
                {
                    vMensajes.mensajes("info", "Conexión correcta con Hibernate");
                    vLogin.dispose();
                    //Llamamos al controlador del jFrame VistaPrincipal
                    Controlador controlador = new Controlador(sesion);
                }
                else
                    vMensajes.mensajes("error","Error en la conexion con Hibernate. Revise los valores de conexion");
                break;
                
            case "SalirDialogoConexion":
                vLogin.dispose(); //Cierra la ventana jframe
                System.exit(0);
                break;
        }
    }
    
    
    
    //Si se quiere conectar a la BD de la ETSI, cambiar los siguientes valores (tanto aquí como en el file "hibernate.cfg.xml"):
    //ip = 172.17.20.39    y   service_bd = etsi
    
    //Si se quiere conectar a la BD de la RABIDA, cambiar los siguientes valores (tanto aquí como en el file "hibernate.cfg.xml"):
    //ip = 172.17.20.75    y   service_bd = rabida
    public Session conectar()
    {
        String server = (String) (vLogin.Servidor.getSelectedItem());
        String ip = vLogin.IP.getText();
        String service_bd = vLogin.ServidorBD.getText();
        String usuario = vLogin.Usuario.getText();
        String password = new String (vLogin.Password.getPassword());
            
        if("MySQL".equals(server)) server = "mysql";
        
        else if("Oracle".equals(server)) server = "oracle";
        
        if ("oracle".equals(server) && "172.17.20.39".equals(ip) && "etsi".equals(service_bd)
            && "DDSI_007".equals(usuario) && "DDSI_007".equals(password)) 
                sesion = HibernateUtil.getSessionFactory().openSession();
        
        return(sesion);
    }
    
   public boolean desconectar()
   {
        boolean resultado = true;
        sesion.disconnect();
        vConsola.vistaConsolaLogin("Desconexion correcta. ¡¡Adios!!");
        return resultado;
   }
   
   //----------- EJERCICIO 3_Practica 1---------------
//   public boolean MetaDatos() throws SQLException
//   {
//       boolean resultado = false;
//       
//       try
//       {
//           DatabaseMetaData data = conexion.informacionBD();
//           
//           vConsola.vistaMetadatos(data);
//           resultado = true;
//       } catch(SQLException ex) {
//           vConsola.vistaConsolaLogin("Error al conseguir la Metadata", ex.getMessage());
//       }
//       
//       return resultado;
//   }  
}
