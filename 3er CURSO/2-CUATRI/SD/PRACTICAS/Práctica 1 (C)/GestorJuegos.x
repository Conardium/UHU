typedef char Cadena[300];
enum TDificultad {VACIO, MUY_FACIL, FACIL, MEDIA, DIFICIL, MUY_DIFICIL};

struct RCadena
{
   bool Salida;
   Cadena Contenido;
};

struct TFCV
{
   int pCod;
   int pFil;
   int pCol;
   char pVal;
};

struct TFC
{
   int pCod;
   int pFil;
   int pCol;
};

program GESTORJUEGOS {
	version GESTORJUEGOS_VER {
    		int Nuevo(TDificultad pDifi)=1;
		bool Borrar(int pCod)=2;
		bool PonerValor(TFCV pPos)=3;
		char ObtenerValor(TFC pPos)=4;
		bool ComprobarValor(TFCV pPos)=5;
		int NumeroHuecos(int pCod)=6;
		bool Correcto(int pCod)=7;
		RCadena Ayuda(TFC pPos)=8;
		RCadena GetSudoku(int pPos)=9;
	} = 1;
} = 0x30000001;
