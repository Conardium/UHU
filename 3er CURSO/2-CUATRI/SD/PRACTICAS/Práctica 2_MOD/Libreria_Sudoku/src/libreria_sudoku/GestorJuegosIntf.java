package libreria_sudoku;

import java.rmi.*;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public interface GestorJuegosIntf extends Remote{
    
    //Dado una dificultad crea un sudoku y devuelve su código.
    int NuevoJuego(TipoDificultad pDifi) throws RemoteException;

    //Dado un código de sudoku lo elimina. Devuelve true si se ha realizado, false si el juego no existe.
    boolean BorrarJuego(int pCodJuego) throws RemoteException;

    //Actualiza la posición pFila, pColumna con el valor pValor en tablero del sudoku cuyo código es
    //pasado por parámetro. Devuelve true si se ha podido actualizar, fase si el juego no existe.
    boolean PonerValor(int pCodJuego, int pFila, int pColumna, char pValor) throws RemoteException;

    //Devuelve el valor de la posición pFil, PColumna del tablero del sudoku cuyo código es pasado por
    //parámetro. Si la posición no existe en el tablero de sudoku devuelve ‘-‘.
    char ObtenerValor(int pCodJuego, int pFila, int pColumna) throws RemoteException;

    //Comprueba si la posición pFila, pColumna del tablero de sudoku cuyo código es pasado por parámetro,
    //si puede o no actualizarse. Devuelve true si la posición indicada verifica las reglas del sudoku,
    //false si no las verifica o bien si el juego no existe.
    boolean ComprobarValor(int pCodJuego, int pFila, int pColumna, char pValor) throws RemoteException;

    //Devuelve el número de posiciones del tablero que están vacías en el sudoku cuyo código es pasado
    //por parámetro. Si no existe el sudoku devuelve -1.
    int NumeroHuecos(int pCodJuego) throws RemoteException;

    //Devuelve una cadena con todos los posibles valores que pueden ponerse en la posición pFila,
    //pColumna. Devolverá a cadena vacía si el juego no existe o bien si la posición indicada no se puede
    //poner ningún valor.
    String Ayuda(int pCodJuego, int pFila, int pColumna) throws RemoteException;

    //Verifica que el sudoku cuyo código es pasado por parámetro, no tiene posiciones que incumplen las
    //reglas del sudoku. Devuelve true si el tablero es correcto, false si no lo es o bien si el juego
    //no existe.
    boolean Correcto(int pCodJuego) throws RemoteException;

    //Devuelve una copia formateada del sudoku con código es pasado por parámetro, incluyendo la
    //información del sudoku y el contenido del tablero. Si juego no existe en la información indicará
    //“**Sudoku Vacío **”.
    String GetSudoku(int pCodJuego) throws RemoteException;
}
