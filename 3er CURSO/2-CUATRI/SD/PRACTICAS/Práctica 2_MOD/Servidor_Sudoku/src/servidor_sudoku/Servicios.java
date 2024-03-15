package servidor_sudoku;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import libreria_sudoku.GestorJuegosIntf;
import libreria_sudoku.TipoDificultad;

/**
 *
 * @author Ismael Da Palma Fernandez
 */
public class Servicios implements GestorJuegosIntf {

    private ArrayList<Sudoku> Sudokus = new ArrayList();
    private int CodSudo = 0;
    private int NSudokus;

    //Constructor
    public Servicios() throws RemoteException {
        NSudokus = 0;
    }
    
    //Funcion para obtener la posicion del sudoku con codigo "cod"
    private int BuscarSudoku(int cod) {
        int pos = -1;
        if (!Sudokus.isEmpty()) {
            boolean encontrado = false;
            int i = 0;
            while (!encontrado && i < Sudokus.size()) {
                if (Sudokus.get(i).Codigo == cod) {
                    pos = i;
                    encontrado = true;
                } else {
                    i++;
                }
            }
        }
        return pos;
    }

    @Override
    public int NuevoJuego(TipoDificultad pDifi) throws RemoteException {
        int f, c;
        char Valor;

        CodSudo++;
        Sudoku sudo = new Sudoku(CodSudo); //Creamos un sudoku nuevo
        sudo.Inicializar(pDifi, CodSudo);
        Sudokus.add(sudo);
        NSudokus++;

        char[] ValoresActuales = new char[81];
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 81; i++) {
            ValoresActuales[i] = ' ';
        }

        int Pos;
        for (char v = '1'; v <= '9'; v++) {
            Pos = rnd.nextInt(9);
            while (ValoresActuales[Pos] != ' ') {
                Pos++;
                if (Pos == 9) {
                    Pos = 0;
                }
            }
            ValoresActuales[Pos] = v;
            PonerValor(CodSudo, 0, Pos, v);
        }

        Pos = 9;
        while (Pos < 81) {
            f = Pos / 9;
            c = Pos % 9;
            Valor = ValoresActuales[Pos] != ' ' ? ValoresActuales[Pos] : '0';

            boolean EsCorrecto = false;
            while (EsCorrecto == false && Valor < '9') {
                Valor++;
                PonerValor(CodSudo, f, c, Valor);
                EsCorrecto = ComprobarValor(CodSudo, f, c, Valor);
            }

            if (EsCorrecto == true) {
                ValoresActuales[Pos] = Valor;
                Pos++;
            } else {
                ValoresActuales[Pos] = ' ';
                PonerValor(CodSudo, f, c, ' ');
                Pos--;
            }
        }

        int NHuecos = 0;
        switch (pDifi) {
            case MUY_FACIL:
                NHuecos = 10;
                break;
            case FACIL:
                NHuecos = 30;
                break;
            case DIFICIL:
                NHuecos = 60;
                break;
            case MUY_DIFICIL:
                NHuecos = 70;
                break;
            default:
                NHuecos = 40;
        }

        for (int i = 0; i < NHuecos; i++) {
            do {
                f = rnd.nextInt(9);
                c = rnd.nextInt(9);
            } while (ObtenerValor(CodSudo, f, c) == ' ');
            PonerValor(CodSudo, f, c, ' ');
        }
        
