/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JOptionPane;

/**
 *
 * @author ismae
 */
public class VistaMensajes 
{
    private boolean conexionOK;
    
    public VistaMensajes(boolean c)
    {
        conexionOK = c;
        mensajes();
    }
    
    public void mensajes()
    {
        if(conexionOK)
            JOptionPane.showMessageDialog(null, "Conexión Correcta", "", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Error en la conexion. Revise los parametros introducidos", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
