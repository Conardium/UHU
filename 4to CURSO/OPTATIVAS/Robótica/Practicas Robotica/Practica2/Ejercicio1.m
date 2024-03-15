%Ejercicio para que la pinza coja los 3 bloques del robot pinza

close all, clc
addpath('Funciones')
% CONEXION ROBODK
codigo = 1;
Identificador = Iniciacion('RBDK', codigo)
Ready_lab(Identificador,codigo)


matriz_pinza=eye(4,4); % matriz unidad
posicion=[20 -10 0];  %bloque rojo
alfa=0; beta=0; gamma=0; % Angulos de Euler
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);

matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotacionx(pi); % Agarre desde arriba
matriz_pinza_global = matriz_pieza*matriz_agarre;


%Configuracion de APROXIMACION
codo = 1; avance = 1; simetrico = 0;

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global*Desplazamiento(0, 0, -4), codo, avance, simetrico);
matriz1=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global*Desplazamiento(0, 0, -4));
radianes1 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes1, 1, 1,Identificador, 1)


%Configuracion de PICK
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
matriz2=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global);
radianes2 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes2, 1, 1,Identificador, 1)
RG2_lab(50,Identificador, codigo)

%Configuracion de DESPEGUE
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)*matriz_pinza_global, codo, avance, simetrico);
matriz3=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], Desplazamiento(0, 0, 5)*matriz_pinza_global);
radianes3 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes3, 1, 1,Identificador, 1)

%-------------------------------
% Place
%-----------------------------

posicion=[20 10 0];  
alfa=0; beta=0; gamma=0; % Angulos de Euler
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);


matriz_pinza_global = matriz_pieza*matriz_agarre;

%configuracion de aproximacion place

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)* matriz_pinza_global, codo, avance, simetrico);
matriz4=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], Desplazamiento(0, 0, 5)* matriz_pinza_global);
radianes4 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes4, 1, 1,Identificador, 1)

%Configuracion de place final
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
matriz5=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global);
radianes5 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes5, 1, 1,Identificador, 1)
RG2_lab(100,Identificador, codigo)

%Configuracion separacionl
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global*Desplazamiento(0, 0, -4), codo, avance, simetrico);
matriz5=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global*Desplazamiento(0, 0, -4));
radianes6 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes6, 1, 1,Identificador, 1)


rmpath('Funciones')


