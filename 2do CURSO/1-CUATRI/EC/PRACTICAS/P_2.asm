.model small


.code

    org 100h

    
Programa_Int:

    JMP Reside   
                          
    contador DB 18                      
    cronometro DB '00:00$'


Rutina_Servicio PROC
    
    CLI  
    
    PUSH AX
    PUSH SI
    PUSH DI
    
    DEC contador
    
    CMP contador, 0
    JA FIN
    MOV contador, 18
    
    
    MOV AX, 0B800h
    MOV ES, AX
    MOV AH, 00001111b
    
    
    MOV SI, 0
    MOV DI, 74
    BUCLE:
        MOV AL, cronometro[SI]
        MOV ES:[DI], AX
        ADD DI, 2
        INC SI
    CMP SI, 4
    JBE BUCLE
    
    
    CMP cronometro[4], '9'
    JNE SUMARSEGUNDO
    MOV cronometro[4], '0'
    INC cronometro[3]
    
    CMP cronometro[3], '6'
    JNE FIN
    MOV cronometro[3], '0'
    INC cronometro[1]
    
    CMP cronometro[1], '9'
    JNE FIN
    MOV cronometro[1], '0'
    INC cronometro[0]
    
    CMP cronometro[0], '6'
    JNE FIN
    MOV cronometro[0], '0'
    JMP FIN
    
    
    SUMARSEGUNDO:
    INC cronometro[4]           
    
    
    FIN:
    
    POP DI
    POP SI
    POP AX
    
    
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