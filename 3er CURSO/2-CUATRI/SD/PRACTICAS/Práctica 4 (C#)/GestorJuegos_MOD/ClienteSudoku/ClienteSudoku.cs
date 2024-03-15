using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;

namespace GestorJuegos
{
    class ClienteSudoku
    {
        static List<ListaSudoku> Sudokus = new List<ListaSudoku>();
        static int aviso = 0;
        static String Ayuda = "";
        static int fila_aux, col_aux;

        static int menuPrincipal(){
            int valor;
            Console.WriteLine("---- Servidor de Juegos ----");
            Console.WriteLine("1.- Gestionar Juegos.");
            Console.WriteLine("2.- Jugar.");
            Console.WriteLine("0.- Salir.");
            Console.Write("\nElige Opción: ");
            int.TryParse(Console.ReadLine(), out valor);

            return valor;
        }

        //======== MODIFICACIÓN ============
        static int menuGestion(){
            int valor;
            Console.WriteLine("---- Gestión de Juegos ----");
            Console.WriteLine("1.- Crear Juego.");
            Console.WriteLine("2.- Borrar Juego.");
            Console.WriteLine("3.- Seleccionar Juego.");
            Console.WriteLine("4.- Clonar Juego.");
            Console.WriteLine("0.- Salir.");
            Console.Write("\nElige Opción: ");
            int.TryParse(Console.ReadLine(), out valor);

            return valor;
        }

        static int menuJuego()
        {
            int valor;
            Console.WriteLine("---- Menú de Juegos ----");
            Console.WriteLine("1.- Poner Valor.");
            Console.WriteLine("2.- Borrar Valor.");
            Console.WriteLine("3.- Ayuda.");
            Console.WriteLine("0.- Salir.");
            Console.Write("\nElige Opción: ");
            int.TryParse(Console.ReadLine(), out valor);

            return valor;
        }

        //======== MODIFICACIÓN ============
        public static int ListarJuegos(int selección)
        {
            int valor;
            Console.WriteLine("\n\nLista de Juegos Almacenados\n");
            Console.WriteLine("Posición        Código          Dificultad");
            Console.WriteLine("------------------------------------------");
            int j = 1;
            for (int i = 0; i < Sudokus.Count; i++)
            {
                Console.WriteLine(j++ + "\t\t" + Sudokus[i].getCodigo() + "\t\t" + Sudokus[i].GetDificultad());
            }
            if (selección == 1) //Borrar
                Console.Write("\nIntroduce la posición del Juego a borrar: ");

            else if(selección == 2) //Clonar
                Console.Write("\nIntroduce la posición del Juego a clonar: ");

            else //Seleccionar
                Console.Write("\nIntroduce la posición del Juego a continuar: ");

            int.TryParse(Console.ReadLine(), out valor);

            return valor - 1;
        }

        public static int MenuDificultad()
        {
            int valor;
            do
            {
                Console.WriteLine("---- Eligen la Dificultad ----");
                Console.WriteLine("1.- Muy Facil.");
                Console.WriteLine("2.- Facil.");
                Console.WriteLine("3.- Medio.");
                Console.WriteLine("4.- Dificil.");
                Console.WriteLine("5.- Muy Dificil.");
                Console.Write("\nElige Opción: ");
                int.TryParse(Console.ReadLine(), out valor);

                if (valor < 1 || valor > 5)
                    Console.WriteLine("** Error, elija una dificultad correcta.");
                
            } while (valor < 1 || valor > 5);

            return valor;
        }

        //======== MODIFICACIÓN ============
        public static void Avisos(int cual)
        {
            switch (cual)
            {
                case 1: //Sudoku creado correctamente
                    Console.WriteLine("*** Nuevo Sudoku creado correctamente. ***\n");
                    break;
                case 2: //Sudoku cargado correctamente
                    Console.WriteLine("*** Sudoku cargado correctamnete. ***\n");
                    break;
                case 3: //Sudoku borrado correctamente
                    Console.WriteLine("*** Sudoku eliminado correctamente. ***\n");
                    break;
                case 4: //Hueco ocupado
                    Console.WriteLine("*** La posicion seleccionada ya está ocupada. ***\n");
                    break;
                case 5: //Hueco rellenado correctamente
                    Console.WriteLine("*** Valor ingresado correctamente. ***\n");
                    break;
                case 6: //Valor no permitido 
                    Console.WriteLine("*** Ingresa un valor valido. ***\n");
                    break;
                case 7: //Lista de Sudokus vacia 
                    Console.WriteLine("*** No hay ningún sudoku que mostrar. ***\n");
                    break;
                case 8: //Valor borrado 
                    Console.WriteLine("*** Valor borrado con exito. ***\n");
                    break;
                case 9: //Mostrar Ayuda
                    Console.WriteLine("*** Los posibles valores en la posicion F=" + fila_aux + " C=" + col_aux + " son: " + Ayuda + "\n");
                    break;
                case 10: //Opcion no valida
                    Console.WriteLine("*** Error, Indica una opción válida\n");
                    break;
                case 11: //Juego clonado
                    Console.WriteLine("*** Juego clonado correctamente ***\n");
                    break;
            }
        }

        static void Main(string[] args)
        {
            int opcPrin = -1, opcDif = -1, opcGest = -1, opcJug = -1, codActual = 0, seleccion, posSudoAct = -1;
            int fil = -1, col = -1, valor = -1;
            char OKborrar;
            TipoDificultad Dificultad = TipoDificultad.VACIO;

            //### Conexión con el servidor ###
            ChannelServices.RegisterChannel(new TcpChannel(), false);
            GestorJuegos gestor = (GestorJuegos)Activator.GetObject(typeof(GestorJuegos), "tcp://localhost:12345/GestorJuegos");

            do
            {
                Console.Clear();
                Console.WriteLine("\nCódigo: " + codActual + "\t" + gestor.GetSudoku(codActual));
                Avisos(aviso);
                opcPrin = menuPrincipal();

                switch (opcPrin)
                {
                    case 1: //Al menú de gestión
                        do
                        {
                            Console.Clear();
                            Console.WriteLine("\nCódigo: " + codActual + "\t" + gestor.GetSudoku(codActual));
                            Avisos(aviso);
                            opcGest = menuGestion();
                            switch (opcGest)
                            {
                                case 1: //Crear Juego
                                    Console.Clear();
                                    opcDif = MenuDificultad();
                                    switch (opcDif)
                                    {
                                        case 1:
                                            Dificultad = TipoDificultad.MUY_FACIL;
                                            break;
                                        case 2:
                                            Dificultad = TipoDificultad.FACIL;
                                            break;
                                        case 3:
                                            Dificultad = TipoDificultad.MEDIA;
                                            break;
                                        case 4:
                                            Dificultad = TipoDificultad.DIFICIL;
                                            break;
                                        case 5:
                                            Dificultad = TipoDificultad.MUY_DIFICIL;
                                            break;
                                    }
                                    codActual = gestor.NuevoJuego(Dificultad);
                                    ListaSudoku ls = new ListaSudoku(codActual, Dificultad);
                                    Sudokus.Add(ls);
                                    posSudoAct = Sudokus.Count - 1;
                                    aviso = 1;
                                    break;

                                case 2: //Borrar Juego
                                    if (codActual != 0){
                                        Console.Write("¿Desea borrar el Sudoku actual? (s/n): ");
                                        char.TryParse(Console.ReadLine(), out OKborrar);
                                        if (OKborrar == 's' || OKborrar == 'S')
                                        {
                                            gestor.BorrarJuego(codActual);
                                            codActual = 0;
                                            Sudokus.RemoveAt(posSudoAct);
                                            aviso = 3;
                                        }
                                        else{
                                            Console.Clear();
                                            seleccion = ListarJuegos(1); //1 = Borrar
                                            if (seleccion >= 0 && seleccion < Sudokus.Count)
                                            {
                                                gestor.BorrarJuego(Sudokus[seleccion].getCodigo());
                                                if (seleccion == posSudoAct)
                                                    codActual = 0;
                                                Sudokus.RemoveAt(seleccion);
                                                aviso = 3;
                                            }
                                            else
                                                aviso = 0;
                                        }
                                    }
                                    else {
                                        if (Sudokus.Count > 0)
                                        {
                                            Console.Clear();
                                            seleccion = ListarJuegos(1); //1 = Borrar
                                            if (seleccion >= 0 && seleccion < Sudokus.Count)
                                            {
                                                gestor.BorrarJuego(Sudokus[seleccion].getCodigo());
                                                if (seleccion == posSudoAct)
                                                    codActual = 0;
                                                Sudokus.RemoveAt(seleccion);
                                                aviso = 3;
                                            }
                                            else
                                                aviso = 0;
                                        }
                                        else
                                            aviso = 7;
                                    }
                                    break;

                                case 3: //Seleccionar Juego
                                    if (Sudokus.Count != 0){
                                        Console.Clear();
                                        seleccion = ListarJuegos(0); //0 = Seleccionar
                                        if (seleccion >= 0 && seleccion < Sudokus.Count)
                                        {
                                            codActual = Sudokus[seleccion].getCodigo();
                                            posSudoAct = seleccion;
                                            aviso = 2;
                                        }
                                        else
                                            aviso = 0;    
                                    }
                                    else
                                        aviso = 7;
                                    break;

                                //======== MODIFICACIÓN ============
                                case 4: //Clonar Juego
                                    if (Sudokus.Count != 0){
                                        Console.Clear();
                                        seleccion = ListarJuegos(2); //0 = Clonar
                                        if (seleccion >= 0 && seleccion < Sudokus.Count)
                                        {
                                            codActual = gestor.ClonarJuego(Sudokus[seleccion].getCodigo());
                                            ListaSudoku aux = new ListaSudoku(codActual, Sudokus[seleccion].GetDificultad());
                                            Sudokus.Add(aux);
                                            posSudoAct = Sudokus.Count-1;
                                            aviso = 11;
                                        }
                                        else
                                            aviso = 0;
                                    }
                                    else
                                        aviso = 7;
                                    break;

                                case 0: //Salir
                                    aviso = 0;
                                    break;

                                default:
                                    aviso = 10;
                                    break;
                            }
                        } while (opcGest != 0);
                        break;

                //------------------------------------------------------------------------------
                    case 2: //Al menú de juego
                        do
                        {
                            Console.Clear();
                            Console.WriteLine("\nCódigo: " + codActual + "\t" + gestor.GetSudoku(codActual));
                            if (gestor.NumeroHuecos(codActual) == 0){
                                Console.Write("*** SUDOKU COMPLETO ");
                                if (gestor.Correcto(codActual) == false)
                                    Console.Write("PERO IN");
                                
                                Console.WriteLine("CORRECTO ***\n");
                            }
                            Avisos(aviso);
                            
                            opcJug = menuJuego();
                            switch (opcJug)
                            {
                                case 1: //Poner Valor
                                    Console.Write("Indica la fila: ");
                                    int.TryParse(Console.ReadLine(), out fil);
                                    Console.Write("Indica la columna: ");
                                    int.TryParse(Console.ReadLine(), out col);
                                    Console.Write("Indica el valor: ");
                                    int.TryParse(Console.ReadLine(), out valor);

                                    if (fil <= 0 || fil > 9 || col <= 0 || col > 9 || valor <= 0 || valor > 9)
                                        aviso = 6;
                                    else{
                                        if (gestor.ComprobarValor(codActual, fil - 1, col - 1, valor.ToString()[0]))
                                            aviso = 4;
                                        
                                        else{
                                            gestor.PonerValor(codActual, fil - 1, col - 1, valor.ToString()[0]);
                                            aviso = 5;
                                        }
                                    }
                                    break;

                                case 2: //Borrar Valor
                                    Console.Write("Indica la fila: ");
                                    int.TryParse(Console.ReadLine(), out fil);
                                    Console.Write("Indica la columna: ");
                                    int.TryParse(Console.ReadLine(), out col);

                                    if (fil <= 0 || fil > 9 || col <= 0 || col > 9)
                                        aviso = 6;
                                    else{
                                        gestor.PonerValor(codActual, fil - 1, col - 1, ' ');
                                        aviso = 8;
                                    }
                                    break;

                                case 3: //Ayuda
                                    Console.Write("Indica la fila: ");
                                    int.TryParse(Console.ReadLine(), out fila_aux);
                                    Console.Write("Indica la columna: ");
                                    int.TryParse(Console.ReadLine(), out col_aux);
                                    Ayuda = gestor.Ayuda(codActual, fila_aux - 1, col_aux - 1);
                                    aviso = 9;
                                    break;

                                case 0: //Volver al menú principal
                                    aviso = 0;
                                    break;

                                default:
                                    aviso = 10;
                                    break;
                            }
                        } while (opcJug != 0);
                        break;

                //------------------------------------------------------------------------------
                    case 0: //Salir
                        aviso = 0;
                        break;

                    default:
                        aviso = 10;
                        break;
                }
            } while (opcPrin != 0);
        }
    }
}
