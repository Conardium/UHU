/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ActividadDAO;
import Modelo.InscripcionDAO;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.VistaActividad;
import Vista.VistaConsola;
import Vista.VistaInscripciones;
import Vista.VistaMonitores;
import Vista.VistaPrincipal;
import Vista.VistaSocios;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;


public class Controlador implements ActionListener
{                                                   
    private Session sesion = null;
    private VistaPrincipal vPrincipal = null;
    
    private VistaMonitores vMonitor = null;
    private MonitorDAO monitorDAO = null;
    
    private VistaSocios vSocio = null;
    private SocioDAO socioDAO = null;
    
    private VistaActividad vActividad = null;
    private ActividadDAO actividadDAO = null;
    
    private VistaInscripciones vInscripcion = null;
    private InscripcionDAO inscripcionDAO = null;
    
    
    private UtilTablas utilTablas = null;
    private VistaConsola vMensaje = null;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    
    public Controlador(Session sesion) //Constructor de la clase
    {
        this.sesion = sesion;
        
        //Vista Principal
        vPrincipal = new VistaPrincipal();
        vPrincipal.setTitle("No pain, no gain"); //Establecemos el nombre del gimnasio
        
        //Vista monitor y monitorDAO
        vMonitor = new VistaMonitores();
        monitorDAO = new MonitorDAO(sesion);
        
        //Vista socio y socioDAO
        vSocio = new VistaSocios();
        socioDAO = new SocioDAO(sesion);
        
        //Vista actividad y actividadDAO
        vActividad = new VistaActividad();
        actividadDAO = new ActividadDAO(sesion);
        
        //Vista incrincipciones e inscripcionDAO
        vInscripcion = new VistaInscripciones();
        inscripcionDAO = new InscripcionDAO(sesion);
        
        //Utiltablas y vista mensajes
        utilTablas = new UtilTablas();
        vMensaje = new VistaConsola();
        
        addListeners();
        
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);
        
        
        vPrincipal.getContentPane().setLayout(new CardLayout());
   
