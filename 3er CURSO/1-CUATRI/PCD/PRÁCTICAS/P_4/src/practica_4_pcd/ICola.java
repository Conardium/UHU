/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_4_pcd;

/**
 *
 * @author ismae
 */
public interface ICola {

    void Acola(Object elemento) throws Exception;

    Object Desacola() throws Exception;

    int GetNum();

    Object Primero() throws Exception;
    
}
