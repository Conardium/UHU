using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Cliente_SW_Sudoku.GestorJuegos_Web;

namespace Cliente_SW_Sudoku
{
    class ListaSudoku
    {
        private int Codigo;
        private TipoDificultad Dificultad;

        public ListaSudoku() { }

        public TipoDificultad GetDificultad()
        {
            return Dificultad;
        }

        public int getCodigo()
        {
            return Codigo;
        }

        public ListaSudoku(int c, TipoDificultad dif)
        {
            Codigo = c;
            Dificultad = dif;
        }
    }
}
