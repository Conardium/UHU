
package cliente_sudoku;

import libreria_sudoku.TipoDificultad;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class ListaSudoku {
    int Codigo;
    TipoDificultad Dificultad;

    public ListaSudoku() {
    }
    
    public ListaSudoku(int c, TipoDificultad dif) {
        Codigo = c;
        Dificultad = dif;
    }
    
    
}
