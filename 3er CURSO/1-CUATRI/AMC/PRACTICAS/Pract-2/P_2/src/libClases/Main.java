/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

import javax.swing.UIManager;

/**
 *Clase main que ejecuta el programa
 * @author Ismael Da Palma Fernández
 */

public class Main
{
    public static void main(String[] args)
    {
        try //Para cambiar el diseño del jframe a Nimbus
        {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        Controlador c = new Controlador();
    }
}
