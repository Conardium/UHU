%% Movimiento con mano
clear all, close all, clc


mi_Robot=legoev3('USB');
motor_Cabeza = motor(mi_Robot,'A'); 

resetRotation(motor_Cabeza); %Reseta el encoder
tstart = tic;
tf = 5;

k = 1;
tiempo(k) = 0;

%Definimos motor
motor_Cabeza = motor(mi_Robot,'A');

motor_Cabeza.Speed = 0;
while tiempo(k) < tf
    k = k + 1;
    tiempo(k) = toc(tstart)
    %leer encoder
    giro_Cabeza(k)=readRotation(motor_Cabeza);
end
%Parar motor
motor_Cabeza.Speed = 0;
stop(motor_Cabeza);

plot(tiempo, giro_Cabeza, '.r')

