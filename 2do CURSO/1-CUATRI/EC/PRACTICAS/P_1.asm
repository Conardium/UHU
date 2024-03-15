.model small  ;1 segmento de memoria para datos + 1 segmento para codigo

.data ;(Segmento de Datos), para declarar las variables que se van a usar

    cadena db 5,0,0,0,0,0,0 ;El primer numero indica la cantidad max de bits
                            ;El segundo indica el contador que ira incrementando cada vez que se pulse una tecla
                            ;El resto son los bits que vamos a indicar por teclado, el ultimo bit hay que poner ENTER
    
    
    peso db 8,4,2,1         ;Necesario para indicar el peso de cada digito, ej.: 2^0 = 1, 2^1 = 2, ...
    
    ;Para el exceso Z=8
    signo db '+'            ;Le pongo + por defecto
    exceso db 8             ;Numero de bits de la cadena (n=4 --> 2^(4-1) = 8)
    
     
.code
    ;Inicializacion del segmento de datos (siempre se pone)
    mov ax, seg cadena
    mov ds, ax                                             
   
    
    mov dx, offset cadena   ;offset cadena indica el desplazamiento dentro del segmento de datos para ir
                            ;almacenando los caracteres
    
    ;Interrupcion para leer por teclado una cadena de caracteres (int 21h)
    mov ah, 0ah
    int 21h
    
    ;Pasamos de Codigo ASCII a sus valores numericos correspondientes
    sub cadena[2],48
    sub cadena[3],48
    sub cadena[4],48
    sub cadena[5],48                                              
    
      
      
    ;----------------------- BINARIO ----------------------------  
    mov al, cadena[2]   ;Calculamos el valor en decimal a traves del peso que tiene cada bit
    mul peso[0]       
    mov bl, al          ;La primera vez movemos el valor de al a bl, en el resto vamos a sumarselo
                        ;para evitar sobrescribir bl
    mov al, cadena[3]   
    mul peso[1]    
    add bl, al
    
    mov al, cadena[4] 
    mul peso[2]    
    add bl, al
    
    mov al, cadena[5] 
    mul peso[3]    
    add bl, al  
    
    
    mov ax, bx ;Vamos a dividir el valor obtenido para repartir cada digito en su parte alta(h) y baja(l)
    mov bl, 10
    
    div bl     ;En este caso pasamos el valor del registro bx a ax, luego de dividirlo en ah se guarda la
               ;el resto(unidad) y en al se guarda el cociente(decena)
    mov cl, al
    add cl, 48
    mov ch, ah ;Despues utilizamos el registro cx(por ejemplo) para pasar el valor decimal a codigo ASCII
    add ch, 48 ;ya el modo pantalla los representa en ASCII
    
    
    ;Establecemos el modo texto de pantalla para imprimir el resultado   
    mov al, 03h
    mov ah, 00h
    int 10h
    
    mov ax, 0b800h ;---> Direccion de la memoria de video en modo texto
    mov es, ax
    mov di, 0
    
    ;--- Representamos BINARIO ---
    mov ah, 00001111b ;caracter impreso en pantalla con fondo negro y texto en blanco
    mov es:[di], 'B'
    mov es:[di+2], 'I'    
    mov es:[di+4], 'N'  ;di es la posicion de la pantalla de la memoria de video 
    mov es:[di+6], ':'  ;donde se va a escribir el caracter
    
    mov al, cl
    mov es:[di+8], ax
    mov al, ch
    mov es:[di+10], ax
    
    
    
    ;----------------------------- EXCESO Z = 2^(n-1) = 8 ---------------------------
    mov al, cadena[2]
    mul peso[0]
    mov bl, al
                           ;En exceso Z primero obtenemos el valor en binario.
    mov al, cadena[3]      ;luego, antes de restarle el exceso, vemos si el valor introducido(ya pasado a
    mul peso[1]            ;decimal) es menor que el exceso (en este caso 8).
    add bl, al
                           ;Para ello vamos a usar una instruccion de comparacion con la condicion
    mov al, cadena[4]      ;JL que saltara si el primer valor es menor que el segundo
    mul peso[2]
    add bl, al
    
    mov al, cadena[5]
    mul peso[3]
    add bl, al
    
    
    ;Vemos si el valor al restarle 8 da negativo
    ;Instruccion de comparacion
    cmp bl, 8
    JL Num_Negativo;Si bl < 8 se salta a Num_Negativo
    
    
    ;Si bl es mayor, solo le quito el exceso
    sub bl, 8
    
    
    ;Para no pasar por el segmento de Num_Negativo hacemos un salto incondicional
    JMP Saltar_Num_Negativo
    
    
    Num_Negativo:      ;Como el exceso es mayor que el valor en decimal es necesario restarle al exceso
      mov dh, bl       ;el valor del numero decimal y no al reves ya que provoca que el numero salga
      mov bl, 8        ;"corrupto" debido a que sale "negativo".
      sub bl, dh  
      ;Cambiamos el signo a negativo
      mov signo, '-'
    
    
    Saltar_Num_Negativo:
    
    mov ax, bx
    mov bl, 10
    
    div bl     ;Divide ax entre bl, en ah se almacena el resto(unidad) y en al el cociente(decena) 
    mov cl, al
    add cl, 48
    mov ch, ah
    add ch, 48
    
    ;--- Representamos EXCESO Z ---
    mov ah, 00001111b
    mov es:[di+320], 'E'
    mov es:[di+322], 'X'
    mov es:[di+324], 'C'
    mov es:[di+326], ':'
    
    mov al, signo
    mov es:[di+328], ax
    
    mov al, cl
    mov es:[di+330], ax
    mov al, ch
    mov es:[di+332], ax
     
     
    ;Interrupcion que espera hasta pulsar una tecla para cerrar la pestania 
    mov ah, 00h
    int 16h
    

    mov ah, 4ch  ;Interrupcion para devolver el control al S.O.
    int 21h
    
end 