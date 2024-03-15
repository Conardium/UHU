/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ActividadDAO;
import Modelo.Conexion;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.VistaActividad;
import Vista.VistaConsola;
import Vista.VistaMonitores;
import Vista.VistaPrincipal;
import Vista.VistaSocios;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class Controlador implements ActionListener
{
    private Conexion conexion = null;
    private VistaPrincipal vPrincipal = null;
    
    private VistaMonitores vMonitor = null;
    private MonitorDAO monitorDAO = null;
    
    private VistaSocios vSocio = null;
    private SocioDAO socioDAO = null;
    
    private VistaActividad vActividad = null;
    private ActividadDAO actividadDAO = null;
    
    private UtilTablas utilTablas = null;
    private VistaConsola vMensaje = null;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    
    public Controlador(Conexion conexion) //Constructor
    {
        this.conexion = conexion;
        vPrincipal = new VistaPrincipal();
        
        vMonitor = new VistaMonitores();
        monitorDAO = new MonitorDAO(conexion);
        
        vSocio = new VistaSocios();
        socioDAO = new SocioDAO(conexion);
        
        vActividad = new VistaActividad();
        actividadDAO = new ActividadDAO(conexion);
        
        utilTablas = new UtilTablas();
        vMensaje = new VistaConsola();
        
        addListeners();
        
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);
        
        
        vPrincipal.getContentPane().setLayout(new CardLayout());
   
        vMonitor.setVisible(false);
        vSocio.setVisible(false);
    }
    
    public void addListeners()
    {
        //Botones del menu principal
        vPrincipal.jGestionDeMonitores.addActionListener(this); //Muestra la gestion de monitores (JPanel)
        vPrincipal.SalirAPP.addActionListener(this); //Salir de la aplicacion
        vPrincipal.jGestionDeSocios.addActionListener(this); //Muestra la gestion de Socios
        vPrincipal.jSociosPorActividad.addActionListener(this); //Muestra el Jframe de actividad
        
        //Botones de la gestion de monitores
        vMonitor.jButtonActualizar.addActionListener(this);
        vMonitor.jButtonEliminar.addActionListener(this);
        vMonitor.jButtonInsertar.addActionListener(this);
        vMonitor.jButtonListarMonitores.addActionListener(this);
        vMonitor.jButtonVaciarTabla.addActionListener(this);
        vMonitor.jVolverAlMenu.addActionListener(this);
        
        //Botones de la gestion de socios
        vSocio.jButtonActualizar.addActionListener(this);
        vSocio.jButtonEliminar.addActionListener(this);
        vSocio.jButtonInsertar.addActionListener(this);
        vSocio.jButtonListarSocios.addActionListener(this);
        vSocio.jButtonVaciarTabla.addActionListener(this);
        vSocio.jVolverAlMenu.addActionListener(this);
        
        //Botones de Actividad
        vActividad.jButtonLanzarProcedimiento.addActionListener(this);
        vActividad.jButtonCerrarVentana.addActionListener(this);
        
        //Para controlar al clickar el raton (MONITORES)
        vMonitor.jTablaMonitores.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent evt)
                    {
                        vMonitorjTablaMonitoresMouseClicked(evt);
                    }      
                });
        
        //Para controlar al clickar el raton (SOCIOS)
        vSocio.jTablaSocios.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent evt)
                    {
                        vSociojTablaSociosMouseClicked(evt);
                    }      
                });
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {  
            case "Salir de la aplicación":
                vPrincipal.dispose();
            {
                try {
                    conexion.desconexion();
                    System.out.println("Desconexión correcta... ADIOS!!");
                } catch (SQLException ex) {
                    vMensaje.vistaConsolaLogin("Error", "Error al desconectar\n" + ex.getMessage());
                }
            }
                System.exit(0);
                break;

                
            case "GestiónDeMonitores":
                //Hacemos visible el Jpanel vMonitor y quitamos el de Socios (si lo está)
                vPrincipal.remove(vSocio);
                vSocio.setVisible(false);
                
                vPrincipal.add(vMonitor);
                vMonitor.setVisible(true);
                utilTablas.dibujarTablaMonitores(vMonitor);
                
                try
                {
                    pideMonitores();
                }catch(SQLException ex)
                {
                    vMensaje.vistaConsolaLogin("Error", "Error en la peticion\n" + ex.getMessage());
                }
                break;
                
            case "Actualizar":
                String codigo = vMonitor.Codigo.getText();
                String nom = vMonitor.Nombre.getText();
                String DNI = vMonitor.DNI.getText();
                String telefono = vMonitor.Telefono.getText();
                String corr = vMonitor.Correo.getText();
                String fech = vMonitor.FechaEntrada.getText();
                String nic = vMonitor.Nick.getText();
                
                Monitor ma = new Monitor(codigo, nom, DNI, telefono, corr, fech, nic);
                
                try {
                   
                    ArrayList listaMonitores=monitorDAO.listaMonitores();
                    monitorDAO.actualizarMonitor(ma);
                    listaMonitores.add(ma);
                    utilTablas.vaciarTablaMonitores();
                    utilTablas.rellenarTablaMonitores(listaMonitores);
                    pideMonitores();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

                
            case "Eliminar": //Modificacion_Practica4
                int PosMonitor = vMonitor.jTablaMonitores.getSelectedRow();
                int opcion = JOptionPane.showConfirmDialog(vPrincipal, "¿Está seguro de eliminar el monitor?");
                
                if(opcion == 0) //Cuando le das a SI
                {
                    String cod = vMonitor.Codigo.getText();
                    try 
                    {
                        conexion.inicioTransaccion();
                        //Vemos si el monitor es responsable de alguna actividad
                        int numAct = monitorDAO.NumeroDeActividadesResponsable(cod);
                        System.out.println("El monitor " + cod + " es responsable de " + numAct + " actividades");
                        if(numAct == 0) //Si NO es responsable de ninguna actividad
                        {
                            //Borramos el monitor
                            System.out.println("El monitor se borrará automaticamente");
                            monitorDAO.eliminarMonitor(cod);
                            utilTablas.EliminarMonitor(PosMonitor);
                            utilTablas.vaciarTablaMonitores();
                            pideMonitores();
                        }
                        else //Si el monitor SI es responsable de una actividad
                        {
                            //Insertamos el monitor generico
                            Monitor mGen = new Monitor("M999", "Responsable Genérico", "00000000A", null, null, null, null);
                            monitorDAO.insertarMonitor(mGen);
                            
                            //Sustituimos el responsable de cada actividad de ese monitor a M999
                            actividadDAO.actualizarMonitorResponsableAGenerico(cod);
                            
                            System.out.println("Las actividades responsables de " + cod + " han pasado a M999, borramos ahora a " + cod);
                            //Finalmente borramos el monitor 
                            monitorDAO.eliminarMonitor(cod);
                            utilTablas.EliminarMonitor(PosMonitor);
                            utilTablas.vaciarTablaMonitores();
                            pideMonitores();
                        }
                        
                        conexion.guardarCambiosTransaccion();
                        conexion.cerrarTransaccion();
                    } 
                    catch (SQLException ex) 
                    {
                        try //Si falla al borrar el monitor
                        {
                            conexion.cancelarTransaccion();
                            System.out.println("\n--- La Transicion ha sido cancelada ---");
                        } catch (SQLException ex1) 
                        {
                            JOptionPane.showMessageDialog(null, ex1.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }  
                }
                break;
                
            case "Insertar": 
                String cod = vMonitor.Codigo.getText();
                String n = vMonitor.Nombre.getText();
                String dni = vMonitor.DNI.getText();
                String tlf = vMonitor.Telefono.getText();
                String correo = vMonitor.Correo.getText();
                String fecha = vMonitor.FechaEntrada.getText();
                String nick = vMonitor.Nick.getText();
                
                Monitor mi = new Monitor(cod, n, dni, tlf, correo, fecha, nick);
                
                try {
                   
                    ArrayList listaMonitores=monitorDAO.listaMonitores();
                    monitorDAO.insertarMonitor(mi);
                    listaMonitores.add(mi);
                    utilTablas.vaciarTablaMonitores();
                    utilTablas.rellenarTablaMonitores(listaMonitores);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

                
            case "Listar Monitores":
                try {
                    pideMonitores();
                } catch (SQLException ex) {
                    vMensaje.vistaConsolaLogin("Error:", "Error al mostrar los monitores\n" + ex.getMessage());
                }
                break;


                
            case "Vaciar Tabla":
                utilTablas.vaciarTablaMonitores();
                break;
// -----------------------------------------------------------------------------------------------------------            
// ---                                      BOTONES DE LOS SOCIOS                                          ---
// -----------------------------------------------------------------------------------------------------------
            case "GestionDeSocios":
                //Hacemos visible el Jpanel vSocio y quitamos el de Monitores (si lo está)
                vPrincipal.remove(vMonitor);
                vMonitor.setVisible(false);
                
                vPrincipal.add(vSocio);
                vSocio.setVisible(true);
                utilTablas.dibujarTablaSocios(vSocio);
                
                try
                {
                    pideSocios();
                }catch(SQLException ex)
                {
                    vMensaje.vistaConsolaLogin("Error", "Error en la peticion\n" + ex.getMessage());
                }
                break;
                
            case "InsertarSocio":
                String num = vSocio.NumeroSocio.getText();
                String nombre = vSocio.Nombre.getText();
                String d = vSocio.DNI.getText();
                String fechaN = vSocio.FechaNacimiento.getText();
                String tele = vSocio.Telefono.getText();
                String email = vSocio.Correo.getText();
                String fechaE = vSocio.FechaEntrada.getText();
                String categoria = vSocio.Categoria.getText();
                
                Socio si = new Socio(num, nombre, d, fechaN, tele, email, fechaE, categoria);
                
                try {
                   
                    ArrayList listaSocio=socioDAO.listaSocios();
                    socioDAO.insertarSocio(si);
                    listaSocio.add(si);
                    utilTablas.vaciarTablaSocios();
                    utilTablas.rellenarTablaSocios(listaSocio);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "ActualizarSocio":
                String codigoS = vSocio.NumeroSocio.getText();
                String nomS = vSocio.Nombre.getText();
                String DNIS = vSocio.DNI.getText();
                String fechaNacS = vSocio.FechaNacimiento.getText();
                String telefonoS = vSocio.Telefono.getText();
                String corrS = vSocio.Correo.getText();
                String fechS = vSocio.FechaEntrada.getText();
                String catS = vSocio.Categoria.getText();
                
                Socio sa = new Socio(codigoS, nomS, DNIS, fechaNacS,telefonoS, corrS, fechS, catS);
                
                try {
                   
                    ArrayList listaSocios=socioDAO.listaSocios();
                    socioDAO.actualizarSocio(sa);
                    listaSocios.add(sa);
                    utilTablas.vaciarTablaSocios();
                    utilTablas.rellenarTablaSocios(listaSocios);
                    pideSocios();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "EliminarSocio":
                int PosSocio = vSocio.jTablaSocios.getSelectedRow();
                int op = JOptionPane.showConfirmDialog(vPrincipal, "¿Está seguro de eliminar el socio?");
                
                if(op == 0) //Cuando le das a SI
                {
                    String numeroSocio = vSocio.NumeroSocio.getText();
                    try {
                        socioDAO.eliminarSocio(numeroSocio);
                        
                        utilTablas.EliminarSocio(PosSocio);
                        utilTablas.vaciarTablaSocios();
                        pideSocios();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }  
                }
                break;
                
            case "Vaciar Tabla Socios":
                utilTablas.vaciarTablaSocios();
                break;
                
            case "Listar Socios":
                try {
                    pideSocios();
                } catch (SQLException ex) {
                    vMensaje.vistaConsolaLogin("Error:", "Error al mostrar los monitores\n" + ex.getMessage());
                }
                break;
            
                
// -----------------------------------------------------------------------------------------------------------            
// ---                                      BOTONES DE ACTIVIDAD                                          ---
// ----------------------------------------------------------------------------------------------------------- 
            case "Socios por Actividad":
                vActividad.setLocationRelativeTo(null);
                vActividad.setVisible(true);
                break;
            
            case "Cerrar Ventana":
                vActividad.dispose();
                break;
                
            case "Lanzar Procedimiento":
                String idActividad = vActividad.jidActividad.getText();
                utilTablas.dibujarTablaActividad(vActividad);
                if(idActividad.equals(""))
                    JOptionPane.showMessageDialog(null, "No se ha indicado un id de Actividad", "Error", JOptionPane.INFORMATION_MESSAGE);
                else
                    try 
                    {
                        ResultSet rs = actividadDAO.listaDeSociosPorActividad(idActividad);
                        utilTablas.vaciarTablaActividad();
                        utilTablas.rellenarTablaActividad(rs);
                        utilTablas.dibujarTablaActividad(vActividad);
                    } 
                    catch (SQLException ex) 
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                break;
                
            case "VolverMenu":
                vPrincipal.remove(vMonitor);
                vMonitor.setVisible(false);
                vPrincipal.remove(vSocio);
                vSocio.setVisible(false);
        }
    } 
    
    
    
    public void pideMonitores() throws SQLException
    {
        ArrayList<Monitor> lMonitores = monitorDAO.listaMonitores();
        utilTablas.vaciarTablaMonitores();
        utilTablas.rellenarTablaMonitores(lMonitores);
    }
    
    public void pideSocios() throws SQLException
    {
        ArrayList<Socio> lSocios = socioDAO.listaSocios();
        utilTablas.vaciarTablaSocios();
        utilTablas.rellenarTablaSocios(lSocios);
    }
    
    //Funcion para rellenar los campos al seleccionar una fila (MONITORES)
    private void vMonitorjTablaMonitoresMouseClicked(MouseEvent evt)
    {
        int fila = vMonitor.jTablaMonitores.getSelectedRow();
        vMonitor.Codigo.setText(vMonitor.jTablaMonitores.getValueAt(fila, 0).toString());
        vMonitor.Nombre.setText(vMonitor.jTablaMonitores.getValueAt(fila, 1).toString());
        vMonitor.DNI.setText(vMonitor.jTablaMonitores.getValueAt(fila, 2).toString());
        vMonitor.Telefono.setText(vMonitor.jTablaMonitores.getValueAt(fila, 3).toString());
        vMonitor.Correo.setText(vMonitor.jTablaMonitores.getValueAt(fila, 4).toString());
        vMonitor.FechaEntrada.setText(vMonitor.jTablaMonitores.getValueAt(fila, 5).toString());
        vMonitor.Nick.setText(vMonitor.jTablaMonitores.getValueAt(fila, 6).toString());
    }
    
    //Funcion para rellenar los campos al seleccionar una fila (SOCIOS)
    private void vSociojTablaSociosMouseClicked(MouseEvent evt)
    {
        int fila = vSocio.jTablaSocios.getSelectedRow();
        vSocio.NumeroSocio.setText(vSocio.jTablaSocios.getValueAt(fila, 0).toString());
        vSocio.Nombre.setText(vSocio.jTablaSocios.getValueAt(fila, 1).toString());
        vSocio.DNI.setText(vSocio.jTablaSocios.getValueAt(fila, 2).toString());
        vSocio.FechaNacimiento.setText(vSocio.jTablaSocios.getValueAt(fila, 3).toString());  
        vSocio.Telefono.setText(vSocio.jTablaSocios.getValueAt(fila, 4).toString());
        vSocio.Correo.setText(vSocio.jTablaSocios.getValueAt(fila, 5).toString());
        vSocio.FechaEntrada.setText(vSocio.jTablaSocios.getValueAt(fila, 6).toString());
        vSocio.Categoria.setText(vSocio.jTablaSocios.getValueAt(fila, 7).toString());
    }
}


//---Para quitar la gestion de monitores de vPrincipal----
//vPrincipal.remove(vMonitor);
//vMonitor.setVisible(false);