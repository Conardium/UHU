/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Clase que se encarga de controlar las distintas acciones del usuario en la interfaz gráfica
 * @author Ismael Da Palma Fernández
 */
public class Controlador implements ActionListener
{
    private VistaPrincipal vPrincipal = null;
    private VistaTeclado vTeclado = null;
    private VistaTecladoAFND vTecladoAFND = null;
    private VistaFichero vFichero = null;
    private VistaFicheroAFND vFicheroAFND = null;
    private AFD afd = null;
    private AFND afnd = null;
    private UtilTablas utilTablas = null;
    
    /**
     * Constructor de la clase
     */
    public Controlador()
    {
        vPrincipal = new VistaPrincipal();
        vTeclado = new VistaTeclado();
        vFichero = new VistaFichero();
        vTecladoAFND = new VistaTecladoAFND();
        vFicheroAFND = new VistaFicheroAFND();
        
        addListeners();
        
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);   
   
        afd = new AFD();
        afnd = new AFND();
        utilTablas = new UtilTablas();
        
        vTeclado.setVisible(false);
        vTecladoAFND.setVisible(false);
        vFichero.setVisible(false);
    }
    
    /**
     * Metodo que añade los distintos Listeners de todos los botones de las interfaces.
     * Para que así se puedan usar sus ActionCommand
     */
    private void addListeners()
    {
        //--- Botones del menu principal ---
        vPrincipal.jButtonSalir.addActionListener(this);
        vPrincipal.jButtonPorTeclado.addActionListener(this);
        vPrincipal.jButtonAFNDporTeclado.addActionListener(this);
        vPrincipal.jButtonPorFichero.addActionListener(this);
        vPrincipal.jButtonAFNDporFichero.addActionListener(this);
        
        //--- Botones del Teclado AFD ---
        vTeclado.jButtonMenuT.addActionListener(this);
        vTeclado.jButtonGenerarAutomata.addActionListener(this);
        vTeclado.jButtonInsertarEstFinales.addActionListener(this);
        vTeclado.jButtonInsertarTransicion.addActionListener(this);
        vTeclado.jButtonInsertarEstInicial.addActionListener(this);
        
        //--- Botones del Teclado AFND ---
        vTecladoAFND.jButtonGenerarAFND.addActionListener(this);
        vTecladoAFND.jButtonInsertarEstFinalesAFND.addActionListener(this);
        vTecladoAFND.jButtonInsertarEstInicialAFND.addActionListener(this);
        vTecladoAFND.jButtonInsertarTransicionAFND.addActionListener(this);
        vTecladoAFND.jButtonMenuTAFND.addActionListener(this);
        
        //--- Botones del Fichero AFD ---
        vFichero.jButtonMenuF.addActionListener(this);
        vFichero.jButtonAbrirFichero.addActionListener(this);
        
        //--- Botones del Fichero AFND ---
        vFicheroAFND.jButtonMenuF.addActionListener(this);
        vFicheroAFND.jButtonAbrirFichero.addActionListener(this);
    }
    
    /**
     * Metodo que realiza las distintas acciones según el evento que se accione en la interfaz gráfica
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "Salir":
                vPrincipal.dispose();
                System.exit(0);
                break;
                
            case "Generar teclado":
                vPrincipal.setVisible(false);
                vTeclado.setLocationRelativeTo(null);
                vTeclado.setVisible(true);
                
                utilTablas.dibujarTablaPorTeclado(vTeclado);
                break;
                
            case "Insertar Transicion":
                    String eO = vTeclado.EstadoOrigen.getText();
                    char simbolo = vTeclado.Simbolo.getText().charAt(0);
                    String eD = vTeclado.EstadoDestino.getText();
                    vTeclado.EstadoOrigen.setText("");
                    vTeclado.EstadoDestino.setText("");
                    vTeclado.Simbolo.setText("");
                    
                    afd.agregarTransicion(eO, simbolo, eD);                    
                    utilTablas.vaciarTablaPorTeclado();
                    utilTablas.rellenarTablaPorTeclado(afd.getTransiciones());
                break;
                
            case "Insertar estados finales":
                String estadoF = vTeclado.EstadosFinales.getText();
                vTeclado.EstadosFinales.setText("");
                
                if(afd.EstadosFinales(estadoF)) //Si el estado existe en alguna transicion continuamos
                {
                    afd.setEstadosFinales(estadoF);
                    System.out.println("\n--- Los estados finales actuales son ---");
                    for(int i = 0; i < afd.getEstadosFinales().length; i++)
                        System.out.println(afd.getEstadosFinales()[i]);
                }
                else
                    JOptionPane.showMessageDialog(null, "El estado insertado debe de existir en el AFD", "Error",JOptionPane.ERROR_MESSAGE);
                break;
                
            case "Establer estado inicial":
                String estadoI = vTeclado.EstadoInicial.getText();
                vTeclado.EstadoInicial.setText("");
                afd.setEstadoInicial(estadoI);
                System.out.println("--- El estado inicial actual es: " + afd.getEstadoInicial());
                break;
                
            case "Reconocer cadena":
                String cadena = vTeclado.Cadena.getText();
                if(cadena.equals(""))
                    JOptionPane.showMessageDialog(null, "La cadena está vacia", "Error",JOptionPane.ERROR_MESSAGE);
                else
                {
                    if(afd.reconocer(cadena)) //SI devuelve true entones la cadena es aceptada, en caso contrario se rechaza
                        JOptionPane.showMessageDialog(null, "La cadena es aceptada", "Info",JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "La cadena es rechazada", "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case "Volver teclado":
                vTeclado.setVisible(false);
                vPrincipal.setVisible(true);
                break;
                
            //------------------------------------------------------------   
            // --                CASEs del AFND por Teclado             --
            //------------------------------------------------------------
            case "Generar AFND por teclado":
                vPrincipal.setVisible(false);
                vTecladoAFND.setLocationRelativeTo(null);
                vTecladoAFND.setVisible(true);
                
                utilTablas.dibujarTablaPorTecladoAFND(vTecladoAFND);
                break;
             
            case "Insertar Transicion AFND":
                String e1 = vTecladoAFND.EstadoOrigen.getText();
                char s;
                
                if(vTecladoAFND.Simbolo.getText().equals(""))
                    s = '\0';
                else
                    s = vTecladoAFND.Simbolo.getText().charAt(0);
                
                String[] e2 = vTecladoAFND.EstadoDestino.getText().split(",");
                
                String[] Tl = vTecladoAFND.TransicionLambda.getText().split(",");
                vTecladoAFND.EstadoOrigen.setText("");
                vTecladoAFND.EstadoDestino.setText("");
                vTecladoAFND.Simbolo.setText("");
                vTecladoAFND.TransicionLambda.setText("");
                
                afnd.agregarTransicion(e1, s, e2);
                afnd.agregarTransicionλ(e1, Tl);
                utilTablas.vaciarTablaPorTecladoAFND();
                utilTablas.rellenarTablaPorTecladoAFND(afnd.getTransiciones(), afnd.getTransicionesλ());
                break;
                
            case "Establer estado inicial AFND":
                String estadoIniAFND = vTecladoAFND.EstadoInicial.getText();
                vTecladoAFND.EstadoInicial.setText("");
                afnd.setEstadoInicial(estadoIniAFND);
                System.out.println("--- El estado inicial actual es: " + afnd.getEstadoInicial());
                break;
                
            case "Insertar estados finales AFND":
                String[] estadosFinalesAFND = vTecladoAFND.EstadosFinales.getText().split(",");

                boolean OK = true;
                int i = 0;
                while(OK && i < estadosFinalesAFND.length) //Comprobamos que todos los estados insertados existan en alguna transicion
                {
                    if(!afnd.buscarEstado(estadosFinalesAFND[i]))
                        OK = false;
                    i++;
                }
                if(OK)
                {
                    for(int j = 0; j < estadosFinalesAFND.length; j++)
                        afnd.setEstadosFinales(estadosFinalesAFND[j]);
                    
                    System.out.println("\n--- Los estados finales actuales del AFND son ---");
                    for(int z = 0; z < afnd.getEstadosFinales().length; z++)
                        System.out.println(afnd.getEstadosFinales()[z]);
                }
                else
                    JOptionPane.showMessageDialog(null, "El/Los estado/s insertado/S debe/n de existir en alguna transicion", "Error",JOptionPane.ERROR_MESSAGE);
                break;
                
            case "Reconocer cadena AFND":
                String cadenaAFND = vTecladoAFND.Cadena.getText();
                if(cadenaAFND.equals(""))
                    JOptionPane.showMessageDialog(null, "La cadena está vacia", "Error",JOptionPane.ERROR_MESSAGE);
                else
                {
                    if(afnd.reconocer(cadenaAFND)) //SI devuelve true entones la cadena es aceptada, en caso contrario se rechaza
                        JOptionPane.showMessageDialog(null, "La cadena es aceptada", "Info",JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "La cadena es rechazada", "Error",JOptionPane.ERROR_MESSAGE);
                }             
                break;
                
            case "Volver teclado AFND":
                vTecladoAFND.setVisible(false);
                vPrincipal.setVisible(true);
                break;
                
                
            //------------------------------------------------------------   
            // --                CASEs del AFD por Fichero              --
            //------------------------------------------------------------
            case "Generar fichero":
                vPrincipal.setVisible(false);
                vFichero.setLocationRelativeTo(null);
                vFichero.setVisible(true);
                break;
            
            case "Abrir":
                //Se abre el fichero con el nombre escrito en el TextField
                break;
                
            case "Volver fichero":
                vFichero.setVisible(false);
                vPrincipal.setVisible(true);
                break;
                
                
            //------------------------------------------------------------   
            // --                CASEs del AFND por Fichero             --
            //------------------------------------------------------------
            case "Generar AFND por fichero":
                vPrincipal.setVisible(false);
                vFicheroAFND.setLocationRelativeTo(null);
                vFicheroAFND.setVisible(true);
                break;
                
            case "Abrir AFND":
                //Se habre el fichero
                break;
                
            case "Volver fichero AFND":
                vFicheroAFND.setVisible(false);
                vPrincipal.setVisible(true);
                break;
        }
    }
}