        return CodSudo;
    }

    @Override
    public boolean BorrarJuego(int pCodJuego) throws RemoteException {
        int pos = BuscarSudoku(pCodJuego);
        Sudokus.remove(pos);
        return true;
    }

    @Override
    public boolean PonerValor(int pCodJuego, int pFila, int pColumna, char pValor) throws RemoteException {
        if (pValor <= 0) {
            return false;
        } else {
            int pos = BuscarSudoku(pCodJuego);
            Sudokus.get(pos).Tablero[pFila][pColumna] = pValor;
            return true;
        }
    }

    @Override
    public char ObtenerValor(int pCodJuego, int pFila, int pColumna) throws RemoteException {
        int pos = BuscarSudoku(pCodJuego);
        if(pos == -1)
            return ' ';
        else
            return Sudokus.get(pos).Tablero[pFila][pColumna];
    }

    @Override
    public boolean ComprobarValor(int pCodJuego, int pFila, int pColumna, char pValor) throws RemoteException {
        boolean resultado = true;
        int pos = BuscarSudoku(pCodJuego);
        Sudoku s = Sudokus.get(pos);

        if (s.BuscarPorFila(pFila, pValor) != 1 || s.BuscarPorColumna(pColumna, pValor) != 1 || s.BuscarPorBloque(pFila, pColumna, pValor) != 1) 
            resultado = false;
        
        return resultado;
    }

    @Override
    public int NumeroHuecos(int pCodJuego) throws RemoteException {
        int pos = BuscarSudoku(pCodJuego);
        int Cuantos = 0;
        if(pos == -1)
            Cuantos = 81;
        else{
            for (int f = 0; f < 9; f++)
                for (int c = 0; c < 9; c++)
                    if (Sudokus.get(pos).Tablero[f][c] == ' ')
                        Cuantos++;
        }
        return Cuantos;
    }

    @Override
    public String Ayuda(int pCodJuego, int pFila, int pColumna) throws RemoteException {
        String pAyu = "";
        char Valor;
        char v = ObtenerValor(pCodJuego, pFila, pColumna);
        
        //MODIFICACION
        if(NumeroHuecos(pCodJuego) != 81){ //Si hay menos de 81 huecos libres borramos un valor, en otro caso no
            Random rnd = new Random();
            rnd.setSeed(System.currentTimeMillis());
            boolean valorEliminado = false;

            while(!valorEliminado){
                int f = rnd.nextInt(9);
                int c = rnd.nextInt(9);
                if(ObtenerValor(pCodJuego, f, c) != ' '){
                    PonerValor(pCodJuego, f, c, ' ');
                    valorEliminado = true;
                }
            }
        }
        
        if (v == ' ') {
            Valor = '1';
            while (Valor <= '9') {
                PonerValor(pCodJuego, pFila, pColumna, Valor);
                if (ComprobarValor(pCodJuego, pFila, pColumna, Valor) == true) {
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

    @Override
    public boolean Correcto(int pCodJuego) throws RemoteException {
        int pos = BuscarSudoku(pCodJuego);
        boolean resultado = true;
        char Valor = '1';
        int f, c;
        while (Valor <= '9' && resultado == true) {
            c = 0;
            while (c < 9 && resultado == true) {
                if (Sudokus.get(pos).BuscarPorColumna(c, Valor) != 1) {
                    resultado = false;
                }
                c++;
            }
            f = 0;
            while (f < 9 && resultado == true) {
                if (Sudokus.get(pos).BuscarPorFila(f, Valor) != 1) {
                    resultado = false;
                }
                f++;
            }

            for (f = 0; f < 9 && resultado == true; f += 3) {
                for (c = 0; c < 9 && resultado == true; c += 3) {
                    if (Sudokus.get(pos).BuscarPorBloque(f, c, Valor) != 1) {
                        resultado = false;
                    }
                }
            }
            Valor++;
        }
        return resultado;
    }

    @Override
    public String GetSudoku(int pCodJuego) throws RemoteException {
        char Numero;
        int Fila;
        int pos = BuscarSudoku(pCodJuego);
        String pSudo = "";
        
        pSudo += "Dificultad: ";
        if (pos == -1) {
            pSudo += "** Sudoku vacio **";
        } 
        else {
            switch (Sudokus.get(pos).Dificultad) {
                case MUY_FACIL:
                    pSudo += "Muy Fácil";
                    break;
                case FACIL:
                    pSudo += "Fácil";
                    break;
                case MEDIA:
                    pSudo += "Media";
                    break;
                case DIFICIL:
                    pSudo += "Difícil";
                    break;
                case MUY_DIFICIL:
                    pSudo += "Muy Difícil";
                    break;
            }
        }

        pSudo += "\t\tHuecos: ";
        pSudo += NumeroHuecos(pCodJuego);
        pSudo += "\n  123 456 789\n";
        for (int f = 0; f < 9; f++) {
            if (f % 3 == 0) {
                pSudo += " +---+---+---+\n";
            }

            Fila = f + 1;
            pSudo += Fila;
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0) {
                    pSudo += "|";
                }
                Numero = ObtenerValor(pCodJuego, f, c);
                pSudo += Numero;
            }
            pSudo += "|\n";
        }
        pSudo += " +---+---+---+\n";
        
        return pSudo;
    }

}
