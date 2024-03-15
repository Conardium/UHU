using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace GestorJuegos_Web
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de interfaz "IService1" en el código y en el archivo de configuración a la vez.

    public enum TipoDificultad { VACIO, MUY_FACIL, FACIL, MEDIA, DIFICIL, MUY_DIFICIL };

    [ServiceContract]
    public interface IServicioJuegos
    {
        //Dada una dificultad crea un sudoku y devuelve su código.
        [OperationContract]
        int NuevoJuego(TipoDificultad pDifi);

        //Dado un código de sudoku lo elimina. Devuelve true si se ha realizado, false si el
        //juego no existe.
        [OperationContract]
        bool BorrarJuego(int pCodJuego);

        //Actualiza la posición pFila, pColumna con el valor pValor en el tablero del sudoku cuyo
        //código es pasado por parámetro. Devuelve true si se ha podido actualizar, false si el
        //juego no existe.
        [OperationContract]
        bool PonerValor(int pCodJuego, int pFila, int pColumna, char pValor);

        //Devuelve el valor de la posición pFila, PColumna del tablero del sudoku cuyo código es
        //pasado por parámetro. Si la posición no existe en el tablero devuelve ‘-‘.
        [OperationContract] 
        char ObtenerValor(int pCodJuego, int pFila, int pColumna);

        //Comprueba si la posición pFila, pColumna del tablero del sudoku cuyo código es pasado
        //por parámetro, si puede o no actualizarse. Devuelve true si la posición indicada 
        //verifica las reglas del sudoku, false si no las verifica o bien si el juego no existe.
        [OperationContract] 
        bool ComprobarValor(int pCodJuego, int pFila, int pColumna, char pValor);

        //Devuelve el número de posiciones del tablero que están vacías en el sudoku cuyo código
        //es pasado por parámetro. Si no existe el sudoku devuelve -1.
        [OperationContract] 
        int NumeroHuecos(int pCodJuego);

        //Devuelve una cadena con todos los posibles valores que pueden ponerse en la posición
        //pFila, pColumna. Devolverá un string vacío si el juego no existe o bien si la posición
        //indicada no se puede poner ningún valor.
        [OperationContract] 
        string Ayuda(int pCodJuego, int pFila, int pColumna);

        //Verifica que el sudoku cuyo código es pasado por parámetro, no tiene posiciones que 
        //incumplen las reglas del sudoku. Devuelve true si el tablero es correcto, false si no
        //lo es o bien si el juego no existe.
        [OperationContract] 
        bool Correcto(int pCodJuego);

        //Devuelve una copia formateada del sudoku cuyo código es pasado por parámetro, 
        //incluyendo la información del sudoku y el contenido del tablero. Si juego no existe
        //en la información indicará “**Sudoku Vacío **”.
        [OperationContract] 
        string GetSudoku(int pCodJuego);
    }

}
