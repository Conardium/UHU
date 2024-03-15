package servidor_sudoku;

import libreria_sudoku.TipoDificultad;

/**
 *
 * @author Ismael Da Palma Fernández
 */
public class Sudoku {
    protected char[][] Tablero = new char[9][9];
    protected TipoDificultad Dificultad;
    protected int Codigo;

    public Sudoku(int cod) {
        Inicializar(TipoDificultad.VACIO, cod);
    }
    
    //Método que devuelve el número de ocurrencias del carácter pVal en la fila pFil.
    //La salida podrá ser 0 = Ninguna ocurrencia, 1=Una ocurrencia, 2=dos o más ocurrencias.
    int BuscarPorFila(int pFil, char pVal){
        int Cuantos = 0;
        for (int c = 0; c < 9 && Cuantos < 2; c++)
            if (Tablero[pFil][c] == pVal)
                Cuantos++;
        return Cuantos;
    }

    //Método que devuelve el número de ocurrencias del caracter pval, en la columna pCol.
    int BuscarPorColumna(int pCol, char pVal){
        int Cuantos = 0;
        for (int f = 0; f < 9 && Cuantos < 2; f++)
            if (Tablero[f][pCol] == pVal)
                Cuantos++;
        return Cuantos;
    }

    //Método que devuelve el número de ocurrencias del caracter pval, en bloque del juego que
    //contiene la fila pfil y la columna pCol.
    int BuscarPorBloque(int pFil, int pCol, char pVal){
        int minF = (pFil / 3) * 3;
        int minC = (pCol / 3) * 3;
        int maxF = minF + 3;
        int maxC = minC + 3;

        int Cuantos = 0;
        for (int f = minF; f < maxF && Cuantos < 2; f++)
            for (int c = minC; c < maxC && Cuantos < 2; c++)
                if (Tablero[f][c] == pVal)
                    Cuantos++;
        return Cuantos;
    }

    //Método que inicializa el atributo Dificultad con pDifi y el Tablero con el carácter ' '
    void Inicializar(TipoDificultad pDifi, int cod){
        Dificultad=pDifi;
        Codigo = cod;
        for (int f = 0; f < 9; f++)
            for (int c = 0; c < 9; c++)
                Tablero[f][c] = ' ';
    }
}


