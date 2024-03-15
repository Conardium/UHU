using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GestorJuegos
{
    class Sudoku
    {
        private char[,] Tablero = new char[9,9];
        private TipoDificultad Dificultad;
        private int Codigo;

        public Sudoku(int cod){
            Inicializar(TipoDificultad.VACIO, cod);
        }

        public char[,] getTablero(){
            return Tablero;
        }

        public TipoDificultad getDificultad(){
            return Dificultad;
        }

        public int getCodigo() {
            return Codigo;
        }

        public int BuscarPorFila(int pFil, char pVal){
            int Cuantos = 0;
            for (int c = 0; c < 9 && Cuantos < 2; c++)
                if (Tablero[pFil,c] == pVal)
                    Cuantos++;
            return Cuantos;
        }

        public int BuscarPorColumna(int pCol, char pVal){
            int Cuantos = 0;
            for (int f = 0; f < 9 && Cuantos < 2; f++)
                if (Tablero[f,pCol] == pVal)
                    Cuantos++;
            return Cuantos;
        }

        public int BuscarPorBloque(int pFil, int pCol, char pVal){
            int minF = (pFil / 3) * 3;
            int minC = (pCol / 3) * 3;
            int maxF = minF + 3;
            int maxC = minC + 3;

            int Cuantos = 0;
            for (int f = minF; f < maxF && Cuantos < 2; f++)
                for (int c = minC; c < maxC && Cuantos < 2; c++)
                    if (Tablero[f,c] == pVal)
                        Cuantos++;
            return Cuantos;
        }

        public void Inicializar(TipoDificultad pDifi, int cod)
        {
            Dificultad = pDifi;
            Codigo = cod;
            for (int f = 0; f < 9; f++)
                for (int c = 0; c < 9; c++)
                    Tablero[f,c] = ' ';
        }
    }
}