        vMonitor.setVisible(false);
        vSocio.setVisible(false);
    }
    
    public void addListeners() //Metodo para poder captar los action command de los botones de cada menú
    {
        //Botones del menu principal
        vPrincipal.jGestionDeMonitores.addActionListener(this); //Muestra la gestion de monitores (JPanel)
        vPrincipal.SalirAPP.addActionListener(this); //Salir de la aplicacion
        vPrincipal.jGestionDeSocios.addActionListener(this); //Muestra la gestion de Socios
        vPrincipal.jSociosPorActividad.addActionListener(this); //Muestra el Jframe de actividad
        vPrincipal.jGestionDeInscripciones.addActionListener(this); //Muestra la gestión de Inscripciones
        
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
        vSocio.jButtonBuscarSocios.addActionListener(this);
        
        //Botones de Actividad
        vActividad.jButtonLanzarProcedimiento.addActionListener(this);
        vActividad.jButtonCerrarVentana.addActionListener(this);
        
        //Botones de Inscripcion
        vInscripcion.jButtonDarDeAlta.addActionListener(this);
        vInscripcion.jButtonDarDeBaja.addActionListener(this);
        vInscripcion.jButtonSalir.addActionListener(this);
        
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
        
        //Para controlar al clickar el raton (INSCRIPCIONES)
        vInscripcion.jTableInscripciones.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent evt)
                    {
                        vInscripcionjTablaInscripcionesMouseClicked(evt);
                    }      
                });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) //Aquí se va a guardar la función que va a realizar cada uno de los botones y menús de la aplicación
    {
        switch(e.getActionCommand())
        {  
            case "Salir de la aplicación": //Cierra la ventana Principal y termina la ejecución del programa
                vPrincipal.dispose();
            {
                try 
                {
                    sesion.disconnect();
                    System.out.println("Desconexión correcta... ADIOS!!");
                } catch (Exception ex) {
                    vMensaje.vistaConsolaLogin("Error", "Error al desconectar\n" + ex.getMessage());
                }
            }
                System.exit(0);
                break;

                
            case "GestiónDeMonitores": //Abre el jPanel vistaMonitores y muestra una lista de los monitores que hay en la BD
                //Hacemos visible el Jpanel vMonitor y quitamos el de Socios (si lo está)
                vPrincipal.remove(vSocio);
                vSocio.setVisible(false);
                
                vPrincipal.add(vMonitor);
                vMonitor.setVisible(true);
                utilTablas.dibujarTablaMonitores(vMonitor); //Establecemos con este metodo las propiedades del jTable de vistaMonitores
                
                try //Mostramos todos los monitores en la tabla con el metodo pideMonitores
                {
                    pideMonitores();
                }catch(Exception ex) //En caso de fallar al mostrar la tabla, salta un mensaje de error
                {
                    vMensaje.vistaConsolaLogin("Error", "Error en la peticion\n" + ex.getMessage());
                }
                break;
                
            case "Actualizar": //Actualiza el monitor con los valores introducidos en los jTextFields de VistaMonitores
                String codigo = vMonitor.Codigo.getText();
                String nom = vMonitor.Nombre.getText();
                String DNI = vMonitor.DNI.getText();
                String telefono = vMonitor.Telefono.getText();
                String corr = vMonitor.Correo.getText();
                String fech = vMonitor.FechaEntrada.getText();
                String nic = vMonitor.Nick.getText();
                
                Monitor ma = new Monitor(codigo, nom, DNI, telefono, corr, fech, nic); //Creamos un monitor auxiliar con los valores de los jTextFields
                
                try //Intentamos actualizar el monitor "ma". Si el codigo del monitor no existe, entonces saltará la excepción
                {
                    ArrayList listaMonitores=monitorDAO.listaMonitores(); //Guardamos en listaMonitores la lista de monitores que hay actualmente en la BD
                    monitorDAO.actualizarMonitor(ma); //Actualiza el monitor con codigo "ma.getCodigo()", si el codigo no existe en la BD se cancela la transaccion
                    listaMonitores.add(ma); //Añadimos la nueva actualizacion del monitor a la lista
                    
                    //Refrescamos la tabla de monitores para ver los cambios que se han producido
                    utilTablas.vaciarTablaMonitores();
                    utilTablas.rellenarTablaMonitores(listaMonitores);
                    pideMonitores();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vMonitor, "No se ha podido actualizar el monitor.", "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

                
            case "Eliminar": //Este metodo se encarga de eliminar el monitor con el codigo escrito en el jTextField(Codigo) de vistaMonitores
                  //antes de borrarlo, comprobará si ese monitor es responsable de alguna actividad. Si lo es, se creará un monitor genérico
                  //(si es que todavía no se ha creado) con código M999 en el que se guardaran las actividades de las que es responsable el
                  //monitor que queremos borrar, una vez se pasan las actividades al monitor generico procedemos a eliminar el monitor que hemos
                  //indicado. En el caso de que el monitor no tenga actividades responsables simplemente se borrará de la BD sin realizar nada más
                
                int PosMonitor = vMonitor.jTablaMonitores.getSelectedRow();
                int opcion = JOptionPane.showConfirmDialog(vPrincipal, "¿Está seguro de eliminar el monitor?");
                
                if(opcion == 0) //Cuando le das a SI
                {
                    String cod = vMonitor.Codigo.getText(); //Obtenemos el código del monitor a borrar
                    try 
                    {
                        //Vemos si el monitor es responsable de alguna actividad
                        ArrayList<String> numAct = monitorDAO.NumeroDeActividadesResponsable(cod);
                        
                        if(numAct.toString().equals("[0]")) //Si NO es responsable de ninguna actividad
                        {
                            //Borramos el monitor
                            if(monitorDAO.eliminarMonitor(cod)) //Buscamos el codigo del monitor en la BD, si existe lo borramos, sino existe saltará la excepcion
                                utilTablas.EliminarMonitor(PosMonitor); //Si se consigue borrar de la BD, borramos la posicion de la tabla donde estaba ese monitor
                            utilTablas.vaciarTablaMonitores();
                            pideMonitores(); //Rellenamos la tabla de monitores
                        }
                        else //Si el monitor SI es responsable de alguna actividad
                        {
                            //Comprobamos si el monitor generico ya existe o no
                            if(monitorDAO.existeMonitor("M999")) //Si existe, solamente actualizamos las actividades responsables de este
                            {
                                actividadDAO.actualizarMonitorResponsableAGenerico(cod); //actualiza las actividades responsables del monitor con
                                                                                         //codigo "cod" al monitor generico "M999".
                            
                                //Finalmente borramos el monitor 
                                if(monitorDAO.eliminarMonitor(cod)) //Buscamos el codigo del monitor en la BD, si existe lo borramos, sino no
                                    utilTablas.EliminarMonitor(PosMonitor);
                                utilTablas.vaciarTablaMonitores(); 
                                pideMonitores(); //Rellenamos la tabla de monitores
                            }
                            else //En caso de que el monitor generico no exista lo creamos y a continuación cambiamos las actividades responsables 
                            {   //del monitor con codigo "cod" al monitor generico (ESTE "else" SOLO SE CUMPLIRÁ UNA VEZ, YA QUE LAS DEMÁS VECES EL MONITOR M999 YA ESTARÁ CREADO)
                                
                                Monitor mGen = new Monitor("M999", "Responsable Genérico", "00000000A", "", "", "", "");
                                monitorDAO.insertarMonitor(mGen);
                                
                                actividadDAO.actualizarMonitorResponsableAGenerico(cod);
                                
                                //Finalmente borramos el monitor 
                                if(monitorDAO.eliminarMonitor(cod)) //Buscamos el codigo del monitor en la BD, si existe lo borramos, sino no
                                    utilTablas.EliminarMonitor(PosMonitor);
                                utilTablas.vaciarTablaMonitores();
                                pideMonitores(); //Rellenamos la tabla de monitores
                            }                          
                        }
                    } 
                    catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog(null, "-- Transacción cancelada --\n\n" + ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);   
                    }  
                }
                break;
                
            case "Insertar": //Inserta el monitor con los valores de los jTextFields de vistaMonitores y lo intenta insertar en la BD
                String cod = vMonitor.Codigo.getText();
                String n = vMonitor.Nombre.getText();
                String dni = vMonitor.DNI.getText();
                String tlf = vMonitor.Telefono.getText();
                String correo = vMonitor.Correo.getText();
                String fecha = vMonitor.FechaEntrada.getText();
                String nick = vMonitor.Nick.getText();
                
                Monitor mi = new Monitor(cod, n, dni, tlf, correo, fecha, nick); //Guardamos en "mi" el posible monitor que vamos a insertar
                
                try {
                   
                    ArrayList listaMonitores=monitorDAO.listaMonitores();
                    if(monitorDAO.insertarMonitor(mi)) //El metodo devuelve true (si ha conseguido insertar el monitor) o false (si el codigo del
                                                       //monitor ya existia en la BD).                             
                        listaMonitores.add(mi); //Si devuelve true, inserta el monitor en la lista de monitores a mostrar en el jTable
                    
                    //Refrescamos la tabla de monitores para ver los cambios que ha habido
                    utilTablas.vaciarTablaMonitores(); 
                    utilTablas.rellenarTablaMonitores(listaMonitores);
                } catch (Exception ex) { //En caso de que falle al insertar, devolverá una excepcion
                    
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

                
            case "Listar Monitores": //Lista los monitores que se encuentran en la BD.
                try {
                    pideMonitores();
                } catch (Exception ex) {
                    vMensaje.vistaConsolaLogin("Error:", "Error al mostrar los monitores\n" + ex.getMessage());
                }
                break;


                
            case "Vaciar Tabla": //Elimina las filas de la tabla monitores
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
                utilTablas.dibujarTablaSocios(vSocio); //Establecemos las propiedades del jTable socio (PreferredWidgth, ...)
                
                try
                {
                    pideSocios(); //Nada más abrir la gestión de Socios, mostramos un listado de todos los socios de la BD con este metodo
                }
                catch(Exception ex) //En caso de fallar, salta la excepción
                {
                    vMensaje.vistaConsolaLogin("Error", "Error en la peticion\n" + ex.getMessage());
                }
                break;
                
            case "InsertarSocio": //Inserta el socio con los valores de los jTextFields de vSocio y lo intenta insertar en la BD
                String num = vSocio.NumeroSocio.getText();
                String nombre = vSocio.Nombre.getText();
                String d = vSocio.DNI.getText();
                String fechaN = vSocio.FechaNacimiento.getText();
                String tele = vSocio.Telefono.getText();
                String email = vSocio.Correo.getText();
                String fechaE = vSocio.FechaEntrada.getText();
                Character categoria = vSocio.Categoria.getText().charAt(0);
                
                Socio si = new Socio(num, nombre, d, fechaN, tele, email, fechaE, categoria); //Guardamos en "si" el socio que queremos insertar
                
                try {
                   
                    ArrayList listaSocio=socioDAO.listaSocios();
                    if(socioDAO.insertarSocio(si)) //Al igual que con el insertar de monitor, si devuelve true (es que se ha conseguido insertar el socio), si devuelve false es que el socio ya existe en la BD.
                        listaSocio.add(si); //Si se ha insertado, entonces lo añadimos a la lista de socios a mostrar en la tabla
                    
                    utilTablas.vaciarTablaSocios();
                    utilTablas.rellenarTablaSocios(listaSocio);
                } catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

                
            case "ActualizarSocio": //Actualiza el socio con los valores introducidos en los jTextFields de vSocio
                String codigoS = vSocio.NumeroSocio.getText();
                String nomS = vSocio.Nombre.getText();
                String DNIS = vSocio.DNI.getText();
                String fechaNacS = vSocio.FechaNacimiento.getText();
                String telefonoS = vSocio.Telefono.getText();
                String corrS = vSocio.Correo.getText();
                String fechS = vSocio.FechaEntrada.getText();
                Character catS = vSocio.Categoria.getText().charAt(0);
                
                Socio sa = new Socio(codigoS, nomS, DNIS, fechaNacS,telefonoS, corrS, fechS, catS); //Creamos un socio auxiliar con los valores de los TextFields
                
                try 
                {
                    ArrayList listaSocios=socioDAO.listaSocios(); //Guardamos en listaSocios la lista de socios que hay actualmente en la BD
                    socioDAO.actualizarSocio(sa); //Actualizamos el socio con numero (sa.getNumSocio). Si este numero de socio no existe, se cancela la transaccion
                    listaSocios.add(sa);
                    
                    //Actualizamos la tabla de socios para ver los cambios que se han producido
                    utilTablas.vaciarTablaSocios();
                    utilTablas.rellenarTablaSocios(listaSocios);
                    pideSocios();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "EliminarSocio": //Elimina el socio con el numero de socio que se haya indicado en el jTextField
                int PosSocio = vSocio.jTablaSocios.getSelectedRow();
                int op = JOptionPane.showConfirmDialog(vPrincipal, "¿Está seguro de eliminar el socio?");
                
                if(op == 0) //Cuando le das a SI
                {
                    String numeroSocio = vSocio.NumeroSocio.getText(); //Obtenemos el numero del socio
                    try 
                    {
                        if(socioDAO.eliminarSocio(numeroSocio)) //El metodo devuelve true si se ha conseguido borrar correctamente el socio.
                            utilTablas.EliminarSocio(PosSocio); //Si se cumple la condición, borramos ese socio de la lista de socios de la tabla vSocio.
                        
                        //Actualizamos la tabla
                        utilTablas.vaciarTablaSocios();
                        pideSocios();
                    } catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }  
                }
                break;
                
            case "Vaciar Tabla Socios": //Elimina las filas de la tabla vSocio
                utilTablas.vaciarTablaSocios();
                break;
                
            case "Listar Socios": //Muestra en el jTable de vSocio la lista de los socios que hay en la BD.
                try 
                {
                    pideSocios();
                } catch (Exception ex) {
                    vMensaje.vistaConsolaLogin("Error:", "Error al mostrar los monitores\n" + ex.getMessage());
                }
                break;
            
            case "Buscar socios": //Muestra la lista de socios cuyo nombre o letras sean iguales a las escritas en el jTextField "Filtrar por nombre"
                String nombreSocio = vSocio.BuscarSocio.getText(); //Obtenemos el nombre o letras a buscar
                
                if(nombreSocio.equals("")) //Si no hemos escrito nada en el TextField nos salta un mensaje que nos informa que no hemos indicado un nombre
                    JOptionPane.showMessageDialog(null, "No se ha indicado ningún nombre", "Información", JOptionPane.INFORMATION_MESSAGE);
                
                else //En caso de haber indicado un nombre o letras
                {
                    try
                    {
                        //Creamos una lista donde vamos a guardar los socios que han sido filtrados por el nombre o letras indicadas anteriormente.
                        //Si no hay ningún socio que empieze por ese nombre o letras, la tabla se muestra vacia.
                        //!!OJO¡¡ --> El filtrado es sensible a MAYUS.
                        ArrayList<Socio> listaSocioFiltrado = socioDAO.listaSocioPorLetra(nombreSocio); //El metodo busca en la BD los socios cuyo nombre empiece por "nombreSocio", y devuelve los que ha encontrado.
                        
                        vSocio.BuscarSocio.setText(""); //Vaciamos el campo una vez buscamos para más comodidad
                        
                        //Actualizamos la tabla para que solo ahora muestre los socios que se han filtrado
                        utilTablas.vaciarTablaSocios();
                        utilTablas.rellenarTablaSocios(listaSocioFiltrado);
                    }catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            
                
// -----------------------------------------------------------------------------------------------------------            
// ---                                      BOTONES DE ACTIVIDAD                                          ---
// ----------------------------------------------------------------------------------------------------------- 
            
            case "Socios por Actividad": //Hace visible el jFrame correspondiente a la gestion de socios por actividad
                vActividad.setLocationRelativeTo(null);
                vActividad.setVisible(true);
                break;
            
            case "Cerrar Ventana": //Oculta el jFrame de la gestion de socios por actividad
                vActividad.dispose();
                break;
                
            case "Lanzar Procedimiento": //Muestra los socios que participan en la actividad indicada en el jTextField
                
                String idActividad = vActividad.jidActividad.getText(); //Obtenemos el id de la actividad
                utilTablas.dibujarTablaActividad(vActividad); //Configuramos la tabla de vActividad
                
                if(idActividad.equals("")) //En el caso de que no hayamos escrito nada, no realizará la busqueda y simplemente nos informará de que no hemos escrito nada
                    JOptionPane.showMessageDialog(null, "No se ha indicado un id de Actividad", "Error", JOptionPane.INFORMATION_MESSAGE);
                else
                    try 
                    {
                        //Creamos una lista de objetos que va a guardar los socios que pertenecen a la actividad indicada en el campo "id Actividad"
                        ArrayList<Object[]> socios = actividadDAO.listaDeSociosPorActividad(idActividad);
                        
                        //Refrescamos la tabla para ver cuales son los socios que pertenecen a esa actividad, si esta no existe no se mostrará nada
                        utilTablas.vaciarTablaActividad();
                        utilTablas.rellenarTablaActividad(socios);
                        utilTablas.dibujarTablaActividad(vActividad);
                    } 
                    catch (Exception ex) //En caso de haber algún error saltará la excepción
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                break;
            
// -----------------------------------------------------------------------------------------------------------            
// ---                                      BOTONES DE INSCRIPCIONES                                       ---
// -----------------------------------------------------------------------------------------------------------
            
            case "Gestión de Inscripciones": //Hacemos visible el jFrame que se va a encargar de las inscripciones de los socios en las diferentes actividades
                vInscripcion.setLocationRelativeTo(null);
                vInscripcion.setVisible(true);
                
                utilTablas.dibujarTablaRealiza(vInscripcion); //Configuramos el jTable de vInscripción
                try
                {
                    pideInscripciones(); //Rellenamos la tabla con todos los socios y sus correspondientes actividades en las que están inscritos
                }catch(Exception ex)
                {
                    vMensaje.vistaConsolaLogin("Error", "Error en la peticion\n" + ex.getMessage());
                }
                break;
            
            case "Dar de alta": //Añade en la actividad indicada en el campo "Codigo Actividad" el socio del campo "Codigo socio"
                String numS = vInscripcion.CodigoSocio.getText(); //Guardamos en numS el numero del socio a insertar
                String idA = vInscripcion.CodigoActividad.getText(); //Guardamos en idA la actividad en la que queremos dar de alta al socio
                try 
                {
                    inscripcionDAO.darDeAlta(numS, idA); //El metodo intentará asignarle al socio la actividad indicada. Si la actividad o el socio
                                                        //no existe en la BD, la operación se cancelará. Lo mismo ocurre cuando el socio
                                                        //ya está inscrito en esa actividad.
                    
                    pideInscripciones(); //Actualizamos la lista de inscripciones para ver los cambios
                } catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "Dar de baja": //Elimina de la actividad del campo "Codigo Actividad" el socio del campo "Codigo socio"
                String numSocio = vInscripcion.CodigoSocio.getText(); //Guardamos en numSocio el numero del socio a dar de baja
                String idAct = vInscripcion.CodigoActividad.getText(); //Guardamos en idAct la actividad en la que queremos dar de baja al socio
                try 
                {
                    inscripcionDAO.darDeBaja(numSocio, idAct); //El metodo intentará dar de baja al socio con codigo numSocio en la actividad con id
                                                               //idAct. Si la actividad o el socio no existe en la BD, la operación se cancelará, lo mismo
                                                               //ocurre si el socio(que existe en la BD) no está asignado a esa actividad(que existe en la BD). 
                    pideInscripciones();
                } catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            
                case "Salir": //Oculta el jFrame de vista Inscripciones
                vInscripcion.dispose();
                break;
    
                
// -----------------------------------------------------------------------------------------------------------            
// ---                                      BOTONES DE USO COMÚN                                           ---
// -----------------------------------------------------------------------------------------------------------                                                        
            case "VolverMenu": //Vuelve a la vista principal
                vPrincipal.remove(vMonitor);
                vMonitor.setVisible(false);
                vPrincipal.remove(vSocio);
                vSocio.setVisible(false);
            break;
        }
    } 
    
    
    
    public void pideMonitores() throws Exception //Se encarga de guardar en un arraylist la lista de los monitores de la BD, vacía el jTable
    {                                            //de vistaMonitores y lo rellena con los valores obtenidos del arraylist.
        ArrayList<Monitor> lMonitores = monitorDAO.listaMonitores();
        utilTablas.vaciarTablaMonitores();
        utilTablas.rellenarTablaMonitores(lMonitores);
    }
    
    public void pideSocios() throws Exception //Se encarga de guardar en un arraylist la lista de los socios de la BD, vacía el jTable
    {                                         //de vistaSocios y lo rellena con los valores obtenidos del arraylist.
        ArrayList<Socio> lSocios = socioDAO.listaSocios();
        utilTablas.vaciarTablaSocios();
        utilTablas.rellenarTablaSocios(lSocios);
    }
    
    public void pideInscripciones() throws Exception //Guarda la lista de los socios de la BD, vacía el jTable de vistaInscripciones y
    {                                               //lo rellena con los valores obtenidos de la lista
        List<Socio> listaInscripciones = inscripcionDAO.listaDeInscripciones();
        utilTablas.vaciarTablaInscripciones();
        utilTablas.rellenarTablaInscripciones(listaInscripciones);
    }
    
    //Funcion para rellenar los campos al seleccionar una fila en VistaMonitores
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
    
    //Funcion para rellenar los campos al seleccionar una fila en VistaSocios
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
    
    //Funcion para rellenar los campos al seleccionar una fila en VistaInscripciones
    private void vInscripcionjTablaInscripcionesMouseClicked(MouseEvent evt)
    {
        int fila = vInscripcion.jTableInscripciones.getSelectedRow();
        vInscripcion.CodigoSocio.setText(vInscripcion.jTableInscripciones.getValueAt(fila, 0).toString());
        vInscripcion.CodigoActividad.setText(vInscripcion.jTableInscripciones.getValueAt(fila, 1).toString());
    }
}