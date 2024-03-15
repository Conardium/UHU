using GestorJuegos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GestorJuegos
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

        public ListaSudoku(int c, TipoDificultad dif){
            Codigo = c;
            Dificultad = dif;
        }
    }
}
