close all, clc, clear all
%% 26/10/2022
% pinta_bloque(Desplazamiento(5, 4, 3), 'b')
% plot3(5, 4, 3, '*r')
% pinta_bloque(eye(4, 4), 'b')
% pinta_bloque(Rotacionx(pi/2), 'r')
% pinta_bloque(Rotacionx(pi/2)*Desplazamiento(0, 0, 5), 'y')

%% 
% 1.- Bloque que se desplaza al (5, 5, 5)
% 2.- Se rota sobre su eje y (pi/4)
% 3.- Se rota sobre su eje z (-pi/4)
% 4.- Se deslaza respecto al Y global 5 unidades

matriz_transformacion = Desplazamiento(0, 5, 0)*Desplazamiento(5, 5, 5)*...
    Rotaciony(pi/4)*Rotacionz(-pi/4);
pinta_bloque(matriz_transformacion, 'b');
L = [1.5; 1.5; 6; 1];
punto = matriz_transformacion*L;

plot3(punto(1, 1), punto(2, 1), punto(3, 1), '*r')


%% Guia 2

matriz_pinza=eye(4,4) % matriz unidad
q=[0 -1.5700 -1.5700 -1.5700 1.5700 0];

matriz=funcion_pinta_UR3_new(q, matriz_pinza)


%configuracion de la pieza
posicion= [20, -10, 0];
alfa = 0; beta = 0; gamma = 0;

matriz_pinza = Desplazamiento(posicion(1), posicion(2), posicion(3))*Rotacionx

%pinta_pieza_delgada(matriz_pieza)
pinta_bloque(matriz_pieza, 'b');

matriz_agarre = Desplazamiento(0, 0, 4)

