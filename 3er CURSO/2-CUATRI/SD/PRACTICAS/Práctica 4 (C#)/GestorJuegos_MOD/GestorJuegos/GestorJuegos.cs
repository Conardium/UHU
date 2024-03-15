using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GestorJuegos
{
    public class GestorJuegos : MarshalByRefObject, IGestorJuegos
    {
        private List<Sudoku> sudokus = new List<Sudoku>();
        private int CodSudo = 0;
        private int NSudokus = 0;

        //Función para obtener la posición del Sudoku en el Array de Sudokus pasandole el codigo
        private int BuscarSudoku(int codigo) {
            int pos = -1;
            if (sudokus.Count != 0){
                bool encontrado = false;
                int i = 0;
                while (!encontrado && i < sudokus.Count){
                    if (sudokus[i].getCodigo() == codigo){
                        pos = i;
                        encontrado = true;
                    }
                    else
                        i++;
                }
            }
            return pos;
        }


        // ============== SERVICIOS A IMPLEMENTAR POR LA INTERFAZ ===============
        public int NuevoJuego(TipoDificultad pDifi)
        {
            int f, c;
            char Valor;

            CodSudo++;
            Sudoku sudo = new Sudoku(CodSudo); //Creamos un sudoku nuevo
            sudo.Inicializar(pDifi, CodSudo);
            sudokus.Add(sudo);
            NSudokus++;

            char[] ValoresActuales = new char[81];
            Random rnd = new Random((int)DateTime.Now.Ticks);

            for (int i = 0; i < 81; i++)
                ValoresActuales[i] = ' ';

            int Pos;
            for (char v = '1'; v <= '9'; v++){
                Pos = rnd.Next(0, 9);
                while (ValoresActuales[Pos] != ' '){
                    Pos++;
                    if (Pos == 9)
                        Pos = 0;   
                }
                ValoresActuales[Pos] = v;
                PonerValor(CodSudo, 0, Pos, v);
            }

            Pos = 9;
            while (Pos < 81){
                f = Pos / 9;
                c = Pos % 9;
                Valor = ValoresActuales[Pos] != ' ' ? ValoresActuales[Pos] : '0';

                bool EsCorrecto = false;
                while (EsCorrecto == false && Valor < '9'){
                    Valor++;
                    PonerValor(CodSudo, f, c, Valor);
                    EsCorrecto = ComprobarValor(CodSudo, f, c, Valor);
                }

                if (EsCorrecto == true){
                    ValoresActuales[Pos] = Valor;
                    Pos++;
                }
                else{
                    ValoresActuales[Pos] = ' ';
                    PonerValor(CodSudo, f, c, ' ');
                    Pos--;
                }
            }

            int NHuecos = 0;
            switch (pDifi){
                case TipoDificultad.MUY_FACIL:
                    NHuecos = 10;
                    break;
                case TipoDificultad.FACIL:
                    NHuecos = 30;
                    break;
                case TipoDificultad.DIFICIL:
                    NHuecos = 60;
                    break;
                case TipoDificultad.MUY_DIFICIL:
                    NHuecos = 70;
                    break;
                default:
                    NHuecos = 40;
                    break;
            }

            for (int i = 0; i < NHuecos; i++){
                do{
                    f = rnd.Next(0, 9);
                    c = rnd.Next(0, 9);
                } while (ObtenerValor(CodSudo, f, c) == ' ');
                PonerValor(CodSudo, f, c, ' ');
            }

            return CodSudo;
        }

        public bool BorrarJuego(int pCodJuego)
        {
            int pos = BuscarSudoku(pCodJuego);
            if (pos == -1)
                return false;
            else{
                sudokus.RemoveAt(pos);
                return true;
            }
        }

        public bool PonerValor(int pCodJuego, int pFila, int pColumna, char pValor)
        {
            int pos = BuscarSudoku(pCodJuego);

            if (pValor <= 0 || pos == -1)
                return false;
            
            else{
                sudokus[pos].getTablero()[pFila, pColumna] = pValor;
                return true;
            }
        }

        public char ObtenerValor(int pCodJuego, int pFila, int pColumna)
        {
            int pos = BuscarSudoku(pCodJuego);
            if (pos == -1)
                return ' ';
            else
                return sudokus[pos].getTablero()[pFila, pColumna];
        }

        public bool ComprobarValor(int pCodJuego, int pFila, int pColumna, char pValor)
        {
            bool resultado = true;
            int pos = BuscarSudoku(pCodJuego);
            Sudoku s = sudokus[pos];

            if (s.BuscarPorFila(pFila, pValor) != 1 || s.BuscarPorColumna(pColumna, pValor) != 1 
                || s.BuscarPorBloque(pFila, pColumna, pValor) != 1)
                resultado = false;

            return resultado;
        }

        public int NumeroHuecos(int pCodJuego)
        {
            int pos = BuscarSudoku(pCodJuego);
            int Cuantos = 0;
            if (pos == -1)
                Cuantos = -1;
            else
            {
                for (int f = 0; f < 9; f++)
                    for (int c = 0; c < 9; c++)
                        if (sudokus[pos].getTablero()[f, c] == ' ')
                            Cuantos++;
            }
            return Cuantos;
        }

        public string Ayuda(int pCodJuego, int pFila, int pColumna)
        {
            string pAyu = "";
            char Valor;
            char v = ObtenerValor(pCodJuego, pFila, pColumna);
            int pos = BuscarSudoku(pCodJuego);
            if (v == ' ' || pos != -1)
            {
                Valor = '1';
                while (Valor <= '9')
                {
                    PonerValor(pCodJuego, pFila, pColumna, Valor);
                    if (ComprobarValor(pCodJuego, pFila, pColumna, Valor) == true)
                    {
                        pAyu += Valor;
                        pAyu += " ";
                    }
                    Valor++;
                }
                PonerValor(pCodJuego, pFila, pColumna, ' ');
                pAyu += "\n";
            }
            return pAyu;
        }

        public bool Correcto(int pCodJuego)
        {
            int pos = BuscarSudoku(pCodJuego);
            bool resultado = true;
            char Valor = '1';
            int f, c;
            while (Valor <= '9' && resultado == true){
               
                c = 0;
                while (c < 9 && resultado == true){
                    if (sudokus[pos].BuscarPorColumna(c, Valor) != 1)
                        resultado = false;
                    
                    c++;
                }

                f = 0;
                while (f < 9 && resultado == true){
                    if (sudokus[pos].BuscarPorFila(f, Valor) != 1)
                        resultado = false;
                    
                    f++;
                }

                for (f = 0; f < 9 && resultado == true; f += 3){
                    for (c = 0; c < 9 && resultado == true; c += 3){
                        if (sudokus[pos].BuscarPorBloque(f, c, Valor) != 1)
                            resultado = false;
                    }
                }
                Valor++;
            }
            return resultado;
        }

        public string GetSudoku(int pCodJuego)
        {
            char Numero;
            int numHuecos;
            int Fila;
            int pos = BuscarSudoku(pCodJuego);
            String pSudo = "";

            pSudo += "Dificultad: ";
            if (pos == -1)
                pSudo += "** Sudoku vacio **";
            
            else{
                switch (sudokus[pos].getDificultad())
                {
                    case TipoDificultad.MUY_FACIL:
                        pSudo += "Muy Fácil";
                        break;
                    case TipoDificultad.FACIL:
                        pSudo += "Fácil";
                        break;
                    case TipoDificultad.MEDIA:
                        pSudo += "Media";
                        break;
                    case TipoDificultad.DIFICIL:
                        pSudo += "Difícil";
                        break;
                    case TipoDificultad.MUY_DIFICIL:
                        pSudo += "Muy Difícil";
                        break;
                }
            }

            pSudo += "\t\tHuecos: ";
            numHuecos = NumeroHuecos(pCodJuego);
            pSudo += (numHuecos == -1 ? 81 : numHuecos);

            pSudo += "\n  123 456 789\n";
            for (int f = 0; f < 9; f++){
                if (f % 3 == 0)
                    pSudo += " +---+---+---+\n";

                Fila = f + 1;
                pSudo += Fila;
                for (int c = 0; c < 9; c++){
                    if (c % 3 == 0)
                        pSudo += "|";
                    
                    Numero = ObtenerValor(pCodJuego, f, c);
                    pSudo += Numero;
                }
                pSudo += "|\n";
            }
            pSudo += " +---+---+---+\n";

            return pSudo;
        }


        //======== MODIFICACIÓN ============
        public int ClonarJuego(int Codigo)
        {
            int pos = BuscarSudoku(Codigo);
            if (pos != -1)
            {
                CodSudo++;
                Sudoku sudo = new Sudoku(CodSudo); //Creamos un sudoku nuevo
                sudo.Inicializar(sudokus[pos].getDificultad(), CodSudo);
                sudo.setTablero(sudokus[pos].getTablero());
                sudokus.Add(sudo);
                NSudokus++;

                return CodSudo;
            }
            else
                return -1;
        }
    }
}
