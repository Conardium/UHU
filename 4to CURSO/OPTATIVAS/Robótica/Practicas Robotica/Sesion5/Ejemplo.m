% Agarre de objeto y movimiento de brazo
close all, clc
% CONEXION ROBODK
codigo = 1;
Identificador = Iniciacion('RBDK', codigo)

matriz_pinza=eye(4,4); % matriz unidad
posicion=[20 -10 0];  %bloque rojo
%posicion=[30 -10 0];  %bloque azul
%posicion=[40 -10 0];  %bloque verde
%posicion=[20 10 0]; %bloque rojo colocado en otro sitio
alfa=0; beta=0; gamma=0; % Angulos de Euler
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);

%pinta_pieza_delgada(matriz_pieza)
pinta_bloque(matriz_pieza,'b')
matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotacionx(pi); % Agarre desde arriba
%matriz_agarre = Desplazamiento(0,-0.8,4)*Rotacionx(-pi/2)*Rotacionz(pi/2); % Agarre lateral
matriz_pinza_global = matriz_pieza*matriz_agarre;


%Configuracion de APROXIMACION
codo = 1; avance = 1; simetrico = 0;

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global*Desplazamiento(0, 0, -4), codo, avance, simetrico);
matriz1=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global*Desplazamiento(0, 0, -4));
q1 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(q1, 1, 1,Identificador, 1)

