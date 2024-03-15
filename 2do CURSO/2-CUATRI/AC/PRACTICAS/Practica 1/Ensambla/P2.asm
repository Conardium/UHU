.model small


.code

    org 100h

    
Programa_Int:

    JMP Reside   
                          
    contador DB 18                      


Rutina_Servicio PROC
    
    CLI  
    
    
    
    DEC contador
    
    CMP contador, 0
    JA FIN
    MOV contador, 18
    
    ;Interrupcion que espera hasta pulsar una tecla para cerrar la pestania
    mov ah, 00h
    int 16h
    
    FIN:
    
    
    STI
    IRET

ENDP


Reside:
    
    MOV DX, offset Rutina_Servicio
    MOV AX, 0
    MOV ES, AX
    MOV SI, 1Ch*4
    CLI
    MOV ES:[SI], DX
    MOV ES:[SI+2], CS
    STI
    MOV DX, offset Reside
    INT 27h
    


END Programa_Int