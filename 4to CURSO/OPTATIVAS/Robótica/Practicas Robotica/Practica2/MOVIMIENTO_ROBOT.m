%Ejercicio para que la pinza coja los 3 bloques del robot pinza

close all, clc
addpath('Funciones')

% CONEXION RoboDK ------------------------
codigo = 1;
Identificador = Iniciacion('RBDK', codigo)
Ready_lab(Identificador,codigo)
%-----------------------------------------

% CONEXION ROBOT AULA -------------------------
% codigo = 35;
% Identificador = Iniciacion('Robot_2', codigo)
% Ready_lab(Identificador,codigo)
% velocidad =10.5;
% aceleracion = 0.5;
%-----------------------------------------

%Configuracion de la pieza pick:
matriz_pinza=eye(4,4); % matriz unidad
posicion=[20 -10 0];  %bloque rojo
alfa=0; beta=0; gamma=0; % Angulos de Euler
codo = 1; avance = 1; simetrico = 0; % Opciones adicionales del robot
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma);

matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotacionx(pi); % Agarre desde arriba
matriz_pinza_global = matriz_pieza*matriz_agarre;


%Configuracion de APROXIMACION
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global*Desplazamiento(0, 0, -4), codo, avance, simetrico);
radianes1 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes1, aceleracion, velocidad,Identificador, codigo)

%Configuracion de PICK
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
radianes2 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes2, aceleracion, velocidad,Identificador, codigo)
RG2_lab(20,Identificador, codigo) %Agarre el bloque en RoboDK

%Configuracion de DESPEGUE
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)*matriz_pinza_global, codo, avance, simetrico);
radianes3 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes3, aceleracion, velocidad,Identificador, codigo)

%-----------------------------
% Configuracion del Place
%-----------------------------
%Configuracion de la pieza place:
posicion=[20 10 0];  
alfa=0; beta=0; gamma=0; % Angulos de Euler
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma);

matriz_pinza_global = matriz_pieza*matriz_agarre;

%Configuracion de aproximacion place
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)* matriz_pinza_global, codo, avance, simetrico);
radianes4 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes4, aceleracion, velocidad,Identificador, codigo)

%Configuracion del place
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
radianes5 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes5, aceleracion, 1,Identificador, codigo)
RG2_lab(100,Identificador, codigo) %Suelta el bloque en RoboDK

%Configuracion separacion place
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global*Desplazamiento(0, 0, -4), codo, avance, simetrico);
radianes6 = [q1, q2, q3, q4, q5, q6]
MoveJ_Robot_lab(radianes6, aceleracion, velocidad,Identificador, codigo)

Ready_lab(Identificador,codigo) %Vuelta a la posicion inicial
rmpath('Funciones')


