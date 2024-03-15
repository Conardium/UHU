.model small  ;1 segmento de memoria para datos + 1 segmento para codigo

.data ;(Segmento de Datos), para declarar las variables que se van a usar

    verde DB 02h
    
     
.code
    ;Inicializacion del segmento de datos (siempre se pone)
    mov ax, seg verde
    mov ds, ax                                             
    
    ;Establecemos el modo texto de pantalla para imprimir el resultado   
    mov al, 13h
    mov ah, 0
    int 10h
    
    mov ax, 0A000h ;---> Direccion de la memoria de video en modo grafico
    mov es, ax
    
    
    mov SI,0
    
    Bucle:
    
    mov bl, verde
    mov ES:[SI], bl
    add SI, 2
      
    CMP SI, 64000  
    JB Bucle
    
     
    ;Interrupcion que espera hasta pulsar una tecla para cerrar la pestania 
    mov ah, 00h
    int 16h
    

    mov ah, 4ch  ;Interrupcion para devolver el control al S.O.
    int 21h
    
end 