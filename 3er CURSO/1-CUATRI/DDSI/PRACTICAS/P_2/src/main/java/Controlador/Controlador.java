/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaConsola;
import Vista.VistaLogin;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controlador implements ActionListener
{
    private Conexion conexion = null;
    private VistaConsola vConsola = null;
    private VistaPrincipal vPrincipal = null;
    
    public Controlador(Conexion conexion)
    {
        this.conexion = conexion;
        vConsola = new VistaConsola();
        vPrincipal = new VistaPrincipal();
        
        addListeners();
        
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);
    }
    
    public void addListeners()
    {
        vPrincipal.jButtonCerrar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "CerrarDialogoDesconexion":
                vPrincipal.dispose();
                System.exit(0);
                break;
        }
    }
    
}