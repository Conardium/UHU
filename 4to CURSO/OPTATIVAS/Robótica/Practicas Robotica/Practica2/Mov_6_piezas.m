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
% Identificador = Iniciacion('Robot_1', codigo)
% Ready_lab(Identificador,codigo)
%-----------------------------------------

RG2_lab(100,Identificador, codigo) %Agarre el bloque en RoboDK

velocidad =20.5;
aceleracion = 0.5;

%% Bloques arriba
%Configuracion de las piezas y pinzas:
posicion_pick=[20 -10 6];  %bloque rojo
posicion_place = [20, 10, 0];

alfa=0; beta=0; gamma=0; % Angulos de Euler

codo = 1; avance = 1; simetrico = 0; % Opciones adicionales del robot

matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotacionx(pi); % Agarre desde arriba

matriz_pieza_pick=Desplazamiento(posicion_pick(1), posicion_pick(2), posicion_pick(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma);
matriz_pinza_pick = matriz_pieza_pick*matriz_agarre;

matriz_pieza_place=Desplazamiento(posicion_place(1), posicion_place(2), posicion_place(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma);
matriz_pinza_place = matriz_pieza_place*matriz_agarre;


%Bucle para coger las 3 piezas de arriba
numPiezas = 3;
for i = 1:numPiezas
    RG2_lab(100,Identificador, codigo)
    
    %Configuracion de APROXIMACION
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_pick*Desplazamiento(0, 0, -4), codo, avance, simetrico);
    radianes1 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes1, aceleracion, velocidad,Identificador, codigo)

    %Configuracion de PICK
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_pick, codo, avance, simetrico);
    radianes2 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes2, aceleracion, velocidad,Identificador, codigo)
    RG2_lab(20,Identificador, codigo) %Agarre el bloque en RoboDK

    %Configuracion de DESPEGUE
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)*matriz_pinza_pick, codo, avance, simetrico);
    radianes3 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes3, aceleracion, velocidad,Identificador, codigo)

    %-----------------------------
    % Configuracion del Place
    %-----------------------------
    %Configuracion de la pieza place:

    %Configuracion de aproximacion place
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)* matriz_pinza_place, codo, avance, simetrico);
    radianes4 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes4, aceleracion, velocidad,Identificador, codigo)

    %Configuracion del place
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_place, codo, avance, simetrico);
    radianes5 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes5, aceleracion, 1,Identificador, codigo)
    RG2_lab(100,Identificador, codigo) %Suelta el bloque en RoboDK

    %Configuracion separacion place
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_place*Desplazamiento(0, 0, -4), codo, avance, simetrico);
    radianes6 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes6, aceleracion, velocidad,Identificador, codigo)

    Ready_lab(Identificador,codigo) %Vuelta a la posicion inicial
	
    matriz_pinza_pick = Desplazamiento(10, 0, 0) * matriz_pinza_pick;
    matriz_pinza_place = Desplazamiento(0, 5, 0) * matriz_pinza_place;

end


%% Bloques debajo
posicion_pick=[20 -10 0];  %bloque rojo
posicion_place = [25, 10, 0];

matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotacionx(pi); % Agarre desde arriba

matriz_pieza_pick=Desplazamiento(posicion_pick(1), posicion_pick(2), posicion_pick(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma);
matriz_pinza_pick = matriz_pieza_pick*matriz_agarre;

matriz_pieza_place=Desplazamiento(posicion_place(1), posicion_place(2), posicion_place(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionz(gamma);
matriz_pinza_place = matriz_pieza_place*matriz_agarre;


%Bucle para coger las 3 piezas de abajo
numPiezas = 3;
for i = 1:numPiezas
    RG2_lab(100,Identificador, codigo)
    
    %Configuracion de APROXIMACION
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_pick*Desplazamiento(0, 0, -4), codo, avance, simetrico);
    radianes1 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes1, aceleracion, velocidad,Identificador, codigo)

    %Configuracion de PICK
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_pick, codo, avance, simetrico);
    radianes2 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes2, aceleracion, velocidad,Identificador, codigo)
    RG2_lab(20,Identificador, codigo) %Agarre el bloque en RoboDK

    %Configuracion de DESPEGUE
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)*matriz_pinza_pick, codo, avance, simetrico);
    radianes3 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes3, aceleracion, velocidad,Identificador, codigo)

    %-----------------------------
    % Configuracion del Place
    %-----------------------------
    %Configuracion de la pieza place:

    %Configuracion de aproximacion place
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)* matriz_pinza_place, codo, avance, simetrico);
    radianes4 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes4, aceleracion, velocidad,Identificador, codigo)

    %Configuracion del place
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_place, codo, avance, simetrico);
    radianes5 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes5, aceleracion, 1,Identificador, codigo)
    RG2_lab(100,Identificador, codigo) %Suelta el bloque en RoboDK

    %Configuracion separacion place
    [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_place*Desplazamiento(0, 0, -4), codo, avance, simetrico);
    radianes6 = [q1, q2, q3, q4, q5, q6]
    MoveJ_Robot_lab(radianes6, aceleracion, velocidad,Identificador, codigo)

    Ready_lab(Identificador,codigo) %Vuelta a la posicion inicial
	
    matriz_pinza_pick = Desplazamiento(10, 0, 0) * matriz_pinza_pick;
    matriz_pinza_place = Desplazamiento(0, 5, 0) * matriz_pinza_place;

end

rmpath('Funciones')
