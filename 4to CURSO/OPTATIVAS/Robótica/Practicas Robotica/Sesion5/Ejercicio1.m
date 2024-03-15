%Ejercicio para que la pinza coja los 3 bloques del robot pinza

close all, clc
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
grados1 = [q1, q2, q3, q4, q5, q6]*(180/pi)

pause,cla

%Configuracion de PICK
pinta_bloque(matriz_pieza,'b')

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
matriz2=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global);
grados2 = [q1, q2, q3, q4, q5, q6]*(180/pi)

pause, cla
%Configuracion de DESPEGUE
pinta_bloque(Desplazamiento(0, 0, 5)*matriz_pieza,'b')
[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)*matriz_pinza_global, codo, avance, simetrico);
matriz3=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], Desplazamiento(0, 0, 5)*matriz_pinza_global);
grados3 = [q1, q2, q3, q4, q5, q6]*(180/pi)


pause, cla
%-------------------------------
% Place
%-----------------------------

%posicion=[20 10 0];  
posicion=[15 12 10];
alfa=pi/3; beta=pi/4; gamma=0; % Angulos de Euler
  
matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);


pinta_bloque(Desplazamiento(0, 0, 5)*matriz_pieza,'b')

matriz_pinza_global = matriz_pieza*matriz_agarre;

%configuracion de aproximacion place

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(Desplazamiento(0, 0, 5)* matriz_pinza_global, codo, avance, simetrico);
matriz4=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], Desplazamiento(0, 0, 5)* matriz_pinza_global);
grados4 = [q1, q2, q3, q4, q5, q6]*(180/pi)

pause, cla

%Configuracion de place final
pinta_bloque(matriz_pieza,'b')

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
matriz5=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global);
grados5 = [q1, q2, q3, q4, q5, q6]*(180/pi)

pause, cla

%Configuracion separacionl
pinta_bloque(matriz_pieza,'b')

[q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global*Desplazamiento(0, 0, -4), codo, avance, simetrico);
matriz5=funcion_pinta_UR3_new([q1, q2, q3, q4, q5, q6], matriz_pinza_global*Desplazamiento(0, 0, -4));
grados6 = [q1, q2, q3, q4, q5, q6]*(180/pi)



% %======== Bloque movido ========== 
% matriz_pinza=eye(4,4); % matriz unidad
% %posicion=[20 -10 0];  %bloque rojo
% %posicion=[30 -10 0];  %bloque azul
% %posicion=[40 -10 0];  %bloque verde
% posicion=[20 10 0]; %bloque rojo colocado en otro sitio
% alfa=0; beta=0; gamma=0; % Angulos de Euler
%   
% matriz_pieza=Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionz(alfa)*Rotaciony(beta)*Rotacionx(gamma);
% 
% %pinta_pieza_delgada(matriz_pieza)
% pinta_bloque(matriz_pieza,'b')
% %matriz_agarre = Desplazamiento(0,0,4)*Rotacionz(-pi/2)*Rotacionx(pi); % Agarre desde arriba
% matriz_agarre = Desplazamiento(0,-0.8,4)*Rotacionx(-pi/2)*Rotacionz(pi/2); % Agarre lateral
% matriz_pinza_global = matriz_pieza*matriz_agarre;
% 
% %q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];
% codo = 1; avance = 1; simetrico = 0;
% [q1, q2, q3, q4, q5, q6] = inv_kinema_ur3_new(matriz_pinza_global, codo, avance, simetrico);
% 
% q = [-39.000000, -82.740000, -90.290000, -85.140000, 102.520000, -46.200000];
% 
% q = [q1, q2, q3, q4, q5, q6] *180/pi %Para pasarselo a las diferentes articulaciones del robot
% 
% matriz=funcion_pinta_UR3_new(q*pi/180, matriz_pinza_global);



