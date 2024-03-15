/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libClases;

/**
 *Interfaz que establece unos métodos comunes entre el AFD y el AFND
 * @author Ismael Da Palma Fernández
 */
public interface Proceso 
{
    public abstract boolean esFinal(String estado); //true si estado es un estado final
    public abstract boolean reconocer(String cadena) ; //true si la cadena es reconocida
    public abstract String toString( ) ; //muestra las transiciones y estados finales
}
