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
    
    public VistaMensajes()
    {
        mensajes("","");
    }
    
    public void mensajes(String mensaje1, String mensaje2)
    {
        if(mensaje1.equals("info"))
            JOptionPane.showMessageDialog(null, mensaje2, mensaje1, JOptionPane.INFORMATION_MESSAGE); //Informacion
        else if (mensaje1.equals("error"))
            JOptionPane.showMessageDialog(null, mensaje2, mensaje1, JOptionPane.ERROR_MESSAGE); //Error
    }
}
