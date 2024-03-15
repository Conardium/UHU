all: servidor_ncurses canalpanama barcoeste barcooeste

servidor_ncurses: servidor_ncurses.c definiciones.h comun.h
	cc servidor_ncurses.c -o servidor_ncurses -lncurses

canalpanama: canalpanama.c
	cc canalpanama.c -o canalpanama

barcoeste: barcoeste.c comunbarcos.c comunbarcos.h comun.h
	cc barcoeste.c comunbarcos.c -o barcoeste

barcooeste: barcooeste.c comunbarcos.c comunbarcos.h comun.h
	cc barcooeste.c comunbarcos.c -o barcooeste
